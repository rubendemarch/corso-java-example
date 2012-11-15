/**
 * 
 */
package dbo.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import util.MyLogger;
import configuration.MyProperties;
import exception.config.Config;

/**
 * @author Dr
 *
 */
public class Connessione {
	private MyLogger logger;
	private Connection connection=null;
	private String url = null;
	private String dbName = null;
	private String driver = null;
	private String userName = null; 
	private String password = null;

	/**
	 * 
	 * @throws ReflectiveOperationException
	 * @throws SQLException
	 * @throws Config 
	 */
	public Connessione(MyProperties properties) throws ReflectiveOperationException, SQLException, Config {
		logger=new MyLogger(this.getClass());
		final String metodo="costruttore";
		logger.start(metodo);
		
		try {
			url =				properties.getPropertyValue("url");
			dbName =		properties.getPropertyValue("dbName");
			driver =			properties.getPropertyValue("driver");
			userName =	properties.getPropertyValue("userName");
			password =	properties.getPropertyValue("password");
		} catch (Config e) {
			logger.fatal(metodo,"tentativo recupero dati di configurazione da properties",e);
			throw e;
		}
		
		try {
			Class.forName(driver).newInstance();
		} catch (InstantiationException 
				| IllegalAccessException
				| ClassNotFoundException e) {
			logger.fatal(metodo,
						"tentativo recupero driver",
						e);
			throw e;
		}
		
		try {
			connection=
				DriverManager.getConnection(url+dbName,
											userName,
											password);
		} catch (SQLException e) {
			logger.fatal(metodo,
					"tentativo connessione database",
					e);
			throw e;
		}
		logger.end(metodo);
	}

	@Deprecated
	public void closeConnection(){
		final String metodo="closeConnection";
		logger.start(metodo);
		if (connection!=null) {
			try {
				connection.close();
			} catch (SQLException e) {
				logger.warn(metodo, "tentativo chiusura connessione database", e);
			}
		}
		logger.end(metodo);
	}

	protected void finalize(){
		closeConnection();
	}

	/**
	 * @return the connection
	 */
	public Connection getConnection() {
		return connection;
	}
}
