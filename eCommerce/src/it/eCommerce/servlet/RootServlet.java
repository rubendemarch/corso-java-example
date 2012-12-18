package it.eCommerce.servlet;


import it.eCommerce.util.constants.Common;
import it.eCommerce.util.constants.Request;
import it.eCommerce.util.constants.Session;
import it.eCommerce.util.log.MyLogger;

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
import javax.servlet.http.HttpServletResponse;

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

	protected String urlSite;
	protected String realPath;
	protected String contextPath;

	protected String commonAction;

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
		urlSite=(String) getServletContext().getInitParameter("urlSite");
		log.trace(metodo, urlSite);
		realPath=getServletContext().getRealPath("/");
		log.trace(metodo, realPath);
		contextPath=getServletContext().getContextPath();
		log.trace(metodo, contextPath);
		log.end(metodo);
	}


	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO 
	}

	private Locale getLocale(HttpServletRequest request){
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

	private void loadLanguage(HttpServletRequest request){
		Locale locale = getLocale(request);
		String servletName = this.getClass().getName();
		request.setAttribute(
				Common.SERVLET_NAME,
				servletName);

		ResourceBundle resourceBundle = ResourceBundle.getBundle(Common.RESOURCE, locale);
		request.setAttribute(
				Common.PAGE_TITLE,
				resourceBundle.getString(
					servletName));

		request.setAttribute(
			Request.ResourceBundle,
			resourceBundle);

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

	protected void initProcess(HttpServletRequest request){
		final String metodo="getLocale";
		log.start(metodo);
		loadLanguage(request);
		commonAction = request.getParameter(Common.COMMON_ACTION);
		request.setAttribute(Common.ACTION, commonAction);
		
		log.end(metodo);
	}

	protected void dispatch(
			HttpServletRequest request,
			HttpServletResponse response) throws	ServletException,
																			IOException{
		final String metodo="dispatch";
		log.start(metodo);
		
		request.getRequestDispatcher("jsp/common/root.jsp").forward(request, response);
		
		
		log.end(metodo);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		final String metodo="doGet";
		log.start(metodo);
		
		process(req, resp);
		
		log.end(metodo);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		final String metodo="doPost";
		log.start(metodo);
		
		process(req, resp);
		
		log.end(metodo);
	}

	protected void process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		final String metodo="process";
		log.start(metodo);
		
		initProcess(request);
		dispatch(request, response);
		
		log.end(metodo);
	}
}