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
import com.fdmgroup.model.User;

/**
 * Servlet implementation class AdminLogin
 */
@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("admin-index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		var username = request.getParameter("admin-user");
		var password = request.getParameter("admin-pass");
		
		AuthenticationController ac = new AuthenticationController();
		JPAUserDao jud = new JPAUserDao();
		
		RequestDispatcher dispatcher;
		if (ac.login(username, password)) {
			User user = jud.findByUsername(username).get();
			if (user.getRole().equals("Admin")) {
				request.getSession().setAttribute("user", user);
				request.getSession().setAttribute("message", "Logged in as " + username);
				dispatcher = request.getRequestDispatcher("/WEB-INF/controlPanel.jsp");
				dispatcher.forward(request, response);
			}
			else {
				request.getSession().setAttribute("message", "Not an admin account");
				dispatcher = request.getRequestDispatcher("/admin-index.jsp");
				dispatcher.forward(request, response);
			}
		}
		else {
			request.getSession().setAttribute("message", "Credentials Incorrect");
			dispatcher = request.getRequestDispatcher("/admin-index.jsp");
			dispatcher.forward(request, response);
		}
	}

}
