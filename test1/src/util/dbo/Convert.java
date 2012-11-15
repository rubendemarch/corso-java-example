/**
 * 
 */
package util.dbo;

import java.sql.Date;
import java.util.Calendar;

import bussinessObject.Sesso;

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

//	public static Object convert(Alunno a,ResultSet rs){
//		if(a.getClazz()==String.class){
//			try {
//				return rs.getString(a.getColumName());
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		if(a.getClazz()==Calendar.class){
//			try {
//				return convert(rs.getDate(a.getColumName()));
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		return null;
//	}
}