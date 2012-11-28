package it.alfasoft.corso.java.servlet;

import it.alfasoft.corso.java.util.constants.Session;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Home
 */
@WebServlet(description = "Home", urlPatterns = { "/Home" })
public class Home extends RootServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see RootServlet#RootServlet()
	 */
	public Home() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		process(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		process(request,response);
	}

	private void process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		List<String>resouces=new ArrayList<String>();
		resouces.add("test");
		resouces.add("testa");
		ResourceBundle rb = loadLanguage(request, resouces);
		request.setAttribute(Session.LANG, rb);
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}
}
