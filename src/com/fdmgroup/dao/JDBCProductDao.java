package com.fdmgroup.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fdmgroup.model.Product;
import com.fdmgroup.model.User;

public class JDBCProductDao implements IProductDao{

	private JDBCUserDao jdbcUserDao;
	
	public JDBCProductDao() {
		super();
		this.jdbcUserDao = new JDBCUserDao();
	}

	@Override
	public ArrayList<Product> findMine(User user) {
		ArrayList<Product> products = new ArrayList<>();
		
		Connection conn = JDBCConnection.openConnection();
		
		System.out.println("Finding products for user id: "+ user.getId());
		
		String query1 = "SELECT product_id, status FROM inventory WHERE user_id = ?";
		String query2 = "SELECT product_id, name, creator_id FROM products WHERE product_id = ?";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(query1);
			ps.setInt(1, user.getId());
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {

				ps = conn.prepareStatement(query2);
				ps.setInt(1, rs.getInt(1));
				
				ResultSet rs2 = ps.executeQuery();
				
				while(rs2.next()) {
					User owner = findOwner(rs2.getInt(1));
					User creator = jdbcUserDao.findById(rs2.getInt(3));
					products.add(new Product(rs2.getInt(1), rs2.getString(2), owner, creator));
				}
				
			}
			
		
			conn.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return products;
	}

	public User findOwner(int productId) {
		Connection conn = JDBCConnection.openConnection();
		
		User result = new User();
		
		String query = "SELECT user_id FROM inventory WHERE product_id = ? AND status IN ('Available', 'Auctioned', 'Bought')";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, productId);
			
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			int ownerId = rs.getInt(1);
			result = jdbcUserDao.findById(ownerId);
			
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Product create(Product product) {
		Connection conn = JDBCConnection.openConnection();
		
		String query = "INSERT INTO products (product_id, name, creator_id) VALUES (PRODUCT_ID_SEQ.NEXTVAL, ?, ?)";
		String query2 = "INSERT INTO inventory (user_id, product_id, status) VALUES (?, ?, 'Available')";
		
		PreparedStatement ps;
		Product createdProduct = new Product();
		createdProduct.setProduct_id(-1);
		try {
			ps = conn.prepareStatement(query, new String[]{"PRODUCT_ID"});
			ps.setString(1, product.getName());
			ps.setInt(2, product.getCreator().getId());
			
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int productId = rs.getInt(1);
			
			ps = conn.prepareStatement(query2);
			ps.setInt(1, product.getOwner().getId());
			ps.setInt(2, productId);
			ps.executeUpdate();
			
			conn.close();
			
			createdProduct = product;
			createdProduct.setProduct_id(productId);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return createdProduct;
	}

	@Override
	public Product findById(int id) {
		Connection conn = JDBCConnection.openConnection();
		Product productToReturn = new Product();
		String query = "SELECT name, creator_id FROM products WHERE product_id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				productToReturn.setProduct_id(id);
				productToReturn.setName(rs.getString(1));
				productToReturn.setCreator(jdbcUserDao.findById(rs.getInt(2)));
				productToReturn.setOwner(findOwner(id));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productToReturn;
	}

	@Override
	public List<Product> findAll(User user) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
