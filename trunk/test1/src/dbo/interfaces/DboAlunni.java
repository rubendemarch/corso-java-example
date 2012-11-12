/**
 * 
 */
package dbo.interfaces;


import java.util.List;

import bussinessObject.Alunno;

/**
 * @author Dr
 *
 */
public interface DboAlunni {

	/**
	 * 
	 * @return restituisce tutti gli alunni presenti nella tabella ALUNNI
	 */
	public List<Alunno>readAlunni();
}
