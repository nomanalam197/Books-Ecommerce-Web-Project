package com.user.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.print.event.PrintJobListener;

import com.DAO.BookOrderImpl;
import com.DAO.cartDAOimpl;
import com.DB.DBConnect;
import com.entity.Book_Order;
import com.entity.Cart;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/order")
public class OrderServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			
			HttpSession session = req.getSession();
			
			int id = Integer.parseInt(req.getParameter("id"));
			
			String name = req.getParameter("username");
			String email = req.getParameter("email");
			String phno = req.getParameter("phno");
			String address = req.getParameter("address");
			String landmark = req.getParameter("landmark");
			String city= req.getParameter("city");
			String state = req.getParameter("state");
			String pincode = req.getParameter("pincode");
			String paymentType = req.getParameter("payment");
			
			String fullAddress = address+", "+landmark+", "+city+", "+state+", "+pincode;
			
			cartDAOimpl daOimpl = new cartDAOimpl(DBConnect.getConn());
			List<Cart> blist = daOimpl.getBookByUser(id);
//			System.out.println(name+" "+email+" "+phno+" "+ fullAddress + " "+ paymentType);
//			System.out.println(blist);
			
			if(blist.isEmpty()) {
				
				session.setAttribute("failedMsg", "No Product In Cart.");
				resp.sendRedirect("checkout.jsp");
				
			}else {
				
				BookOrderImpl dao2 = new BookOrderImpl(DBConnect.getConn());
//				int i = dao2.getOrderNo();
				Random random = new Random();

				Book_Order o = null;
				
				ArrayList <Book_Order> orderList = new ArrayList<Book_Order>();
				
				for(Cart c: blist) {
					
					o = new Book_Order();
					o.setOrderId("BOOK-ORDER-00"+ random.nextInt(1000));;
					o.setUserName(name);
					o.setEmail(email);
					o.setPhno(phno);
					o.setFulladd(fullAddress);
					o.setBookname(c.getBookName());
					o.setAuthor(c.getAuthor());
					o.setPrice(c.getPrice()+"");
					o.setPaymentType(paymentType);
					orderList.add(o);
					
				}
				
				
				if("noselect".equals(paymentType)) {
					session.setAttribute("msg", "Choose Payment Method");
					resp.sendRedirect("checkout.jsp");
				}else {
					Boolean fBoolean = dao2.saveOrder(orderList);
					if(fBoolean) {
						session.setAttribute("succMsg", "Order Successfull.");
						resp.sendRedirect("order_success.jsp");
					}else {
						session.setAttribute("failedMsg", "Order Failed.");
						resp.sendRedirect("registration.jsp");
					}
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
}
