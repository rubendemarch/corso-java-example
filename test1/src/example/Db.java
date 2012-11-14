/**
 * 
 */
package example;

import java.sql.SQLException;
import java.util.HashMap;

import util.StringFormat;

import bussinessObject.Alunno;
import configuration.MyProperties;
import dbo.connection.Connessione;
import dbo.impl.DboAlunni;
import exception.config.Config;
import file.RootFile;

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
			RootFile rf = new RootFile();
			rf.creaFile("test1.txt");
			RootFile rf2 = new RootFile();
			rf2.creaFile("test2.txt");
			
			for (HashMap<String, Object> alunno: dboAlunni.dynamicReadAlunni()) {
				rf.println(StringFormat.formatAlunno(alunno, true));
				rf2.println(StringFormat.formatAlunno(alunno, false));
			}
			rf.closePrintStream();
			rf2.closePrintStream();
		}
		c.closeConnection();
	}

}
