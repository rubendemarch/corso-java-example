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

	public List<HashMap<String,Object>> dynamicRead(
			DescriptorsInterface di,
			String tables,
			String whereCond,
			String orderByCond,
			String groupByCond) {
		final String metodo="dynamicRead";
		logger.start(metodo);
		StringBuilder sql = new StringBuilder("SELECT ");
		for (ColumnDescriptorInterface cdi: di.getDescriptors()) {
			sql.append(cdi.getColumName()).append(",");
		}
		sql=sql.deleteCharAt(sql.length()-1);
		sql.append(" FROM ")
			.append(tables)
			.append(whereCond)
			.append(orderByCond)
			.append(groupByCond);
		PreparedStatement ps=null;
		ResultSet rs=null;
		ArrayList<HashMap<String,Object>> res=null;
		try {
			ps = getPreparedStatement(sql.toString());
			
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
}