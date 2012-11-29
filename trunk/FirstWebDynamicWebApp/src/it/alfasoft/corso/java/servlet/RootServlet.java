package it.alfasoft.corso.java.servlet;

import it.alfasoft.corso.java.lang.MultipleResourceBundle;
import it.alfasoft.corso.java.util.constants.Session;
import it.alfasoft.corso.java.util.log.MyLogger;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.Servlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet implementation class RootServlet
 */
@WebServlet(description = "RootServlet", urlPatterns = { "/RootServlet" })
public class RootServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MyLogger log;
	//TODO connessione

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RootServlet() {
		super();
		log=new MyLogger(this.getClass());
		final String metodo="costruttore";
		log.start(metodo);
		
		//.....???
		
		log.end(metodo);
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO connessione <------ :-)
	}

	protected Locale getLocale(HttpServletRequest request){
		final String metodo="getLocale";
		log.start(metodo);
		String language = (String) getServletContext().getInitParameter("language");
		log.info(metodo, language);
		if(request.getSession().getAttribute(Session.LANG)==null){
			List<Locale>locs =
				Collections.list(request.getLocales());
			request
			.getSession()
				.setAttribute(
					Session.LANG,
					request.getLocale());
			for(Locale l: locs){
				if(language.contains(l.toString())){
					request
						.getSession()
							.setAttribute(
								Session.LANG,
								l);
					break;
				}
			}
		}
		//TODO VERIFICA SE DEVE CARICARE UNA LINGUA SALVATA NEL PROFILO
		log.end(metodo);
		return (Locale)request.getSession().getAttribute(Session.LANG);
	}

	protected ResourceBundle loadLanguage(HttpServletRequest request, List<String> resouces){
		return new MultipleResourceBundle(
			getLocale(request),
			resouces);
	}
}