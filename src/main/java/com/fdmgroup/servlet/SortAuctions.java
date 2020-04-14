package com.fdmgroup.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fdmgroup.dao.JPAAuctionDao;
import com.fdmgroup.model.Auction;

/**
 * Servlet implementation class SortAuctions
 */
@WebServlet("/SortAuctions")
public class SortAuctions extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SortAuctions() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JPAAuctionDao jad = new JPAAuctionDao();
		List<Auction> auctions = new ArrayList<>();
		String option = request.getParameter("opt");
		if (option.equals("timeasc")) {
			auctions = jad.findExpiringAuctions();
		}
		else if (option.equals("timedesc")) {
			auctions = jad.findExpiringAuctionsReverse();
		}
		else if(option.equals("name")) {
			auctions = jad.allAuctionsOrderedByProductName();
		}
		else if (option.equals("search")) {
			String val = request.getParameter("val");
			auctions = jad.findAuctionsContaining(val);
			}
		
		String output = "<div class='row'>";
		int counter = 0;
		for (Auction a : auctions) {
			
			String base64Image = new String(Base64.getEncoder().encode(a.getProduct().getImage()));
			
			if(counter % 3 != 0 || counter == 0) {
				output += "<div class='col-4'>"
						+ "<div class='card'>"
							+ "<div class='card-top'>"
								+ "<p>"+ a.getProduct().getName() +"</p>"
							+ "</div>"
							+ "<div class='card-content'>"
								+ "<img src=\"data:image/png;base64,"+ base64Image +"\" alt=\"Product Image\" class=\"product\" />"
							+ "</div>"
							+ "<div class='card-bottom'>"
								+ "<span class='time-left'>"+ jad.getTimeTillExpire(a) +"</span>"
								+ "<a href=\"Navigate?loc=auction&aid="+ a.getAuctionId() +"\">" 
								+		"<button class=\"bid-btn\">"
								+		"$"+ (jad.getHighestBid(a).getValue() + a.getMinIncrease())
								+		"</button>"
								+ "</a>"
							+ "</div>"
						+ "</div>"
						+ "</div>";
			}
			else {
				output += "</div><div class='row'>"
						+ "<div class='col-4'>"
						+ "<div class='card'>"
						+ "<div class='card-top'>"
							+ "<p>"+ a.getProduct().getName() +"</p>"
						+ "</div>"
						+ "<div class='card-content'>"
							+ "<img src=\"data:image/png;base64,"+ base64Image +"\" alt=\"Product Image\" class=\"product\" />"
						+ "</div>"
						+ "<div class='card-bottom'>"
							+ "<span class='time-left'>"+ jad.getTimeTillExpire(a) +"</span>"
							+ "<a href=\"Navigate?loc=auction&aid="+ a.getAuctionId() +"\">" 
							+		"<button class=\"bid-btn\">"
							+		"$"+ (jad.getHighestBid(a).getValue() + a.getMinIncrease())
							+		"</button>"
							+ "</a>"
						+ "</div>"
					+ "</div>"
					+ "</div>";
			}
			counter++;
		}
		response.getWriter().append(output);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
