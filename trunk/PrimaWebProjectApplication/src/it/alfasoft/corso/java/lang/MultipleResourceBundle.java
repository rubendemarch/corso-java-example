/**
 * 
 */
package it.alfasoft.corso.java.lang;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Alfa305
 *
 */
public class MultipleResourceBundle extends ResourceBundle {
	//private List<String>bundles;
	//private Locale locale;
	private List<ResourceBundle>bundles;

	/**
	 * @param locale
	 * @param bundles
	 */
	public MultipleResourceBundle(
			Locale locale,
			List<String> bundles) {
		//this.locale = locale;
		this.bundles= new ArrayList<ResourceBundle>();
		for (String bundle : bundles) {
			this.bundles.add(
				ResourceBundle.getBundle(	bundle,
														locale));
		}
	}

	@Override
	public Enumeration<String> getKeys() {
		return null;
	}

	@Override
	protected Object handleGetObject(String key) {
		Object val=null;
		for (ResourceBundle bundle : bundles) {
			try {
				val=bundle.getObject(key);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(val!=null){
				break;
			}
		}
		return val;
	}

}