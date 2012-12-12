package it.ecommerce.servlet;


import it.ecommerce.util.constants.Common;
import it.ecommerce.util.constants.Request;
import it.ecommerce.util.constants.Session;
import it.ecommerce.util.log.MyLogger;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * Servlet implementation class RootServlet
 */
public class RootServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MyLogger log;

	protected SqlSessionFactory sqlSessionFactory=null;

	protected String siteUrl;
	protected String realPath;
	protected String contextPath;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RootServlet() {
		super();
		log=new MyLogger(this.getClass());
		final String metodo="costruttore";
		log.start(metodo);
		
		String resource = "mybatis/config/mybatis-config.xml";
		InputStream inputStream=null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			log.fatal(metodo, "fallita SqlSessionFactoryBuilder", e);
		}
		try {
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			log.fatal(metodo, "fallita SqlSessionFactoryBuilder", e);
		}
		log.end(metodo);
	}

	
	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		final String metodo="init";
		log.start(metodo);
		super.init(config);
		siteUrl=(String) getServletContext().getInitParameter("siteUrl");
		realPath=getServletContext().getRealPath("/");
		contextPath=getServletContext().getContextPath();
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
		if(request.getSession().getAttribute(Session.LANG)==null){
			String language = 
				(String) getServletContext().getInitParameter("managedLanguages");
			log.info(metodo, language);
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

	protected void loadLanguage(HttpServletRequest request){
		Locale locale = getLocale(request);
		request.setAttribute(
			Request.ResourceBundle,
			ResourceBundle.getBundle(Common.RESOURCE, locale));
		request.setAttribute(
			Request.LOCALE,
			locale);
		
		String language =
			(String) getServletContext().getInitParameter("managedLanguages");
		
		List<Locale>managedLanguages=new ArrayList<Locale>();
		StringTokenizer tok =
			new StringTokenizer(language, " ");
		while(tok.hasMoreTokens()){
			managedLanguages.add(new Locale(tok.nextToken()));
		}
		
		request.setAttribute(
				Request.managedLanguages,
				managedLanguages);
		
	}
}