package com.fdmgroup.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Navigate
 */
@WebServlet("/Navigate")
public class Navigate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Navigate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("");
		
		if(request.getParameter("loc").equals("profile")) {
			dispatcher = request.getRequestDispatcher("/WEB-INF/profile.jsp");
		}
		else if(request.getParameter("loc").equals("products")) {
			dispatcher = request.getRequestDispatcher("/WEB-INF/products.jsp");
		}
		else if(request.getParameter("loc").equals("uploadproduct")) {
			dispatcher = request.getRequestDispatcher("/WEB-INF/uploadProduct.jsp");
		}
		else if(request.getParameter("loc").equals("auctions")) {
			dispatcher = request.getRequestDispatcher("/WEB-INF/auctions.jsp");
		}
		else if(request.getParameter("loc").equals("product")) {
			dispatcher = request.getRequestDispatcher("/WEB-INF/product.jsp");
		}
		
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
