/**
 * 
 */
package dbo.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.MyLogger;
import util.dbo.Convert;
import bussinessObject.Alunno;
import bussinessObject.TitoloDiStudio;
import dbo.RootDbo;
import dbo.connection.Connessione;

/**
 * @author Dr
 *
 */
public class DboAlunni 
		extends RootDbo 
		implements dbo.interfaces.DboAlunni {
	private MyLogger logger;

	/**
	 * @param connessione
	 */
	public DboAlunni(Connessione connessione) {
		super(connessione);
		logger=new MyLogger(this.getClass());
		final String metodo="costruttore";
		logger.start(metodo);
		logger.end(metodo);
	}

	/* (non-Javadoc)
	 * @see dbo.interfaces.DboAlunniInterface#readAlunni()
	 */
	@Override
	public List<Alunno> readAlunni() {
		final String metodo="readAlunni";
		logger.start(metodo);
		StringBuilder sql =
				/*SELECT USER_ID,NOME,COGNOME,DATA_NASCITA,SESSO,CF,
STATO_NASCITA,COD_STATO_NASCITA,COMUNE_NASCITA,COD_COMUNE_NASCITA,
E_MAIL,DATA_ISCRIZIONE,TITOLO_STUDIO,TELEFONO
FROM ALUNNI order by nome desc*/
			new StringBuilder("SELECT ")
				.append("USER_ID,NOME,COGNOME,DATA_NASCITA,SESSO,CF,")
				.append("STATO_NASCITA,COD_STATO_NASCITA,COMUNE_NASCITA,COD_COMUNE_NASCITA,")
				.append("E_MAIL,DATA_ISCRIZIONE,TITOLO_STUDIO ")
				.append("FROM alunni ")
				.append("ORDER BY nome DESC");
		PreparedStatement ps=null;
		ResultSet rs=null;
		ArrayList<Alunno> alunnoList=null;
		try {
			ps = getPreparedStatement(sql.toString());
			rs = executeQuery(ps);
			alunnoList = new ArrayList<Alunno>();
			if(rs!=null){
				try {
					while(rs.next()){
						alunnoList.add(
							new Alunno(rs.getString("USER_ID"),
									rs.getString("NOME"),
									rs.getString("COGNOME"),
									Convert.convert(rs.getDate("DATA_NASCITA")),
									Convert.convert(rs.getString("SESSO")),
									rs.getString("CF"),
									rs.getString("STATO_NASCITA"), 
									rs.getString("COD_STATO_NASCITA"),  
									rs.getString("COMUNE_NASCITA"), 
									rs.getString("COD_COMUNE_NASCITA"), 
									rs.getString("E_MAIL"),  
									Convert.convert(rs.getDate("DATA_ISCRIZIONE")), 
									new TitoloDiStudio(rs.getString("TITOLO_STUDIO"))));
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