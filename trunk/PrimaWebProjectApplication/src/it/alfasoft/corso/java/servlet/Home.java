package it.alfasoft.corso.java.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Home
 */
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
		loadLanguage(request, resouces);
		
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}
}
