package com.user.servlet;

import java.io.IOException;

import com.DAO.BookDAOImpl;
import com.DAO.cartDAOimpl;
import com.DB.DBConnect;
import com.entity.BookDtls;
import com.entity.Cart;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cart")
public class cartServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			int bid = Integer.parseInt(req.getParameter("bid"));
			int uid = Integer.parseInt(req.getParameter("uid"));
			
			BookDAOImpl daoImpl = new BookDAOImpl(DBConnect.getConn());
			BookDtls b = daoImpl.getBookById(bid);
			
			Cart cart = new Cart();
			cart.setBid(bid);
			cart.setUserId(uid);
			cart.setBookName(b.getBookName());
			cart.setAuthor(b.getAuthor());
			cart.setPrice(Double.parseDouble(b.getPrice()));
			cart.setTotalPrice(Double.parseDouble(b.getPrice()));
			System.out.println(cart);;
			cartDAOimpl dao2 = new cartDAOimpl(DBConnect.getConn());
			boolean f = dao2.addcart(cart);
			
			HttpSession session = req.getSession();
			
			if(f) {
				session.setAttribute("addCart", "Book added to cart");
				resp.sendRedirect("all_new_book.jsp");
//				System.out.print("Success added Cart");
			}else {
				session.setAttribute("failed", "Something Wrong Happen!");
				resp.sendRedirect("all_new_book.jsp");
//				System.out.println("Not added to cart");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
