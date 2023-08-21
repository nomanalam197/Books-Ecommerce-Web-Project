package com.user.servlet;

import java.io.IOException;

import com.DAO.BookDAOImpl;
import com.DB.DBConnect;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/delete_old_book")
public class DeleteOldBook extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			String emString = req.getParameter("em");
			int id = Integer.parseInt(req.getParameter("id"));
			BookDAOImpl daoImpl = new BookDAOImpl(DBConnect.getConn());
			
			boolean f = daoImpl.oldBookDelete(emString, "Old", id);
			
			HttpSession session = req.getSession();
			if(f) {
				session.setAttribute("succMsg", "Old Book removed successfully.");
				resp.sendRedirect("old_book.jsp");
			}else {
				session.setAttribute("failedMsg", "Something went wrong on server.");
				resp.sendRedirect("old_book.jsp");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
