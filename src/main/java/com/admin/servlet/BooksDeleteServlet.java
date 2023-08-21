package com.admin.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.DAO.BookDAOImpl;
import com.DB.DBConnect;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;


@WebServlet("/delete")
public class BooksDeleteServlet extends HttpServlet{


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			int id = Integer.parseInt(req.getParameter("id"));
			BookDAOImpl daoImpl= new BookDAOImpl(DBConnect.getConn());
			boolean f = daoImpl.deleteteBooks(id);
			
			HttpSession session = req.getSession();
			if(f) {
				session.setAttribute("succMsg", "Book Deleted successfully");
				resp.sendRedirect("admin/all_books.jsp");
			}else {
				session.setAttribute("failedMsg", "Something wrong on Server");
				resp.sendRedirect("admin/all_books.jsp");
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
