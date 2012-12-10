/**
 * 
 */
package it.ecommerce.util;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;

/**
 * @author Dr
 *
 */
public class KeyGenerator {
	private static final int keyMaxLength=20;
	private static final char padChar='0';

	public static String keyGen(
			SqlSession sql,
			String colName,
			String tableName,
			String prefix){
		HashMap<String,String>parameterMap= new HashMap<String, String>();
		parameterMap.put("colName", colName);
		parameterMap.put("tableName", tableName);
		String lastInsertedKey = sql.selectOne("key.lastInsertedKey",
																	parameterMap);
		if(StringUtils.isEmpty(lastInsertedKey)){
			return StringUtils.rightPad(prefix, keyMaxLength,padChar);
		}
		int prefixLength=prefix.length();
		long l = Long.parseLong(lastInsertedKey.substring(prefixLength));
		l++;
		return prefix + StringUtils.leftPad(l+"", keyMaxLength-prefixLength, padChar);
	}
}
