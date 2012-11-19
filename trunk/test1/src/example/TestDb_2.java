/**
 * 
 */
package example;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import util.StringFormat;
import bussinessObject.ColumnDescriptor;
import bussinessObject.Descriptors;
import bussinessObject.interfaces.ColumnDescriptorInterface;
import configuration.MyProperties;
import dbo.RootDbo;
import dbo.connection.Connessione;
import exception.config.Config;
import file.RootFile;

/**
 * @author Dr
 *
 */
public class TestDb_2 {

	/**
	 * 
	 */
	public TestDb_2() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
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
			
			Descriptors di = new Descriptors();
			
			di.addColumnDescriptor(new ColumnDescriptor("USER_ID",							36, 	50,true,' ', ";",""));
			di.addColumnDescriptor(new ColumnDescriptor("nome",								35, 	50,true,' ', ";",""));
			di.addColumnDescriptor(new ColumnDescriptor("cognome",							35, 	50,true,' ', ";",""));
			di.addColumnDescriptor(new ColumnDescriptor("data_nascita",					0, 		50,true,' ', ";","yyyyMMdd"));
			di.addColumnDescriptor(new ColumnDescriptor("sesso",								1, 		1,true,' ', ";",""));
			di.addColumnDescriptor(new ColumnDescriptor("cf",									16, 	16,true,' ', ";",""));
			di.addColumnDescriptor(new ColumnDescriptor("STATO_NASCITA",				35, 	50,true,' ', ";",""));
			di.addColumnDescriptor(new ColumnDescriptor("COD_STATO_NASCITA",		4,		50,true,' ', ";",""));
			di.addColumnDescriptor(new ColumnDescriptor("COD_COMUNE_NASCITA",	4,		50,true,' ', ";",""));
			di.addColumnDescriptor(new ColumnDescriptor("COMUNE_NASCITA",			35, 	50,true,' ', ";",""));
			di.addColumnDescriptor(new ColumnDescriptor("DATA_ISCRIZIONE",			0, 	50,true,' ', ";","yyyyMMdd"));
			
			
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
			
			RootFile f1 = new RootFile();
			f1.creaFile("1.txt");
			
			RootFile f2 = new RootFile();
			f2.creaFile("2.txt");
			
			RootFile f3 = new RootFile();
			f3.creaFile("3.txt");
			
			
			
			List<HashMap<String,Object>> alunni = dbo.dynamicExecuteQuery(di, sql.toString(), null);
			
			StringBuilder sqlInsert = new StringBuilder("INSERT INTO ALUNNI (");
			StringBuilder sqlInsertParam = new StringBuilder();
			for (ColumnDescriptorInterface cdi: di.getDescriptors()) {
				sqlInsert.append(cdi.getColumName()).append(",");
				sqlInsertParam.append("?,");
			}
			sqlInsert=sqlInsert.deleteCharAt(sqlInsert.length()-1);
			sqlInsert.append(")VALUES(")
						.append(sqlInsertParam.deleteCharAt(sqlInsertParam.length()-1))
						.append(")");
			
			ArrayList<Object>params=new ArrayList<>();
			
			HashMap<String, Object> alunno;
			
			ColumnDescriptorInterface cdi;
			int i;
			for (i =0;i<alunni.size();i++) {
				alunno=alunni.get(i);
				
				f1.println(StringFormat.formatMap(alunno, true, di));
				params.clear();
				
				for (int ci =0;ci<di.getDescriptors().size();ci++ ) {
					cdi=di.getDescriptors().get(ci);
					params.add(alunno.get(cdi.getColumName()));
				}
				
				System.out.println(dbo.dynamicExecuteUpdate(sqlInsert.toString(), params));
			}
			
			alunni = dbo.dynamicExecuteQuery(di, sql.toString(), null);
			
			
			StringBuilder sqlUpdate = new StringBuilder("UPDATE ALUNNI SET ");
			for (int ui=3;ui<6;ui++) {
				cdi=di.getDescriptors().get(ui);
				
				sqlUpdate.append(cdi.getColumName()).append("=?,");
			}
			sqlUpdate=sqlUpdate.deleteCharAt(sqlUpdate.length()-1);
			sqlUpdate.append(" WHERE ")
						.append(di.getDescriptors().get(0).getColumName())
						.append("=?");
			
			
			
			for (i =0;i<alunni.size();i++) {
				alunno=alunni.get(i);
				
				f2.println(StringFormat.formatMap(alunno, true, di));
				params.clear();
				
				for (int ui=3;ui<6;ui++) {
					cdi=di.getDescriptors().get(ui);
					params.add(alunno.get(cdi.getColumName()));
				}
				params.add(alunno.get(di.getDescriptors().get(0).getColumName()));
				
				System.out.println(dbo.dynamicExecuteUpdate(sqlUpdate.toString(), params));
			}
			
			
			alunni = dbo.dynamicExecuteQuery(di, sql.toString(), null);
			for (i =0;i<alunni.size();i++) {
				alunno=alunni.get(i);
				
				f3.println(StringFormat.formatMap(alunno, true, di));
			}
		}
		c.closeConnection();

	}

}
