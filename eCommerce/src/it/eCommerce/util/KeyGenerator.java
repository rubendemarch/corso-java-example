package it.eCommerce.util;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;



public class KeyGenerator {
	private static int keyMaxLenght=20;
	private static char padChar = '0';
	public static String KeyGen(SqlSession sql, String colName, String tableNmae, String prefix){
		
		HashMap<String, String> paramiterMap = new HashMap<String,String>();
		
		String lastInsertedKey= sql.selectOne("brand.lastInsertedKey",
				paramiterMap);
		
		
		paramiterMap.put("colNmae",colName);
		paramiterMap.put("tableNmae",tableNmae);
		
		//generazione della prima chiavi
		if (StringUtils.isEmpty(lastInsertedKey)){
			//inserisco a destra un tot di zeri fino a raggiungere il numero di caratteri stabiliti

			return StringUtils.rightPad(prefix, keyMaxLenght, padChar);
		}
		
		
		int prefixLenght = prefix.length(); 
		long l = Long.parseLong(lastInsertedKey.substring(prefix.length()));
		l++;
		
		// l+"" trasforma un long in stringa (se non ha una virgola!!!!)
		return prefix + StringUtils.leftPad(l+"", keyMaxLenght-prefixLenght, padChar);
	}

}
