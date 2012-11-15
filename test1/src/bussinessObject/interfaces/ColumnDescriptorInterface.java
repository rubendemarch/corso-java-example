/**
 * 
 */
package bussinessObject.interfaces;

/**
 * @author Dr
 *
 */
public interface ColumnDescriptorInterface {
	/**
	 * @return the columName
	 */
	public String getColumName();
	/**
	 * @return the dbSize
	 */
	public int getDbSize();
	/**
	 * @return the fileSize
	 */
	public int getFileSize();
	/**
	 * @return the isLeftAlign
	 */
	public boolean isLeftAlign();
	/**
	 * @return the padChar
	 */
	public char getPadChar();
	/**
	 * @return the separetor
	 */
	public String getSeparetor();
	/**
	 * @return the pattern
	 */
	public String getPattern();
}
