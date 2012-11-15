/**
 * 
 */
package bussinessObject;

import bussinessObject.interfaces.ColumnDescriptorInterface;

/**
 * @author Dr
 *
 */
public class ColumnDescriptor implements ColumnDescriptorInterface{
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
	 * @param pattern
	 */
	public ColumnDescriptor(String columName, int dbSize, int fileSize,
			boolean isLeftAlign, char padChar, String separetor, String pattern) {
		super();
		this.columName = columName;
		this.dbSize = dbSize;
		this.fileSize = fileSize;
		this.isLeftAlign = isLeftAlign;
		this.padChar = padChar;
		this.separetor = separetor;
		this.pattern = pattern;
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