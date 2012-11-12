/**
 * 
 */
package bussinessObject;

import java.util.ArrayList;
import java.util.List;

import util.MyLogger;

/**
 * @author Dr
 *  
 */
public class Aula {
	private MyLogger logger;
	private List<Persona>alunnoList;
	private List<Persona>docenteList;

	/**
	 * 
	 */
	public Aula() {
		logger=new MyLogger(this.getClass());
		final String metodo="costruttore";
		logger.start(metodo);
		logger.info(metodo, "Aula");
		alunnoList=new ArrayList<Persona>();
		docenteList=new ArrayList<Persona>();
		logger.end(metodo);
	}

	public void addNewAlunno(Alunno alunno){
		final String metodo="addNewAlunno";
		logger.start(metodo);
		alunnoList.add(alunno);
		logger.end(metodo);
	}

	public void addNewDocente(Docente docente){
		final String metodo="addNewDocente";
		logger.start(metodo);
		docenteList.add(docente);
		logger.end(metodo);
	}
}
