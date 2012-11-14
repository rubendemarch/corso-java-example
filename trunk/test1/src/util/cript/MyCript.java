/**
 * 
 */
package util.cript;

import org.apache.commons.lang3.StringUtils;



/**
 * @author Dr
 * 
 */
public class MyCript {

	public static String encrypt(String input) {
		StringBuilder ret = new StringBuilder();
		for (char c :input.toCharArray()){
			ret.append(StringUtils.leftPad(((int)c)+"", 3, '0'));
		}
		return ret.toString();
	}

	public static String decrypt(String input) {
		int start=0;
		int end = 3;
		int cntCicli=input.length()/3;
		StringBuilder ret= new StringBuilder();
		for(int i=0;i<cntCicli;i++){
			if(i!=cntCicli-1){
			ret.append((char)Integer.parseInt(input.substring(start, end)));
			}else{
				ret.append((char)Integer.parseInt(input.substring(start)));
			}
			start+=3;
			end+=3;
		}
		return ret.toString();
	}

}