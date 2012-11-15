/**
 * 
 */
package dbo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import util.MyLogger;
import bussinessObject.interfaces.ColumnDescriptorInterface;
import bussinessObject.interfaces.DescriptorsInterface;
import dbo.connection.Connessione;

/**
 * @author Dr
 *
 */
public class RootDbo {
	private MyLogger logger;
	protected Connessione connessione;

	/**
	 * @param connessione
	 */
	public RootDbo(Connessione connessione) {
		logger=new MyLogger(this.getClass());
		final String metodo="costruttore";
		logger.start(metodo);
		this.connessione = connessione;
		logger.end(metodo);
	}
/**
 * 
 * @param ps
 * @param rs
 */
	protected void close(PreparedStatement ps, ResultSet rs){
		final String metodo="close";
		logger.start(metodo);
		if (rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				logger.warn(metodo, "ResultSet.close", e);
			}
		}
		if (ps!=null) {
			try {
				ps.close();
			} catch (SQLException e) {
				logger.warn(metodo, "PreparedStatement.close", e);
			}
		}
		logger.end(metodo);
	}

	/**
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	protected PreparedStatement getPreparedStatement(String sql) throws SQLException {
		final String metodo="getPreparedStatement";
		try {
			return connessione.getConnection().prepareStatement(sql);
		} catch (SQLException e) {
			logger.error(metodo, "creo PreparedStatement", e);
			throw e;
		}
	}
	
	/**
	 * @param ps
	 * @return
	 * @throws SQLException
	 */
	protected ResultSet executeQuery(PreparedStatement ps) throws SQLException {
		final String metodo="executeQuery";
		try {
			return ps.executeQuery();
		} catch (SQLException e) {
			logger.error(metodo, "eseguo query", e);
			throw e;
		}
	}

	public List<HashMap<String,Object>> dynamicRead(DescriptorsInterface di) {
		final String metodo="dynamicRead";
		logger.start(metodo);
		StringBuilder sql = new StringBuilder("SELECT ");
		for (ColumnDescriptorInterface cdi: di.getDescriptors()) {
			sql.append(cdi.getColumName()).append(",");
		}
		sql=sql.deleteCharAt(sql.length()-1);
		sql.append(" FROM alunni ")
				.append("ORDER BY nome DESC");
		PreparedStatement ps=null;
		ResultSet rs=null;
		ArrayList<HashMap<String,Object>> alunnoList=null;
		try {
			ps = getPreparedStatement(sql.toString());
			rs = executeQuery(ps);
			alunnoList = new ArrayList<HashMap<String,Object>>();
			if(rs!=null){
				try {
					while(rs.next()){
						HashMap<String,Object> alunno=new HashMap<String,Object>();
						for (enums.Alunno a : enums.Alunno.values()) {
							//alunno.put(a.getColumName(), Convert.convert(a, rs));
							alunno.put(a.getColumName(), rs.getObject(a.getColumName()));
						}
						
						alunnoList.add(
								alunno
//							new Alunno(rs.getString("USER_ID"),
//									rs.getString("NOME"),
//									rs.getString("COGNOME"),
//									Convert.convert(rs.getDate("DATA_NASCITA")),
//									Convert.convert(rs.getString("SESSO")),
//									rs.getString("CF"),
//									rs.getString("STATO_NASCITA"), 
//									rs.getString("COD_STATO_NASCITA"),  
//									rs.getString("COMUNE_NASCITA"), 
//									rs.getString("COD_COMUNE_NASCITA"), 
//									rs.getString("E_MAIL"),  
//									Convert.convert(rs.getDate("DATA_ISCRIZIONE")), 
//									new TitoloDiStudio(rs.getString("TITOLO_STUDIO")))
							);
					}
				} catch (SQLException e) {
					logger.error(metodo, "scorro risultato", e);
					throw e;
				} catch (Exception e) {
					logger.error(metodo, "COSTRUZIONE ALUNNO", e);
					throw e;
				}
			}
		} catch (SQLException e) {
			logger.error(metodo, "", e);
		}finally{
			close(ps,rs);
		}
		logger.end(metodo);
		return alunnoList;
	}
}