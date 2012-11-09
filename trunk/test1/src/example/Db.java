/**
 * 
 */
package example;

import java.sql.SQLException;

import bussinessObject.Alunno;
import dbo.connection.Connessione;
import dbo.impl.DboAlunni;

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
			c = new Connessione();
		} catch (ReflectiveOperationException | SQLException e) {
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
