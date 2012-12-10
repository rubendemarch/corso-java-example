package it.eCommerce.servlet.common.language;

import it.eCommerce.log.MyLogger;
import it.eCommerce.servlet.RootServlet;
import it.eCommerce.util.constants.Common;
import it.eCommerce.util.constants.Request;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;

/**
 * Servlet implementation class ManageLanguages
 */
public class ManageLanguages extends RootServlet {
	private static final long serialVersionUID = 1L;
	
	private MyLogger log;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageLanguages() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		process(request,response);
		
		log=new MyLogger(this.getClass());
		final String metodo="doGet";
		log.start(metodo);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		process(request,response);
	}

	protected void process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		loadLanguage(request);
		String action = request.getParameter(Common.ACTION);
		if(!StringUtils.isEmpty(action)){
			updateLanguages(request,response);
		}
		
		//3 carico la lista delle lingue gestite
		
		SqlSession sql = sqlSessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<HashMap<String,Object>>managedLanguages=
			(List<HashMap<String,Object>>)
				(List<?>)sql.selectList("language.list");
		sql.close();
		request.setAttribute(Request.MANAGED_LANGUAGES, managedLanguages);
		//4 carico la lista delle lingue da gestire
		
		List<Locale>toManage=new ArrayList<Locale>();
		boolean find;
		for (String locale: Locale.getISOLanguages()) {
			find=false;
			for (HashMap<String,Object>managedLanguage:managedLanguages) {
				if(locale.equals((String)managedLanguage.get("ID_LANGUAGE"))){
					find=true;
					break;
				}
			}
			if(!find){
				toManage.add(new Locale(locale));
			}
		}
		request.setAttribute(Request.TO_MANAGE_LANGUAGES, toManage);

		request
			.getRequestDispatcher("jsp/manage/language/language.jsp")
				.forward(request, response);
	}

	private synchronized void updateLanguages(
			HttpServletRequest request,
			HttpServletResponse response){
		SqlSession sql=sqlSessionFactory.openSession();
		//1 se hanno aggiunto una lingua la va a salvare nel db
		String id_language= request.getParameter("toManage");
		try{
			if(!"0000".equals(id_language)){
				HashMap<String, Object>toManage=new HashMap<String, Object>();
				toManage.put("ID_LANGUAGE", id_language);
				toManage.put("IS_VISIBLE", true);
				sql.insert("language.add", toManage);
			}
			
			//2 aggiorna lo stato di visibilità delle lingue gestite
			
			List<String> paramiters = Collections.list(request.getParameterNames());
			HashMap<String, Object>toManage=new HashMap<String, Object>();
						
			for(String paramiter : paramiters){
				if(paramiter.startsWith("managed")){
					request.getParameter(paramiter);
					toManage.put("ID_LANGUAGE", paramiter.substring(8));
					toManage.put("IS_VISIBLE", Boolean.parseBoolean(
							request.getParameter(paramiter)));
				}
			}
			
			sql.commit();
		}catch(Exception e){
			sql.rollback();
		}
		
		sql.close();
	}

}
