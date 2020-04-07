package com.fdmgroup.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fdmgroup.controller.AuctionController;
import com.fdmgroup.dao.JPAProductDao;
import com.fdmgroup.model.Auction;
import com.fdmgroup.model.Bid;
import com.fdmgroup.model.User;

/**
 * Servlet implementation class CreateAuction
 */
@WebServlet("/CreateAuction")
public class CreateAuction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAuction() {
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
		int productId = Integer.parseInt(request.getParameter("product-id"));
		Double startPrice = Double.parseDouble(request.getParameter("start-price"));
		Double minIncrease = Double.parseDouble(request.getParameter("min-increase"));
		Date start = new Date();
		Date end = new Date();
		if (request.getParameterValues("change-start") != null) {
			//Default start time
		}
		else {
			try {
				start = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(request.getParameter("start-date") +" "+ request.getParameter("start-time"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		try {
			String endTime = (request.getParameter("end-time") == null ? "00:00" : request.getParameter("end-time"));
			end = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(request.getParameter("end-date") +" "+ endTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JPAProductDao jpd = new JPAProductDao();
		Auction auction = new Auction(jpd.findById(productId), start, end, minIncrease, (User) request.getSession().getAttribute("user"));
		auction.addBid(new Bid(startPrice - minIncrease, (User) request.getSession().getAttribute("user"), auction));
		
		AuctionController ac = new AuctionController();
		ac.createAuction(auction, (User) request.getSession().getAttribute("user"));
		
	}

}
