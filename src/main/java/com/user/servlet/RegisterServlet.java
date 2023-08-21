package com.user.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.DAO.*;
import com.entity.*;
import com.DB.*;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			String name = request.getParameter("fname");
			String email = request.getParameter("email");
			String phno = request.getParameter("phno");
			String password = request.getParameter("password");
			String check= request.getParameter("check");
			
//			System.out.print(name+" "+email+" "+phno+" "+password+" "+check);
			User us = new User();
			us.setName(name);
			us.setEmail(email);
			us.setPhno(phno);
			us.setPassword(password);
//			System.out.print(us);
			
			HttpSession session = request.getSession();
			
			if(check != null) {
			
				UserDAOImpl dao = new UserDAOImpl(DBConnect.getConn());
				
				if(dao.checkUser(email)) {
					
					boolean f = dao.userRegister(us);
					
					if(f) {
//						System.out.println("U"
//								+ "ser register successfully");
						session.setAttribute("succMsg", "Registration Successful.");
						resp.sendRedirect("registration.jsp");
					}else {
//						System.out.println("Something went wrong.");
						session.setAttribute("failedMsg", "Somesthing went wrong on server..");
						resp.sendRedirect("registration.jsp");					
					}
					
				}else {
					
					session.setAttribute("failedMsg", "User already exist.");
					resp.sendRedirect("registration.jsp");
					
				}
				
			
			}else {
//				System.out.println("Please Check Agree & Terms Condition");
				session.setAttribute("failedMsg", "Please Check Agree Terms & Condition");
				resp.sendRedirect("registration.jsp");		
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
