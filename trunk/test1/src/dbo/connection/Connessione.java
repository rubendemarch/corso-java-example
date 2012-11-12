/**
 * 
 */
package dbo.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import util.MyLogger;

/**
 * @author Dr
 *
 */
public class Connessione {
	private MyLogger logger;
	private Connection connection=null;
	private String url = "jdbc:oracle:thin:@localhost:1521";// url di connessione a oracle
	private String dbName = ":xe";
	private String driver = "oracle.jdbc.driver.OracleDriver";//driver per connessione a oracle
	private String userName = "APPLICAZIONIJAVA"; 
	private String password = "java";

	/**
	 * 
	 * @throws ReflectiveOperationException
	 * @throws SQLException
	 */
	public Connessione() throws ReflectiveOperationException, SQLException {
		logger=new MyLogger(this.getClass());
		final String metodo="costruttore";
		logger.start(metodo);
		
		try {
			Class.forName(driver).newInstance();
		} catch (InstantiationException 
				| IllegalAccessException
				| ClassNotFoundException e) {
			logger.fatal(metodo,
						"tentativo recupero driver",
						e);
			throw e;
		}
		
		try {
			connection=
				DriverManager.getConnection(url+dbName,
											userName,
											password);
		} catch (SQLException e) {
			logger.fatal(metodo,
					"tentativo connessione database",
					e);
			throw e;
		}
		logger.end(metodo);
	}

	@Deprecated
	public void closeConnection(){
		final String metodo="closeConnection";
		logger.start(metodo);
		if (connection!=null) {
			try {
				connection.close();
			} catch (SQLException e) {
				logger.warn(metodo, "tentativo chiusura connessione database", e);
			}
		}
		logger.end(metodo);
	}

	protected void finalize(){
		closeConnection();
	}

	/**
	 * @return the connection
	 */
	public Connection getConnection() {
		return connection;
	}
}
