package it.alfasoft.corso.java.servlet;

import it.alfasoft.corso.java.util.constants.Session;
import it.alfasoft.corso.java.util.log.MyLogger;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
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
	//connessione

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RootServlet() {
		super();
		log=new MyLogger(this.getClass());
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO connessione
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO connessione
	}

	protected Locale getLocale(HttpServletRequest req){
		if(req.getSession().getAttribute(Session.LANG)==null){
			req.getSession().setAttribute(Session.LANG, req.getLocale());
		}
		//TODO VERIFICA SE DEVE CARICARE UNA LINGUA SALVATA NEL PROFILO
		return (Locale)req.getSession().getAttribute(Session.LANG);
	}

	protected ResourceBundle loadLanguage(HttpServletRequest req, List<String> resouces){
		
		Locale loc =  getLocale(req);
		
		return ResourceBundle.getBundle("",loc);
	}
}