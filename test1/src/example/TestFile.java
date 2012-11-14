/**
 * 
 */
package example;

import java.io.File;
import java.io.IOException;

/**
 * @author Dr
 *
 */
public class TestFile {

	/**
	 * 
	 */
	public TestFile() {
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File f = new File("c:\\primoFile.txt");
		if(f.exists()){
			System.out.println("il file è già stato creato");
		}else{
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
