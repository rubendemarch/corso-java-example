/**
 * 
 */
package util.dbo;

import java.sql.Date;
import java.util.Calendar;

import bussinessObject.Sesso;
import bussinessObject.TitoloDiStudio;

/**
 * @author Dr
 *
 */
public class Convert {

	public static Calendar convert(Date d){
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		return c;
	}

	public static Date convert(Calendar c){
		return new Date(c.getTimeInMillis());
	}

	public static Sesso convert(String s){
		return Sesso.valueOf(s);
	}
	public static String convert(Sesso s){
		return s.name();
	}
}