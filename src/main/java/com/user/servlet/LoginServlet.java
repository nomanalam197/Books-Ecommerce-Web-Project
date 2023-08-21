package com.user.servlet;

import java.io.IOException;

import com.DB.DBConnect;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.entity.*;
import com.DAO.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserDAOImpl dao =  new UserDAOImpl(DBConnect.getConn());
		HttpSession session = req.getSession();
		try {
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			
//			System.out.print(email+" "+password);
			if("admin@gmail.com".equals(email) && "admin".equals(password)) {
				User us = new User();
				us.setName("Admin");
				session.setAttribute("userObj", us);
				resp.sendRedirect("admin/home.jsp");
			}else {
				
				User us = dao.login(email, password);
				if(us != null) {
					session.setAttribute("userObj", us);
					resp.sendRedirect("index.jsp");			
				}else {
					session.setAttribute("failedMsg", "Invalid Email Id & Password.");
					resp.sendRedirect("login.jsp");
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
