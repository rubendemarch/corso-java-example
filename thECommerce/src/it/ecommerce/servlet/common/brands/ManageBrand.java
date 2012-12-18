package it.ecommerce.servlet.common.brands;

import it.ecommerce.servlet.RootServlet;
import it.ecommerce.util.FileNameGenerator;
import it.ecommerce.util.KeyGenerator;
import it.ecommerce.util.constants.Common;
import it.ecommerce.util.constants.Request;
import it.ecommerce.util.log.MyLogger;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.TransactionIsolationLevel;

/**
 * Servlet implementation class ManageBrand
 */
public class ManageBrand extends RootServlet {
	private static final long serialVersionUID = 1L;
	private MyLogger log;
	private String imagePath="images\\brand\\";
	private String imageUrl="/images/brand/";
	/**
	 * @see RootServlet#RootServlet()
	 */
	public ManageBrand() {
		super();
		log=new MyLogger(this.getClass());
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		final String metodo="doGet";
		log.start(metodo);
		process(request, response);
		log.end(metodo);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		final String metodo="doPost";
		log.start(metodo);
		process(request, response);
		log.end(metodo);
	}

	protected void process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		final String metodo="process";
		log.start(metodo);
		initProcess(request);
		
		if(Common.SAVE.equals(request.getParameter(Common.CUSTOM_ACTION))){
			ResourceBundle rb = (ResourceBundle)request
												.getAttribute(Request.ResourceBundle);
			HashMap<String, Object>brand=new HashMap<String, Object>();
			brand.put("colName","NAME");
			brand.put("tableName","BRANDS");
			brand.put("colValue", request.getParameter("name"));
			if(commonAction.equals(Common.MODIFY)){
				brand.put("idColName", "ID_BRAND");
				brand.put("idColValue", request.getParameter("idColumValue"));
			}
			SqlSession sql=sqlSessionFactory.openSession();
			int count=0;
			try {
				count = sql.selectOne("Common.count", brand);
			} catch (Exception e) {
				log.error(metodo, request.getSession().getId(), e);
			}finally{
				sql.close();
			}
			if(count>0){
				request
					.setAttribute(
						"msg",
						rb.getString("salvataggio.alreadyInsered"));
			}else{
				String logoUrl=request.getParameter("logoUrl");
				if("image".equals(request.getParameter("radioLogoUrl"))){
					Part filePart = request.getPart("logoImg");
					if(filePart!=null && filePart.getSize()>0){
						String ext = request.getParameter("ext");
						ext=ext.substring(ext.lastIndexOf('.'));
						String fileNameGen=FileNameGenerator.fileNameGen(ext);
						filePart.write(realPath + imagePath + fileNameGen);
						logoUrl = urlSite +contextPath + imageUrl + fileNameGen;
					}
				}
				boolean success=insertOrUpdateBrand(request,logoUrl);
				request
					.setAttribute(
						"msg",
						rb.getString(
							(success)?
								"salvataggio.ok":
								"salvataggio.ko"));
				String oldLogoUrl=request.getParameter("oldLogoUrl");
				if(success
					&& Common.MODIFY.equals(commonAction)
					&& !StringUtils.isEmpty(oldLogoUrl)
					&& oldLogoUrl.contains(urlSite)
					&& oldLogoUrl.contains("/")
					&& logoUrl.contains("/")
					&& !oldLogoUrl.substring(oldLogoUrl.lastIndexOf('/')).equals(
							logoUrl.substring(logoUrl.lastIndexOf('/')))
					){
					try{
						new File(realPath + imagePath + oldLogoUrl.substring(oldLogoUrl.lastIndexOf('/'))).delete();
					}catch(Exception e){
						log.error(metodo, "on delete file", e);
					}
				}
			}
		}
		if(Common.LIST.equals(commonAction)){
			SqlSession sql= sqlSessionFactory.openSession();
			request.setAttribute(
				"brandList",
				sql.selectList("Brand.list"));
			sql.close();
		}
		if(Common.DETAIL.equals(commonAction)||
			Common.MODIFY.equals(commonAction)){
			SqlSession sql= sqlSessionFactory.openSession();
			HashMap<String, Object>brand=new HashMap<String, Object>();
			brand.put("colName","ID_BRAND");
			brand.put("tableName","BRANDS");
			brand.put("colValue", request.getParameter("commonId"));
			request.setAttribute(
				"brand",
				sql.selectOne("Common.detail", brand));
			sql.close();
		}
		dispatch(request, response);
		log.end(metodo);
	}

	private synchronized boolean insertOrUpdateBrand(
			HttpServletRequest request,
			String logoUrl){
		final String metodo="insertNewBrand";
		log.start(metodo);
		SqlSession sql = sqlSessionFactory
									.openSession(
										TransactionIsolationLevel.READ_COMMITTED);
		int rowsAffected=0;
		try{
			HashMap<String, Object>brand=new HashMap<String, Object>();
			brand.put(
				"ID_BRAND",
				Common.MODIFY.equals(commonAction)?
					request.getParameter("idColumValue"):
					KeyGenerator.keyGen(sql, "ID_BRAND", "brands", "B"));
			brand.put("IS_VISIBLE", request.getParameter("isVisible"));
			brand.put("URL", request.getParameter("url"));
			brand.put("LOGO_URL", logoUrl);
			brand.put("NAME", request.getParameter("name"));
			brand.put("IS_DELETED", request.getParameter("isDeleted"));
			rowsAffected=
				Common.ADD.equals(commonAction)?
					sql.insert("Brand.add", brand):
					sql.update("Brand.update", brand);
			sql.commit();
		}catch(Exception e){
			log.error(metodo, request.getSession().getId(), e);
			sql.rollback();
		}finally{
			sql.close();
			log.end(metodo);
		}
		return rowsAffected>0;
	}
}
