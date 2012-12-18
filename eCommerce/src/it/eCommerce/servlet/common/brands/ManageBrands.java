package it.eCommerce.servlet.common.brands;

import it.eCommerce.log.MyLogger;
import it.eCommerce.util.FileNameGenerator;
import it.eCommerce.util.KeyGenerator;
import it.eCommerce.util.constants.Common;
import it.eCommerce.util.constants.Request;
import it.eCommerce.servlet.RootServlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.imageio.spi.ImageWriterSpi;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.TransactionIsolationLevel;

/**
 * Servlet implementation class ManageBrand
 */

@WebServlet("/ManageBrand")
public class ManageBrands extends RootServlet {
	private static final long serialVersionUID = 1L;
	private MyLogger log;
	private String imagePath="images\\brand\\";
	private String imageUrl="/images/brand/";

	/**
	 * @see RootServlet#RootServlet()
	 */
	public ManageBrands() {
		super();
		log = new MyLogger(this.getClass());
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		final String metodo = "doGet";
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
		final String metodo = "doPost";
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
				brand.put("idColValue", request.getParameter("name"));
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
					if(filePart!=null){
						String ext = request.getParameter("ext");
						ext=ext.substring(ext.lastIndexOf('.'));
						String fileNameGen=FileNameGenerator.fileNameGen(ext);
						filePart.write(realPath + imagePath + fileNameGen);
						logoUrl = urlSite +contextPath + imageUrl + fileNameGen;
					}
				}
				request
					.setAttribute(
						"msg",
						(insertNewBrand(request,logoUrl))?
							rb.getString("salvataggio.ok"):
							rb.getString("salvataggio.ko"));
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
	
	
	/*
	 * metodo che verifica se sn riuscito a inserire un nuovo brand, si utilizza
	 * x gestire la coda degli utenti che contemporaneamente vogliono inserire
	 * nuovi brand
	 */
	private synchronized boolean insertNewBrand(
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
			brand.put("ID_BRAND", KeyGenerator.keyGen(sql, "ID_BRAND", "brands", "B"));
			brand.put("IS_VISIBLE", true);
			brand.put("URL", request.getParameter("url"));
			brand.put("LOGO_URL", logoUrl);
			brand.put("NAME", request.getParameter("name"));
			brand.put("IS_DELETED", false);
			rowsAffected=sql.insert("Brand.add", brand);
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
