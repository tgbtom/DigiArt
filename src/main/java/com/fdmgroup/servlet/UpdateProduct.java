package com.fdmgroup.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fdmgroup.dao.JPAProductDao;
import com.fdmgroup.model.Product;
import com.fdmgroup.model.ProductCategory;
import com.fdmgroup.model.User;

/**
 * Servlet implementation class UpdateProduct
 */
@WebServlet("/UpdateProduct")
public class UpdateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProduct() {
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
		User user = (User) request.getSession().getAttribute("user");
		if(user == null) {
			response.sendRedirect("index.jsp");
		}
		else {
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			ProductCategory category = ProductCategory.valueOf(request.getParameter("category"));
			String description = request.getParameter("description");
			JPAProductDao jpd = new JPAProductDao();
			Product product = jpd.findById(id);
			product.setName(name);
			product.setCategory(category);
			product.setDescription(description);
			jpd.update(product);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("Navigate?loc=products");
		dispatcher.forward(request, response);
	}

}
