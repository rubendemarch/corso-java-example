/**
 * 
 */
package dbo.interfaces;


import java.util.HashMap;
import java.util.List;

import bussinessObject.Alunno;
import bussinessObject.interfaces.DescriptorsInterface;

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


	/**
	 * 
	 * @return restituisce tutti gli alunni presenti nella tabella ALUNNI
	 */
	public List<HashMap<String, Object>>dynamicReadAlunni(DescriptorsInterface di);

	/**
	 * 
	 * @return il numero di alunni inseriti
	 */
	public int dynamicInsertAlunno(DescriptorsInterface di, HashMap<String, Object> alunno);

	/**
	 * 
	 * @return il numero di alunni aggiornati
	 */
	public int dynamicUpdateAlunno(DescriptorsInterface di, HashMap<String, Object> alunno);
}
