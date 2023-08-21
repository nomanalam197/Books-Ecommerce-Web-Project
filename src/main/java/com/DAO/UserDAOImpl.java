package com.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.entity.User;

public class UserDAOImpl implements UserDAO{
	
	private Connection conn;

	public UserDAOImpl(Connection conn) {
		super();
		this.conn = conn;
	}

	public boolean userRegister(User us) {
		// TODO Auto-generated method stub
		boolean f = false;
		try {
			String query = "insert into user(name,email,phno,password) values(?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, us.getName());
			ps.setString(2,us.getEmail());
			ps.setString(3, us.getPhno());
			ps.setString(4, us.getPassword());
			
			int i = ps.executeUpdate();
			if(i == 1) {
				f = true;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return f;
	}

	public User login(String email, String password) {
		
		User us = null;
		
		try {
			String query = "select * from user where email=? and password=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, email);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				us = new User();
				us.setId(rs.getInt(1));
				us.setName(rs.getString(2));
				us.setEmail(rs.getString(3));
				us.setPhno(rs.getString(4));
				us.setPassword(rs.getString(5));
				us.setAddress(rs.getString(6));
				us.setLandmark(rs.getString(7));
				us.setCity(rs.getString(8));
				us.setState(rs.getString(9));
				us.setPincode(rs.getString(10));
				
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return us;
	}

	public boolean checkPassword(int id,String ps) {

		boolean f = false;
		
			try {
				String sqlString = "select * from user where id=? and password=?";
				PreparedStatement pStatement = conn.prepareStatement(sqlString);
				pStatement.setInt(1, id);
				pStatement.setString(2, ps);
				
				ResultSet rs = pStatement.executeQuery();
				while(rs.next()) {
					f = true;
				}
				
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		
		return f;
	}

	public boolean updateProfile(User us) {
		
		boolean f = false;
		try {
//			System.out.println(us);
			String query = "update user set name=?,email=?,phno=? where id=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, us.getName());
			ps.setString(2,us.getEmail());
			ps.setString(3, us.getPhno());
			ps.setInt(4, us.getId());
//			ps.setInt(5, us.getId());
			int i = ps.executeUpdate();
			if(i == 1) {
				f = true;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return f;
	}

	public boolean checkUser(String em) {
		
		boolean f = true;
		try {
//			System.out.println(us);
			String query = "select * from user where email=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1,em);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				f= false;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return f;

	}
	
	
	
}
