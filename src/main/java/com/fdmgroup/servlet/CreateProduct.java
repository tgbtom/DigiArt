package com.fdmgroup.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fdmgroup.controller.ProductController;
import com.fdmgroup.model.ProductCategory;
import com.fdmgroup.model.User;

/**
 * Servlet implementation class CreateProduct
 */
@WebServlet("/CreateProduct")
@MultipartConfig
public class CreateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateProduct() {
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
		User loggedUser = (User) request.getSession().getAttribute("user");
		if(loggedUser == null) {
			response.sendRedirect("index.jsp");
		}
		else {
			String productName = request.getParameter("name");
			String description = request.getParameter("description");
			ProductCategory cat = ProductCategory.valueOf(request.getParameter("category"));
			byte[] image = request.getPart("image").getInputStream().readAllBytes();
			
			ProductController pc = new ProductController();
			pc.insertProduct(loggedUser, productName, image, description, cat);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("Navigate?loc=products");
			dispatcher.forward(request, response);
		}
	}
}
