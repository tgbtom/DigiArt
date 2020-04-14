package com.fdmgroup.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fdmgroup.controller.AuthenticationController;
import com.fdmgroup.dao.JPAUserDao;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		var username = request.getParameter("login-user");
		var password = request.getParameter("login-pass");
		
		AuthenticationController ac = new AuthenticationController();
		JPAUserDao jud = new JPAUserDao();
		
		RequestDispatcher dispatcher;
		if (ac.login(username, password)) {
			request.getSession().setAttribute("user", jud.findByUsername(username).get());
			request.getSession().setAttribute("message", "Logged in as " + username);
			dispatcher = request.getRequestDispatcher("/WEB-INF/dashboard.jsp");
			dispatcher.forward(request, response);
		}
		else {
			request.getSession().setAttribute("message", "Credentials Incorrect");
			dispatcher = request.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		}
	}

}
