/**
 * 
 */
package it.alfasoft.corso.java.lang;

import it.alfasoft.corso.java.util.log.MyLogger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Dr
 *
 */
public class MultipleResourceBundle extends ResourceBundle {
	private MyLogger log;
	private List<ResourceBundle>bundles;
	private Enumeration<String> keys;

	/**
	 * @param locale
	 * @param bundles
	 */
	public MultipleResourceBundle(
			Locale locale,
			List<String> bundles) {
		log=new MyLogger(this.getClass());
		this.bundles= new ArrayList<ResourceBundle>();
		for (String bundle : bundles) {
			this.bundles.add(
				ResourceBundle.getBundle(bundle,locale));
		}
		initKeys();
	}

	@Override
	public Enumeration<String> getKeys() {
		return keys;
	}

	private void initKeys() {
		final String metodo="initKeys";
		log.start(metodo);
		List<String>keys=new ArrayList<String>();
		List<String>bundleKeys;
		for (ResourceBundle bundle:bundles) {
			bundleKeys=Collections.list(bundle.getKeys());
			for (String bundleKey : bundleKeys) {
				keys.add(bundleKey);
			}
		}
		this.keys=Collections.enumeration(keys);
		log.end(metodo);
	}

	@Override
	protected Object handleGetObject(String key) {
		final String metodo="handleGetObject";
		Object val=null;
		for (ResourceBundle bundle : bundles) {
			try {
				val=bundle.getObject(key);
			} catch (Exception e) {
				log.trace(metodo, key + " not found");
			}
			if(val!=null){
				break;
			}
		}
		return val;
	}
}