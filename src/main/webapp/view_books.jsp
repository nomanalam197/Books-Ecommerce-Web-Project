<%@page import="com.entity.BookDtls"%>
<%@page import="com.DAO.BookDAOImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%@ include file="all_component/allCss.jsp" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.util.*" %>
<%@page import="com.DB.DBConnect" %>
</head>
<body style="background-color: #f0f1f2;">
		<%@include file="all_component/navbar.jsp" %>
		<%
			int bid = Integer.parseInt(request.getParameter("bid"));
			BookDAOImpl dao = new BookDAOImpl(DBConnect.getConn());
			BookDtls b = dao.getBookById(bid);
			
		%>
		<div class="container p-3">
			<div class="row">
				<div class="col-md-6 p-5 border text-center bg-white">
					<img src="book/<%= b.getPhotoName() %>" style="height: 170px; width: 150px"><br>
					<h4 class="mt-3">Book Name: <span class="text-success"><%= b.getBookName() %></span></h4>
					<h4>Author Name: <span class="text-success"> <%= b.getAuthor() %></span></h4>
					<h4> Category: <span class="text-success"> <%= b.getBookCategory() %></span> </h4>
				</div>
				
				<div class="col-md-6 p-5 border text-center bg-white">
				
					<h2><%= b.getBookName() %></h2>
					
					<%if("Old".equals(b.getBookCategory())){%>
							<h5 class="text-primary text-center"> Contact To Seller</h5>
							<h5 class="text-primary text-center"><i class="fa-solid fa-envelope"></i> Email: <%=b.getEmail() %></h5>
					<%}%>
				
					<div class="row">
						<div class="col-md-4 text-danger p-2 text-center">						
							<i class="fa-solid fa-money-bill-1-wave fa-2x"></i>
							<p> Cash On Delivery </p>
						</div>
						<div class="col-md-4 text-danger p-2 text-center">					
							<i class="fa-solid fa-rotate-left fa-2x"></i>
							<p> Return Available </p>
						</div>
						<div class="col-md-4 text-danger p-2 text-center">
							<i class="fa-solid fa-truck fa-2x"></i>
							<p> Free Shipping </p>						
						</div>
						
					</div>
					
					<%if("Old".equals(b.getBookCategory())){%>
						
						<div class="text-center p-3">
						<a href="index.jsp" class="btn btn-success"><i class="fa-solid fa-cart-plus"></i> Continue Shopping</a>
						<a href="" class="btn btn-danger"><i class="fa-solid fa-indian-rupee-sign"></i> 200</a>
						</div>
							
					<% }else{%>
					
						<div class="text-center p-3">
							<a href="" class="btn btn-primary"><i class="fa-solid fa-cart-plus"></i> Add Cart</a>
							<a href="" class="btn btn-danger"><i class="fa-solid fa-indian-rupee-sign"></i> 200</a>
						</div>
						
					<%} %>
				</div>
			</div>
		</div>
		
</body>
</html>