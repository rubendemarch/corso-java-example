package it.eCommerce.servlet.common.brands;

import it.eCommerce.log.MyLogger;
import it.eCommerce.util.KeyGenerator;
import it.eCommerce.util.constants.Common;
import it.eCommerce.util.constants.Request;
import it.eCommerce.servlet.RootServlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.TransactionIsolationLevel;

/**
 * Servlet implementation class ManageBrand
 */
@WebServlet("/ManageBrand")
public class ManageBrands extends RootServlet {
	private static final long serialVersionUID = 1L;
	private MyLogger log;

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

		final String metodo = "process";
		log.start(metodo);
		loadLanguage(request);
		String action = request.getParameter(Common.ACTION);
		if ("Inserisci".equals(action)) {
			ResourceBundle rb = (ResourceBundle) request
					.getAttribute(Request.ResourceBundle);
			request.setAttribute("msg", insertNewBrand(request) ?
			// Carica il msg opportuno
			rb.getString("salvataggio.ok")
					: rb.getString("salvataggio.ko"));
		}
		request.getRequestDispatcher("jsp/manage/brands/insertBrand.jsp")
				.forward(request, response);

		log.end(metodo);

	}

	/*
	 * metodo che verifica se sn riuscito a inserire un nuovo brand, si utilizza
	 * x gestire la coda degli utenti che contemporaneamente vogliono inserire
	 * nuovi brand
	 */
	private synchronized boolean insertNewBrand(HttpServletRequest request) {

		final String metodo = "insertNewBrand";
		log.start(metodo);

		SqlSession sql = sqlSessionFactory
				.openSession(TransactionIsolationLevel.READ_COMMITTED);

		int rowsAffected = 0;
		HashMap<String, Object> brand = new HashMap<String, Object>();
		try {
			brand.put("ID_BRAND",
					KeyGenerator.KeyGen(sql, "ID_BRAND", "BRANDS", "B"));
			brand.put("IS_VISIBLE", true);
			brand.put("URL", request.getParameter("url"));// TODO
			brand.put("LOGO_URL", request.getParameter("logo_url"));// TODO
			brand.put("NAME", request.getParameter("name"));
			brand.put("IS_DELETED", false);
			rowsAffected = sql.insert("Brand.add", brand);
			sql.commit();// se è andato bene
		} catch (Exception e) {
			log.error(metodo, request.getSession().getId(), e);
			sql.rollback();// se è andato male
		}

		sql.close();// chiudo sessione
		log.end(metodo);
		return rowsAffected>0;
	}

}
