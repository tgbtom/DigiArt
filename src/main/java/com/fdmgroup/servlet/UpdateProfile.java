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
import com.fdmgroup.model.Password;
import com.fdmgroup.model.User;

/**
 * Servlet implementation class UpdateProfile
 */
@WebServlet("/UpdateProfile")
public class UpdateProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String task = request.getParameter("task");
		JPAUserDao jud = new JPAUserDao();
		User user = (User) request.getSession().getAttribute("user");
		if(task.equals("email")) {
			String value = (String) request.getParameter("newValue");
			user.setEmail(value);
			jud.update(user);
			response.sendRedirect("Navigate?loc=profile");
		} else if (task.equals("password")) {
			String value = (String) request.getParameter("newValue");
			AuthenticationController ac = new AuthenticationController();
			Password newPass = ac.hashPassword((String) value);
			user.setPassword(newPass.getHashedPass());
			user.setSalt(newPass.getSalt());
			jud.update(user);
			response.sendRedirect("Navigate?loc=profile");
		}
		else if (task.equals("deposit")) {
			double value = Double.parseDouble(request.getParameter("newValue"));
			jud.deposit(user, value);
			response.sendRedirect("Navigate?loc=profile");
		}
		else if (task.equals("withdraw")) {
			double value = Double.parseDouble(request.getParameter("newValue"));
			if (jud.withdraw(user, value)) {
				response.sendRedirect("Navigate?loc=profile");
			}
			else {
				request.getSession().setAttribute("message", "Not enough funds to make the withdrawal");
				RequestDispatcher dispatcher = request.getRequestDispatcher("Navigate?loc=profile");
				dispatcher.forward(request, response);
			}
		}
	}

}
