/**
 * 
 */
package util.map;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;

import bussinessObject.interfaces.ColumnDescriptorInterface;
import bussinessObject.interfaces.DescriptorsInterface;

/**
 * @author Dr
 * 
 */
public class Convert {

	public static HashMap<String, Object> formatMap(	String line,
																				boolean isCsvFormat,
																				DescriptorsInterface di) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		int end=0;
		String l=line;
		String appo=null;
		
		for (ColumnDescriptorInterface cdi : di.getDescriptors()) {
			if (isCsvFormat) {
				appo=l.substring(0,l.indexOf(cdi.getSeparetor()));
				l=l.substring(l.indexOf(cdi.getSeparetor())+1);
				if (StringUtils.isEmpty(cdi.getPattern())) {
					map.put(cdi.getColumName(), appo);
				} else {
					try {
						map.put(cdi.getColumName(),
							new Timestamp(
								new SimpleDateFormat(cdi.getPattern())
									.parse(appo).getTime()));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} else {
				appo=l.substring(0,cdi.getFileSize());
				l=l.substring(cdi.getFileSize());
				
				appo=cdi.isLeftAlign()?
						StringUtils.stripEnd(appo,cdi.getPadChar()+""):
						StringUtils.stripStart(appo,cdi.getPadChar()+"");

				if (StringUtils.isEmpty(cdi.getPattern())) {
					map.put(cdi.getColumName(), appo);
				} else {
					try {
						map.put(cdi.getColumName(),
							new Timestamp(
								new SimpleDateFormat(cdi.getPattern())
									.parse(appo).getTime()));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

		return map;
	}

}
