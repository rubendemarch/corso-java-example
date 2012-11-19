/**
 * 
 */
package example;

import java.sql.SQLException;
import java.util.HashMap;

import util.StringFormat;
import bussinessObject.ColumnDescriptor;
import bussinessObject.Descriptors;
import bussinessObject.interfaces.ColumnDescriptorInterface;
import configuration.MyProperties;
import dbo.RootDbo;
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
		test2();
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

	private void test2() {
		Connessione c = null;
		try {
			c = new Connessione(new MyProperties("DbConf.xml"));
		} catch (ReflectiveOperationException | SQLException e) {
			e.printStackTrace();
		}catch (Config e) {
			e.printStackTrace();
		}
		if (c!=null) {
			RootDbo dbo = new RootDbo(c);
			RootFile rf = new RootFile();
			rf.creaFile("test1_1.txt");
			RootFile rf2 = new RootFile();
			rf2.creaFile("test2_1.txt");
			
			Descriptors di = new Descriptors();
			
			di.addColumnDescriptor(new ColumnDescriptor("USER_ID",						36, 	50,true,' ', ";",""));
			di.addColumnDescriptor(new ColumnDescriptor("nome",								35, 	50,true,' ', ";",""));
			di.addColumnDescriptor(new ColumnDescriptor("cognome",						35, 	50,true,' ', ";",""));
			di.addColumnDescriptor(new ColumnDescriptor("data_nascita",					0, 		50,true,' ', ";","yyyyMMdd"));
			di.addColumnDescriptor(new ColumnDescriptor("sesso",								1, 		1,true,' ', ";",""));
			di.addColumnDescriptor(new ColumnDescriptor("cf",									16, 	16,true,' ', ";",""));
			di.addColumnDescriptor(new ColumnDescriptor("STATO_NASCITA",				35, 	50,true,' ', ";",""));
			di.addColumnDescriptor(new ColumnDescriptor("COD_STATO_NASCITA",		4,		50,true,' ', ";",""));
			di.addColumnDescriptor(new ColumnDescriptor("COD_COMUNE_NASCITA",	4,		50,true,' ', ";",""));
			di.addColumnDescriptor(new ColumnDescriptor("COMUNE_NASCITA",			35, 	50,true,' ', ";",""));
			
			StringBuilder sql = new StringBuilder("SELECT ");
			for (ColumnDescriptorInterface cdi: di.getDescriptors()) {
				sql.append(cdi.getColumName()).append(",");
			}
			sql=sql.deleteCharAt(sql.length()-1);
			sql.append(" FROM ")
				.append(" alunni")
				.append("")//whereCond
				.append("")//orderByCond
				.append("");//groupByCond
			
			
			for (HashMap<String, Object> map: dbo.dynamicExecuteQuery(di,
					sql.toString(),
					null)) {
				rf.println(StringFormat.formatMap(map, true, di));
				rf2.println(StringFormat.formatMap(map, false, di));
			}
			rf.closePrintStream();
			rf2.closePrintStream();
		}
		c.closeConnection();
	}
	
}
