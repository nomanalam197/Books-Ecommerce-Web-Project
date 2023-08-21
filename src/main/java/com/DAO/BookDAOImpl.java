package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import com.entity.BookDtls;

public class BookDAOImpl implements BookDAO{
	
	private Connection conn;
	
	public BookDAOImpl(Connection conn) {
		super();
		this.conn = conn;
	}


	public boolean addBooks(BookDtls b) {
		boolean f = false;
		try {
			String queryString = "insert into book_dtls(bookname, author, price, bookCategory, status, photo, email) values(?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(queryString);
			ps.setString(1, b.getBookName());
			ps.setString(2, b.getAuthor());
			ps.setString(3, b.getPrice());
			ps.setString(4, b.getBookCategory());
			ps.setString(5,  b.getStatus());
			ps.setString(6, b.getPhotoName());
			ps.setString(7,  b.getEmail());
			
			int i = ps.executeUpdate();
			if(i == 1) {
				f = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return f;
	}


	public List<BookDtls> getAllBooks() {
		
		List<BookDtls> list  = new ArrayList<BookDtls>();
		
		BookDtls bookDtls = null;
		
		try {
			String queryString = "select * from book_dtls";
			
			PreparedStatement pStatement = conn.prepareStatement(queryString);
			ResultSet rSet = pStatement.executeQuery();
			
			while(rSet.next()) {
				bookDtls = new BookDtls();
				bookDtls.setBookId(rSet.getInt(1));
				bookDtls.setBookName(rSet.getString(2));
				bookDtls.setAuthor(rSet.getString(3));
				bookDtls.setPrice(rSet.getString(4));
				bookDtls.setBookCategory(rSet.getString(5));
				bookDtls.setStatus(rSet.getString(6));
				bookDtls.setPhotoName(rSet.getString(7));
				bookDtls.setEmail(rSet.getString(8));
				list.add(bookDtls);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return list;
	}


	public BookDtls getBookById(int id) {
		BookDtls bookDtls = null;
		try {
			String queryString = "select * from book_dtls where bookId=?";
			PreparedStatement pStatement = conn.prepareStatement(queryString);
			pStatement.setInt(1, id);
			ResultSet rSet = pStatement.executeQuery();
			
			while(rSet.next()) {
				bookDtls = new BookDtls();
				bookDtls.setBookId(rSet.getInt(1));
				bookDtls.setBookName(rSet.getString(2));
				bookDtls.setAuthor(rSet.getString(3));
				bookDtls.setPrice(rSet.getString(4));
				bookDtls.setBookCategory(rSet.getString(5));
				bookDtls.setStatus(rSet.getString(6));
				bookDtls.setPhotoName(rSet.getString(7));
				bookDtls.setEmail(rSet.getString(8));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bookDtls;
	}


	public boolean updateEditBooks(BookDtls b) {
		
		boolean f = false;
		
		try {
			String queryString = "update book_dtls set bookName=?, author=?, price=?, status=? where bookId=?";
			PreparedStatement pStatement = conn.prepareStatement(queryString);
			pStatement.setString(1, b.getBookName());
			pStatement.setString(2, b.getAuthor());
			pStatement.setString(3, b.getPrice());
			pStatement.setString(4, b.getStatus());
			pStatement.setInt(5, b.getBookId());
			
			int i = pStatement.executeUpdate();
			
			if(i == 1) {
				f = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return f;
		
	}


	public boolean deleteteBooks(int id) {
		
		boolean f = false;
		
		try {
			String queryString = "delete from book_dtls where bookId=?";
			PreparedStatement psPreparedStatement = conn.prepareStatement(queryString);
			psPreparedStatement.setInt(1, id);
			int i = psPreparedStatement.executeUpdate();
			
			if(i == 1) {
				f = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return f;
		
	}


	public List<BookDtls> getNewBookDtls() {
		
		List<BookDtls> list = new ArrayList<BookDtls>();
		BookDtls bookDtls = null;
		try {
			String sqlString = "select * from book_dtls where bookCategory=? and status=? order by bookId DESC ";
			PreparedStatement pStatement = conn.prepareStatement(sqlString);
			pStatement.setString(1, "New");
			pStatement.setString(2, "Active");
			
			ResultSet rSet = pStatement.executeQuery();
			int i = 1;
			while(rSet.next() && i<=4) {
				bookDtls = new BookDtls();
				bookDtls.setBookId(rSet.getInt(1));
				bookDtls.setBookName(rSet.getString(2));
				bookDtls.setAuthor(rSet.getString(3));
				bookDtls.setPrice(rSet.getString(4));
				bookDtls.setBookCategory(rSet.getString(5));
				bookDtls.setStatus(rSet.getString(6));
				bookDtls.setPhotoName(rSet.getString(7));
				bookDtls.setEmail(rSet.getString(8));
				list.add(bookDtls);
				i++;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
		
	}


	public List<BookDtls> getRecentBookDtls() {
		
		List<BookDtls> list = new ArrayList<BookDtls>();
		BookDtls bookDtls = null;
		try {
			String sqlString = "select * from book_dtls where status=? order by bookId DESC ";
			PreparedStatement pStatement = conn.prepareStatement(sqlString);
			pStatement.setString(1, "Active");
			
			ResultSet rSet = pStatement.executeQuery();
			int i = 1;
			while(rSet.next() && i<=4) {
				bookDtls = new BookDtls();
				bookDtls.setBookId(rSet.getInt(1));
				bookDtls.setBookName(rSet.getString(2));
				bookDtls.setAuthor(rSet.getString(3));
				bookDtls.setPrice(rSet.getString(4));
				bookDtls.setBookCategory(rSet.getString(5));
				bookDtls.setStatus(rSet.getString(6));
				bookDtls.setPhotoName(rSet.getString(7));
				bookDtls.setEmail(rSet.getString(8));
				list.add(bookDtls);
				i++;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
		
	}
	
public List<BookDtls> getOldBookDtls() {
		
		List<BookDtls> list = new ArrayList<BookDtls>();
		BookDtls bookDtls = null;
		try {
			String sqlString = "select * from book_dtls where bookCategory=? and status=? order by bookId DESC ";
			PreparedStatement pStatement = conn.prepareStatement(sqlString);
			pStatement.setString(1, "Old");
			pStatement.setString(2, "Active");
			
			ResultSet rSet = pStatement.executeQuery();
			int i = 1;
			while(rSet.next() && i<=4) {
				bookDtls = new BookDtls();
				bookDtls.setBookId(rSet.getInt(1));
				bookDtls.setBookName(rSet.getString(2));
				bookDtls.setAuthor(rSet.getString(3));
				bookDtls.setPrice(rSet.getString(4));
				bookDtls.setBookCategory(rSet.getString(5));
				bookDtls.setStatus(rSet.getString(6));
				bookDtls.setPhotoName(rSet.getString(7));
				bookDtls.setEmail(rSet.getString(8));
				list.add(bookDtls);
				i++;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
		
	}


	public List<BookDtls> getAllRecentBooks() {

		List<BookDtls> list = new ArrayList<BookDtls>();
		BookDtls bookDtls = null;
		try {
			String sqlString = "select * from book_dtls where status=? order by bookId DESC ";
			PreparedStatement pStatement = conn.prepareStatement(sqlString);
			pStatement.setString(1, "Active");
			
			ResultSet rSet = pStatement.executeQuery();
			while(rSet.next()) {
				bookDtls = new BookDtls();
				bookDtls.setBookId(rSet.getInt(1));
				bookDtls.setBookName(rSet.getString(2));
				bookDtls.setAuthor(rSet.getString(3));
				bookDtls.setPrice(rSet.getString(4));
				bookDtls.setBookCategory(rSet.getString(5));
				bookDtls.setStatus(rSet.getString(6));
				bookDtls.setPhotoName(rSet.getString(7));
				bookDtls.setEmail(rSet.getString(8));
				list.add(bookDtls);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
		
	}
	
	
	public List<BookDtls> getAllNewBooks() {

		List<BookDtls> list = new ArrayList<BookDtls>();
		BookDtls bookDtls = null;
		try {
			String sqlString = "select * from book_dtls where bookCategory=? and status=? order by bookId DESC ";
			PreparedStatement pStatement = conn.prepareStatement(sqlString);
			pStatement.setString(1, "New");
			pStatement.setString(2, "Active");
			
			ResultSet rSet = pStatement.executeQuery();
			while(rSet.next()) {
				bookDtls = new BookDtls();
				bookDtls.setBookId(rSet.getInt(1));
				bookDtls.setBookName(rSet.getString(2));
				bookDtls.setAuthor(rSet.getString(3));
				bookDtls.setPrice(rSet.getString(4));
				bookDtls.setBookCategory(rSet.getString(5));
				bookDtls.setStatus(rSet.getString(6));
				bookDtls.setPhotoName(rSet.getString(7));
				bookDtls.setEmail(rSet.getString(8));
				list.add(bookDtls);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
		
	}
	
	
	public List<BookDtls> getAllOldBooks() {
		
		List<BookDtls> list = new ArrayList<BookDtls>();
		BookDtls bookDtls = null;
		try {
			String sqlString = "select * from book_dtls where bookCategory=? and status=? order by bookId DESC ";
			PreparedStatement pStatement = conn.prepareStatement(sqlString);
			pStatement.setString(1, "Old");
			pStatement.setString(2, "Active");
			
			ResultSet rSet = pStatement.executeQuery();
			while(rSet.next()) {
				bookDtls = new BookDtls();
				bookDtls.setBookId(rSet.getInt(1));
				bookDtls.setBookName(rSet.getString(2));
				bookDtls.setAuthor(rSet.getString(3));
				bookDtls.setPrice(rSet.getString(4));
				bookDtls.setBookCategory(rSet.getString(5));
				bookDtls.setStatus(rSet.getString(6));
				bookDtls.setPhotoName(rSet.getString(7));
				bookDtls.setEmail(rSet.getString(8));
				list.add(bookDtls);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}


	public List<BookDtls> getBookByOld(String email, String cate) {
		
		List<BookDtls> list = new ArrayList<BookDtls>();
		BookDtls bookDtls  =null;
		
		try {
			
			String sqlString = "select * from book_dtls where bookCategory=? and email=?";
			PreparedStatement ps = conn.prepareStatement(sqlString);
			ps.setString(1, cate);
			ps.setString(2, email);
			
			ResultSet rSet = ps.executeQuery();
			
			while(rSet.next()) {
				bookDtls = new BookDtls();
				bookDtls.setBookId(rSet.getInt(1));
				bookDtls.setBookName(rSet.getString(2));
				bookDtls.setAuthor(rSet.getString(3));
				bookDtls.setPrice(rSet.getString(4));
				bookDtls.setBookCategory(rSet.getString(5));
				bookDtls.setStatus(rSet.getString(6));
				bookDtls.setPhotoName(rSet.getString(7));
				bookDtls.setEmail(rSet.getString(8));
				list.add(bookDtls);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
		
	}


	public boolean oldBookDelete(String email, String cate, int id) {
		
		boolean f = false;
		try {
			String sqlString = "delete from book_dtls where bookCategory=? and email=? and bookId=?";
			PreparedStatement ps = conn.prepareStatement(sqlString);
			ps.setString(1, cate);
			ps.setString(2, email);
			ps.setInt(3, id);
			
			int i = ps.executeUpdate();
			
			if(i == 1) {
				f = true;
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		
		
		return f;
	}


	public List<BookDtls> getBookBySearch(String ch) {

		List<BookDtls> list = new ArrayList<BookDtls>();
		BookDtls bookDtls  =null;
		
		try {
//			System.out.println(ch);
			String sqlString = "select * from book_dtls where bookname like ? or author like ? or bookCategory like ? and status=?";
			PreparedStatement ps = conn.prepareStatement(sqlString);
			ps.setString(1, "%"+ch+"%");
			ps.setString(2, "%"+ch+"%");
			ps.setString(3, "%"+ch+"%");
			ps.setString(4, "Active");
			
			ResultSet rSet = ps.executeQuery();
			
			while(rSet.next()) {
				bookDtls = new BookDtls();
				bookDtls.setBookId(rSet.getInt(1));
				bookDtls.setBookName(rSet.getString(2));
				bookDtls.setAuthor(rSet.getString(3));
				bookDtls.setPrice(rSet.getString(4));
				bookDtls.setBookCategory(rSet.getString(5));
				bookDtls.setStatus(rSet.getString(6));
				bookDtls.setPhotoName(rSet.getString(7));
				bookDtls.setEmail(rSet.getString(8));
				list.add(bookDtls);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
		
	}
	
	
	
	
}