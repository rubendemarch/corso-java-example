/**
 * 
 */
package file;

import java.io.File;
import java.io.IOException;

import util.MyLogger;

/**
 * @author Dr
 *
 */
public class RootFile {
	private MyLogger logger;
	private File f;
	/**
	 * 
	 */
	public RootFile() {
		logger=new MyLogger(this.getClass());
	}

	public boolean creaFile(String path){
		final String metodo="creaFile";
		f=new File(path);
		if(f.exists()){
			return true;
		}
		try {
			f.createNewFile();
			return true;
		} catch (IOException e) {
			logger.error(metodo, "createNewFile", e);
		}
		return false;
	}

}
