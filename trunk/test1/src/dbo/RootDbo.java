/**
 * 
 */
package dbo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.MyLogger;
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
}