/**
 * 
 */
package it.eCommerce.util.format.strings;


/**
 * @author Alfa305
 *
 */
public class StringFormat {
	/**
	 * @param metodo
	 * @param message
	 * @return il messaggio nel formato per il log
	 */
	public static String formatMessage(String metodo, String message){
		return new StringBuilder("[").append(metodo).append("][").append(message).append("]").toString();
	}
}
