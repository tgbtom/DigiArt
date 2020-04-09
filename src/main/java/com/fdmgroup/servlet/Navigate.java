package com.fdmgroup.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fdmgroup.dao.JPAAuctionDao;
import com.fdmgroup.dao.JPAProductDao;
import com.fdmgroup.dao.JPAUserDao;
import com.fdmgroup.model.Auction;
import com.fdmgroup.model.User;

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
		JPAProductDao jpd = new JPAProductDao();
		JPAAuctionDao jad = new JPAAuctionDao();
		JPAUserDao jud = new JPAUserDao();
		User user = (User) request.getSession().getAttribute("user");
		if(user == null) {
			response.sendRedirect("index.jsp");
		}
		else if (request.getParameter("loc").equals("logout")) {
			request.getSession().removeAttribute("user");
			response.sendRedirect("index.jsp");
		}
		else {
			//Resync user with db incase changes occurred
			user = jud.findById(user.getId());
			request.getSession().setAttribute("user", user);
			
			if(request.getParameter("loc").equals("profile")) {
				dispatcher = request.getRequestDispatcher("/WEB-INF/profile.jsp");
			}
			else if(request.getParameter("loc").equals("dashboard")) {
				dispatcher = request.getRequestDispatcher("/WEB-INF/dashboard.jsp");
			}
			else if(request.getParameter("loc").equals("products")) {
				dispatcher = request.getRequestDispatcher("/WEB-INF/products.jsp");
			}
			else if(request.getParameter("loc").equals("uploadproduct")) {
				dispatcher = request.getRequestDispatcher("/WEB-INF/uploadProduct.jsp");
			}
			else if(request.getParameter("loc").equals("auctions")) {
				List<Auction> auctionsToShow;
				if(request.getParameter("as").equals("mine")) {
					auctionsToShow = jad.findMine(user);
				}
				else {
					auctionsToShow = jad.allAuctionsOrderedByProductName();
				}
				request.setAttribute("auctions", auctionsToShow);
				dispatcher = request.getRequestDispatcher("/WEB-INF/auctions.jsp");
			}
			else if(request.getParameter("loc").equals("product")) {
				request.setAttribute("product", jpd.findById(Integer.parseInt(request.getParameter("pid"))));
				dispatcher = request.getRequestDispatcher("/WEB-INF/product.jsp");
			}
			else if(request.getParameter("loc").equals("auction")) {
				request.setAttribute("auction", jad.findById(Integer.parseInt(request.getParameter("aid"))));
				dispatcher = request.getRequestDispatcher("/WEB-INF/auction.jsp");
			}
			else if(request.getParameter("loc").equals("createAuction")) {
				request.setAttribute("product", jpd.findById(Integer.parseInt(request.getParameter("pid"))));
				dispatcher = request.getRequestDispatcher("/WEB-INF/createAuction.jsp");
			}
			dispatcher.forward(request, response);
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
