/**
 * 
 */
package example;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

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
		File f = new File("primoFile.txt");
		if(f.exists()){
			System.out.println("il file è già stato creato");
		}else{
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//ora provo a scriverci
		PrintStream printStream=null;
		try {
			printStream = new PrintStream(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(printStream!=null){
			for(int i=0;i<1500;i++){
				printStream.println("riga:"+i);
			}
			printStream.close();
		}
		
	}

}
