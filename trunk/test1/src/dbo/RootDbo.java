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
import bussinessObject.ColumnDescriptor;
import bussinessObject.Descriptors;
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
	//Metodo per la visualizzaziome degli attributi nella tabella (SELECT)
	public List<HashMap<String,Object>> dynamicExecuteQuery(
			DescriptorsInterface di,
			String sql,
			List<Object>params) {
		final String metodo="dynamicExecuteQuery";
		logger.start(metodo);

		PreparedStatement ps=null;
		ResultSet rs=null;
		ArrayList<HashMap<String,Object>> res=null;
		try {
			ps = getPreparedStatement(sql);
			setParams(params, ps);
			rs = executeQuery(ps);
			res = new ArrayList<HashMap<String,Object>>();
			if(rs!=null){
				try {
					while(rs.next()){
						HashMap<String,Object> row=new HashMap<String,Object>();
						for (ColumnDescriptorInterface cdi: di.getDescriptors()) {
							row.put(cdi.getColumName(), rs.getObject(cdi.getColumName()));
						}
						res.add(row);
					}
				} catch (SQLException e) {
					logger.error(metodo, "scorro risultato", e);
					throw e;
				}
			}
		} catch (SQLException e) {
			logger.error(metodo, "", e);
		}finally{
			close(ps,rs);
		}
		logger.end(metodo);
		return res;
	}
	/**
	 * @param params
	 * @param ps
	 * @throws SQLException
	 */
	private void setParams(List<Object> params, PreparedStatement ps)
			throws SQLException {
		final String metodo="setParams";
		logger.start(metodo);

		if (params!=null) {
			for (int i = 0; i < params.size(); i++) {
				ps.setObject(i + 1, params.get(i));
			}
		}
		logger.end(metodo);
	}
	/**
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	//Metodo per l'aggiornamento della tabella (DELETE, UPDATE, INSERT, DROP)
	public int dynamicExecuteUpdate(
			String sql,
			List<Object>params) {
		final String metodo="dynamicExecuteUpdate";
		logger.start(metodo);
		PreparedStatement ps=null;
		try {
			ps = getPreparedStatement(sql);
			setParams(params, ps);
			return executeUpdate(ps);
		} catch (SQLException e) {
			logger.error(metodo, "", e);
		}finally{
			close(ps,null);
			logger.end(metodo);
		}
		return 0;
	}

	/**
	 * @param ps
	 * @return
	 * @throws SQLException
	 */
	private int executeUpdate(PreparedStatement ps) throws SQLException {
		return ps.executeUpdate();
	}

	public DescriptorsInterface readConfig(String configName) {
		final String metodo="readConfig";
		logger.start(metodo);
		StringBuilder sql =
			new StringBuilder("SELECT ")
				.append("COL_NAME,TBL_NAME,DB_SIZE,FILE_SIZE,IS_LEFT_ALIGN,PAD_CHAR,")
				.append("SEP,PATTERN FROM CONFIG_DESCRIPTOR ")
				.append("WHERE NOME_DESCRIPTOR=? ORDER BY INDEX_ORDER");
		PreparedStatement ps=null;
		ResultSet rs=null;
		DescriptorsInterface di=new Descriptors();
		try {
			ps = getPreparedStatement(sql.toString());
			ps.setObject(1, configName);
			rs = executeQuery(ps);
			if(rs!=null){
				try {
					while(rs.next()){
						di.addColumnDescriptor(
							new ColumnDescriptor(
								rs.getString("COL_NAME"),
								rs.getInt("DB_SIZE"),
								rs.getInt("FILE_SIZE"),
								rs.getBoolean("IS_LEFT_ALIGN"),
								((String)rs.getObject("PAD_CHAR"))
									.charAt(0),
								rs.getString("SEP"),
								rs.getString("PATTERN")));
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
		return di;
	}
}