/**
 * 
 */
package util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.StringTokenizer;

import org.apache.commons.lang3.StringUtils;

import bussinessObject.interfaces.ColumnDescriptorInterface;
import bussinessObject.interfaces.DescriptorsInterface;
import enums.Alunno;

/**
 * @author Dr
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

	public static String formatAlunno(HashMap<String, Object>alunno, boolean isCsvFormat){
		StringBuilder ret = new StringBuilder();
		if(isCsvFormat){
			for (enums.Alunno a : enums.Alunno.values()) {
				ret.append(format(alunno.get(a.getColumName()),a))
					.append(a.getSeparetor());
			}
		}else{
			for (enums.Alunno a : enums.Alunno.values()) {
				ret.append(
					(a.isLeftAlign())?
						StringUtils.rightPad(
							format(alunno.get(a.getColumName()),a),
							a.getFileSize(),
							a.getPadChar()):
						StringUtils.leftPad(
							format(alunno.get(a.getColumName()),a),
							a.getFileSize(),
							a.getPadChar()));
			}
		}
		return ret.toString();
	}

	public static String format(Object val, Alunno a){
		if(val!=null){
			if(String.class==val.getClass()){
				return (String)val;
			}
			if(Calendar.class==val.getClass()){
				return new SimpleDateFormat(a.getPattern()).format(((Calendar)val).getTime());
			}
			if(Timestamp.class==val.getClass()){
				return new SimpleDateFormat(a.getPattern()).format(((Timestamp)val).getTime());
			}
			return val.getClass().getName();
		}
		return "";
	}

	public static String formatMap(	HashMap<String, Object>map,
													boolean isCsvFormat,
													DescriptorsInterface di){
		StringBuilder ret = new StringBuilder();
		if(isCsvFormat){
			for (ColumnDescriptorInterface cdi: di.getDescriptors()) {
				ret.append(format(map.get(cdi.getColumName()),cdi))
					.append(cdi.getSeparetor());
			}
		}else{
			for (ColumnDescriptorInterface cdi: di.getDescriptors()) {
				ret.append(
					(cdi.isLeftAlign())?
						StringUtils.rightPad(
							format(map.get(cdi.getColumName()),cdi),
							cdi.getFileSize(),
							cdi.getPadChar()):
						StringUtils.leftPad(
							format(map.get(cdi.getColumName()),cdi),
							cdi.getFileSize(),
							cdi.getPadChar()));
			}
		}
		return ret.toString();
	}
	public static String format(Object val, ColumnDescriptorInterface cdi){
		if(val!=null){
			if(String.class==val.getClass()){
				return (String)val;
			}
			if(Calendar.class==val.getClass()){
				return new SimpleDateFormat(cdi.getPattern()).format(((Calendar)val).getTime());
			}
			if(Timestamp.class==val.getClass()){
				return new SimpleDateFormat(cdi.getPattern()).format(((Timestamp)val).getTime());
			}
			return val.getClass().getName();
		}
		return "";
	}


}
