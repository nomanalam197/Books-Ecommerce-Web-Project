package com.user.servlet;

import java.io.IOException;

import org.apache.tomcat.util.descriptor.tld.TldResourcePath;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			HttpSession session = req.getSession();
			session.removeAttribute("userObj");
			
			HttpSession session2 = req.getSession();
			session2.setAttribute("succMsg", "Logout Successfully.");
			resp.sendRedirect("login.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	
}
