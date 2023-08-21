package com.user.servlet;

import java.io.IOException;

import com.DAO.UserDAOImpl;
import com.DB.DBConnect;
import com.entity.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/update_profile")
public class UpdateProfileServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int idO = Integer.parseInt(req.getParameter("id"));
			String name = req.getParameter("name");
			String email = req.getParameter("email");
			String phno = req.getParameter("phno");
			String password = req.getParameter("password");
			
			User udUser = new User();
			udUser.setId(idO);
			udUser.setName(name);
			udUser.setEmail(email);
			udUser.setPhno(phno);
//			udUser.setPassword(password);
			
			UserDAOImpl daoImpl = new UserDAOImpl(DBConnect.getConn());
			
			boolean f = daoImpl.checkPassword(idO, password);
			
			HttpSession session = req.getSession();
			
			if(f) {
				
				Boolean fBoolean = daoImpl.updateProfile(udUser);
				if (fBoolean) {
					
					session.setAttribute("succMsg", "Profile Updated Successfully.");
					resp.sendRedirect("Edit_profile.jsp");
					
				}else {
					
					session.setAttribute("failedMsg", "Somesthing went wrong on server..");
					resp.sendRedirect("Edit_profile.jsp");
					
				}
				
			}else {
				session.setAttribute("failedMsg", "Wrong Credentials.");
				resp.sendRedirect("Edit_profile.jsp");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
