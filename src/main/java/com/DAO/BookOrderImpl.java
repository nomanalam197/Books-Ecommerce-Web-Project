package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.entity.Book_Order;

public class BookOrderImpl implements BookOrderDAO{

	private Connection conn;
	
		
	public BookOrderImpl(Connection conn) {
		super();
		this.conn = conn;
	}

//	public int getOrderNo() {
//		int i = 1;
//		try {
//			String sqlString = "select * from book_order";
//			PreparedStatement psPreparedStatement = conn.prepareStatement(sqlString);
//			ResultSet rSet = psPreparedStatement.executeQuery();
//			
//			while(rSet.next()) {
//				i++;
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		
//		
//		return i;
//	}

	public boolean saveOrder(List<Book_Order> blist) {
		boolean f = false;
		
		try {
			
			String sqlString = "insert into book_order(order_id, user_name, email, address, phno, book_name, author, price, payment) values(?,?,?,?,?,?,?,?,?)";
			
			conn.setAutoCommit(false);
			PreparedStatement psPreparedStatement = conn.prepareStatement(sqlString);
			// adding multiple data ** row in table at a time usign addBatch
			for(Book_Order b: blist) {
				psPreparedStatement.setString(1, b.getOrderId());
				psPreparedStatement.setString(2, b.getUserName());
				psPreparedStatement.setString(3, b.getEmail());
				psPreparedStatement.setString(4, b.getFulladd());
				psPreparedStatement.setString(5, b.getPhno());
				psPreparedStatement.setString(6, b.getBookname());
				psPreparedStatement.setString(7, b.getAuthor());
				psPreparedStatement.setString(8, b.getPrice());
				psPreparedStatement.setString(9, b.getPaymentType());
				psPreparedStatement.addBatch();
				
			}
			
			int[] count = psPreparedStatement.executeBatch();
			conn.commit();
			f = true;
			conn.setAutoCommit(true);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return f;
	}

	public List<Book_Order> getBook(String email) {
		
		List<Book_Order> list = new ArrayList<Book_Order>();
		
		Book_Order o = null;
		try {
			
			String sqlString = "select * from book_order where email=?";
			
			PreparedStatement ps = conn.prepareStatement(sqlString);
			ps.setString(1, email);
			ResultSet rSet = ps.executeQuery();
			
			while(rSet.next()) {
				o = new Book_Order();
				o.setId(rSet.getInt(1));
				o.setOrderId(rSet.getString(2));
				o.setUserName(rSet.getString(3));
				o.setEmail(rSet.getString(4));
				o.setFulladd(rSet.getString(5));
				o.setPhno(rSet.getString(6));
				o.setBookname(rSet.getString(7));
				o.setAuthor(rSet.getString(8));
				o.setPrice(rSet.getString(9));
				o.setPaymentType(rSet.getString(10));
				
				list.add(o);
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return list;
	}
	
public List<Book_Order> getAllBook() {
		
		List<Book_Order> list = new ArrayList<Book_Order>();
		
		Book_Order o = null;
		try {
			
			String sqlString = "select * from book_order";
			
			PreparedStatement ps = conn.prepareStatement(sqlString);
			ResultSet rSet = ps.executeQuery();
			
			while(rSet.next()) {
				o = new Book_Order();
				o.setId(rSet.getInt(1));
				o.setOrderId(rSet.getString(2));
				o.setUserName(rSet.getString(3));
				o.setEmail(rSet.getString(4));
				o.setFulladd(rSet.getString(5));
				o.setPhno(rSet.getString(6));
				o.setBookname(rSet.getString(7));
				o.setAuthor(rSet.getString(8));
				o.setPrice(rSet.getString(9));
				o.setPaymentType(rSet.getString(10));
				
				list.add(o);
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return list;
	}
	
}
