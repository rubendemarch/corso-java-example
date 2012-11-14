/**
 * 
 */
package example;

import java.sql.SQLException;

import bussinessObject.Alunno;
import configuration.MyProperties;
import dbo.connection.Connessione;
import dbo.impl.DboAlunni;
import exception.config.Config;

/**
 * @author Dr
 *
 */
public class Db {
	
	public Db(){
		test1();
	}
	/**
	 * 
	 */
	private void test1() {
		Connessione c = null;
		try {
			c = new Connessione(new MyProperties("DbConf.xml"));
		} catch (ReflectiveOperationException | SQLException e) {
			e.printStackTrace();
		}catch (Config e) {
			e.printStackTrace();
		}
		if (c!=null) {
			DboAlunni dboAlunni = new DboAlunni(c);
			for (Alunno alunno : dboAlunni.readAlunni()) {
				System.out.println(alunno);
			}
		}
		c.closeConnection();
	}

}
