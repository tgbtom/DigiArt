package com.fdmgroup.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fdmgroup.dao.JPAAuctionDao;
import com.fdmgroup.dao.JPAUserDao;
import com.fdmgroup.model.Auction;
import com.fdmgroup.model.Bid;
import com.fdmgroup.model.User;

/**
 * Servlet implementation class PlaceBid
 */
@WebServlet("/PlaceBid")
public class PlaceBid extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlaceBid() {
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
			JPAAuctionDao jad = new JPAAuctionDao();
			JPAUserDao jud = new JPAUserDao();
			//Resync user with db
			user = jud.findById(user.getId());
			double bidAmt = Double.parseDouble(request.getParameter("bid_amt"));
			Auction auction = jad.findById(Integer.parseInt(request.getParameter("auction_id")));
			
			//Ensure User is not the seller
			if (user.getId() != auction.getSeller().getId()) {
				if(user.getId() != jad.getHighestBid(auction).getBidder().getId()) {
					if(user.getWallet() >= bidAmt) {
						if(bidAmt >= jad.getHighestBid(auction).getValue() + auction.getMinIncrease()) {
							if(user.getLocked() == 0) {
								//Refund last highest bidder IF ITS NOT THE SELLER, lockout funds from this bidder
								Bid oldBid = jad.getHighestBid(auction);
								if(oldBid.getBidder().getId() != auction.getSeller().getId()) {
									oldBid.getBidder().setWallet(oldBid.getBidder().getWallet() + oldBid.getValue());
									jud.update(oldBid.getBidder());
								}

								user.setWallet(user.getWallet() - bidAmt);
								user = jud.update(user);
								
								auction.addBid(new Bid(bidAmt, user, auction));
								jad.update(auction);
								
								request.getSession().setAttribute("message", "Successfully bid $"+ bidAmt + " on auction #"+ auction.getAuctionId());
							}
							else {
								request.getSession().setAttribute("message", "You have currently been locked from bidding!");
							}
						}
						else {
							request.getSession().setAttribute("message", "Bid was not above the minimum threshold");
						}
					}
					else {
						request.getSession().setAttribute("message", "Not enough funds for bid");
					}
				}else {
					request.getSession().setAttribute("message", "You already have the highest bid");
				}
			}
			else {
				request.getSession().setAttribute("message", "You cannot bid on your own products");	
			}
			dispatcher = request.getRequestDispatcher("Navigate?loc=auctions&as=all");
			dispatcher.forward(request, response);
		}
	}

}
