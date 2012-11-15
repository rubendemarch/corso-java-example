/**
 * 
 */
package enums;


/**
 * @author Dr
 *
 */
public enum Alunno {
	USER_ID(						"USER_ID",						36, 	50,true,' ', ";",""),
	NOME(							"nome",								35, 	50,true,' ', ";",""),
	COGNOME(						"cognome",						35, 	50,true,' ', ";",""),
	DATA_NASCITA(				"data_nascita",					0, 		50,true,' ', ";","YYYYMMdd"),
	SESSO(							"sesso",								1, 		1,true,' ', ";",""),
	CF(								"cf",									16, 	16,true,' ', ";",""),
	STATO_NASCITA(			"STATO_NASCITA",				35, 	50,true,' ', ";",""),
	COD_STATO_NASCITA(	"COD_STATO_NASCITA",		4,		50,true,' ', ";",""),
	COD_COMUNE_NASCITA(	"COD_COMUNE_NASCITA",	4,		50,true,' ', ";",""),
	COMUNE_NASCITA(			"COMUNE_NASCITA",			35, 	50,true,' ', ";","");

	private final String columName;
	private final int dbSize;
	private final int fileSize;
	private final boolean isLeftAlign;
	private final char padChar;
	private final String separetor;
	private final String pattern;

	/**
	 * @param columName
	 * @param dbSize
	 * @param fileSize
	 * @param isLeftAlign
	 * @param padChar
	 * @param separetor
	 */
	private Alunno(String columName, int dbSize, int fileSize,
			boolean isLeftAlign, char padChar, String separetor, String pattern) {
		this.columName = columName;
		this.dbSize = dbSize;
		this.fileSize = fileSize;
		this.isLeftAlign = isLeftAlign;
		this.padChar = padChar;
		this.separetor = separetor;
		this.pattern=pattern;
	}

	/**
	 * @return the columName
	 */
	public String getColumName() {
		return columName;
	}

	/**
	 * @return the dbSize
	 */
	public int getDbSize() {
		return dbSize;
	}
	/**
	 * @return the fileSize
	 */
	public int getFileSize() {
		return fileSize;
	}
	/**
	 * @return the isLeftAlign
	 */
	public boolean isLeftAlign() {
		return isLeftAlign;
	}
	/**
	 * @return the padChar
	 */
	public char getPadChar() {
		return padChar;
	}
	/**
	 * @return the separetor
	 */
	public String getSeparetor() {
		return separetor;
	}

	/**
	 * @return the pattern
	 */
	public String getPattern() {
		return pattern;
	}
}