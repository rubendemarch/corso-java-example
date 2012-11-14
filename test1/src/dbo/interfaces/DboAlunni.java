/**
 * 
 */
package dbo.interfaces;


import java.util.HashMap;
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
	

	/**
	 * 
	 * @return restituisce tutti gli alunni presenti nella tabella ALUNNI
	 */
	public List<HashMap<String, Object>>dynamicReadAlunni();
}
