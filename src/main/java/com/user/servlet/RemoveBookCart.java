package com.user.servlet;

import java.io.IOException;

import com.DAO.cartDAOimpl;
import com.DB.DBConnect;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/remove_book")
public class RemoveBookCart extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int bid = Integer.parseInt(req.getParameter("bid"));
		int uid = Integer.parseInt(req.getParameter("uid"));
		int cid = Integer.parseInt(req.getParameter("cid"));
		cartDAOimpl daOimpl = new cartDAOimpl(DBConnect.getConn());
		boolean f = daOimpl.deleteBook(cid,bid,uid);
		
		HttpSession session = req.getSession();
		if(f) {
			session.setAttribute("succMsg", "Book removed from Cart successfully.");
			resp.sendRedirect("checkout.jsp");
		}else {
			session.setAttribute("failedMsg", "Something went wrong on server.");
			resp.sendRedirect("checkout.jsp");
		}
		
	}
	
}
