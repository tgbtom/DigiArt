package com.fdmgroup.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import com.fdmgroup.model.Auction;
import com.fdmgroup.model.Bid;
import com.fdmgroup.model.Product;
import com.fdmgroup.model.User;

public class JDBCAuctionDao implements IAuctionDao{

	private JDBCProductDao jdbcProductDao = new JDBCProductDao();
	private JDBCUserDao jdbcUserDao = new JDBCUserDao();
	
	public static ArrayList<Auction> getAll() {
		ArrayList<Auction> auctions = new ArrayList<>();
		auctions.add(new Auction());
		auctions.add(new Auction());
		return auctions;
	}

	@Override
	public Auction update(Auction t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Auction create(Auction auction) {
		Connection conn = JDBCConnection.openConnection();
		
		String query = "INSERT INTO auctions (auction_id, start_time, end_time, min_bid_increase, bid_holder, product_id, user_id, current_price) "
				+ "VALUES (AUCTION_ID_SEQ.NEXTVAL, "
				+ "TO_DATE(?, 'yyyy-mm-dd HH24:mi:ss'), "
				+ "TO_DATE(?, 'yyyy-mm-dd HH24:mi:ss'), "
				+ "?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement ps = conn.prepareStatement(query, new String[] { "AUCTION_ID" });
			
			ps.setString(1, auction.getStart_time().truncatedTo(ChronoUnit.SECONDS).toString().replace('T', ' '));
			ps.setString(2, auction.getEnd_time().truncatedTo(ChronoUnit.SECONDS).toString().replace('T', ' '));
			ps.setDouble(3, auction.getMin_increase());
			ps.setInt(4, auction.getCurrent_bid().getBidder().getId());
			ps.setInt(5, auction.getProduct().getProduct_id());
			ps.setInt(6, auction.getCurrent_bid().getBidder().getId());
			ps.setDouble(7, auction.getCurrent_bid().getValue());
			
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			auction.setAuctionId(rs.getInt(1));
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return auction;
	}

	@Override
	public Auction findById(int id) {
		Connection conn = JDBCConnection.openConnection();
		String query = "SELECT start_time, end_time, min_bid_increase, bid_holder, product_id, user_id, current_price"
				+ "FROM auctions WHERE auction_id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				LocalDateTime startTime = Instant.ofEpochMilli(rs.getDate(1).getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
				LocalDateTime endTime = Instant.ofEpochMilli(rs.getDate(2).getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
				return new Auction(jdbcProductDao.findById(rs.getInt(5)), startTime, endTime, new Bid(rs.getDouble(7), jdbcUserDao.findById(rs.getInt(4))), rs.getDouble(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Auction> findAll(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Product> findMine(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateStatus(Product product, String newStatus) {
		Connection conn = JDBCConnection.openConnection();
		String query = "UPDATE inventory SET status = ? WHERE product_id = ? AND user_id = ?";
		
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, newStatus);
			ps.setInt(2, product.getProduct_id());
			ps.setInt(3, product.getOwner().getId());
			
			ps.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void testBid(Auction auction) {
		Connection conn = JDBCConnection.openConnection();
		
		double newAmount = auction.getCurrent_bid().getValue() + auction.getMin_increase();
		
		String query = "UPDATE auctions SET current_price = ?, bid_holder = ? WHERE auction_id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setDouble(1, newAmount);
			ps.setInt(2, 9);
			ps.setInt(3, auction.getAuctionId());
			
			ps.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
