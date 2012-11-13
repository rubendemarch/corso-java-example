/**
 * 
 */
package example;

import util.cript.MyCript;

/**
 * @author Dr
 * 
 */
public class TestCript {

	/**
	 * 
	 */
	public TestCript() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		MyCript cript = new MyCript();
		String password = "Url";
		String passwordEnc = cript.encrypt(password);
		String passwordDec = cript.decrypt(passwordEnc);

		System.out.println("Plain Text : " + password);
		System.out.println("Encrypted Text : " + passwordEnc);
		System.out.println("Decrypted Text : " + passwordDec);
	}

}
