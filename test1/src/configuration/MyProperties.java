/**
 * 
 */
package configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import util.MyLogger;
import exception.ConfigFileNotFound;
import exception.ConfigFileNotValid;

/**
 * @author Dr
 *
 */
public class MyProperties {
	private MyLogger logger;
	private Properties properties=null;
	private FileInputStream inputStream=null;
	private FileOutputStream outputStream=null;
	private String pathFile;

	/**
	 * 
	 */
	public MyProperties(String pathFile) {
		logger=new MyLogger(this.getClass());
		final String metodo="costruttore";
		logger.start(metodo);
		this.pathFile=pathFile;
		logger.end(metodo);
	}

	public String getPropertyValue(String key) throws ConfigFileNotFound {
		final String metodo="getPropertyValue";
		if(inputStream==null){
			try {
				inputStream=new FileInputStream(pathFile);
			} catch (FileNotFoundException e) {
				logger.fatal(metodo, "tentativo connessione file di properties", e);
				throw new ConfigFileNotFound("file di configurazione non trovato");
			}
		}
		if(properties==null){
			properties=new Properties();
			if(pathFile.toUpperCase().endsWith(".XML")){
				try {
					properties.loadFromXML(inputStream);
				} catch (InvalidPropertiesFormatException e) {
					logger.fatal(metodo, "file corrotto", e);
					throw new ConfigFileNotValid( "file corrotto");
				} catch (IOException e) {
					logger.fatal(metodo, "impossibile leggere il file di properties.xml", e);
					throw new ConfigFileNotValid( "impossibile leggere il file di properties.xml");
				}
			}else if(pathFile.toUpperCase().endsWith(".PROPERTIES")){
				try {
					properties.load(inputStream);
				} catch (IOException e) {
					logger.fatal(metodo, "impossibile leggere il file di properties.properties", e);
					throw new ConfigFileNotValid( "impossibile leggere il file di properties.properties");
				}
			}
			try {
				inputStream.close();
			} catch (IOException e) {
				logger.warn(metodo, "fallito tentativo chiusura configInputStream", e);
			}
		}
		return properties.getProperty(key);
	}

	//public 
}
