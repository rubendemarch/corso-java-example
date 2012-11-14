/**
 * 
 */
package example;

import configuration.MyProperties;
import exception.config.Config;

/**
 * @author Dr
 *
 */
public class PropertiesTest {

	/**
	 * 
	 */
	public PropertiesTest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		MyProperties myProperties = new MyProperties("../../DbConf.xml");
		MyProperties myProperties = new MyProperties("DbConf.xml");
		myProperties.setProperty("url","jdbc:oracle:thin:@localhost:1521",false);
		myProperties.setProperty("dbName",":xe",false);
		myProperties.setProperty("driver","oracle.jdbc.driver.OracleDriver",false);
		myProperties.setProperty("userName","APPLICAZIONIJAVA",true);
		myProperties.setProperty("password","java",true);
		try {
			myProperties.writeConfigFile();
		} catch (Config e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
