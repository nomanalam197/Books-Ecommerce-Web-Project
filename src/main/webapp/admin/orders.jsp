<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false"%>
<%@page import="com.DAO.BookOrderImpl"%>
<%@page import="com.entity.*" %>
<%@page import="com.DAO.BookDAOImpl"%>
<%@ page import="java.util.*" %>
<%@page import="com.DB.DBConnect" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin: All Orders</title>
<%@include file='allCss.jsp' %>
</head>
<body>
<%@include file="navbar.jsp" %>
	
	<c:if test="${empty userObj}">
		<c:redirect url="../login.jsp" />
	</c:if>
	
	<h3 class="text-center mb-2 mt-2">Hello Admin</h3>
	
	<table class="table table-striped">
	  <thead class="bg-primary text-white">
	    <tr>
	      <th scope="col">OrderId</th>
	      <th scope="col">Name</th>
	      <th scope="col">Email</th>
	      <th scope="col">Address</th>
	      <th scope="col">Ph No</th>
	      <th scope="col">Book Name</th>
	      <th scope="col">Author</th>
	      <th scope="col">Price</th>
	      <th scope="col">Payment type</th>
	    </tr>
	  </thead>
	  <tbody>
	  
	   <%
	  BookOrderImpl dao = new BookOrderImpl(DBConnect.getConn());
		List<Book_Order> blist =  dao.getAllBook();
		for(Book_Order b: blist){ %>
	  
	    <tr>
	      <th scope="row"><%= b.getOrderId() %></th>
	      <td><%= b.getUserName() %></td>
	      <td><%= b.getEmail() %> </td>
	      <td><%= b.getFulladd() %></td>
	      <td><%= b.getPhno() %></td>
	      <td><%= b.getBookname() %></td>
	      <td><%= b.getAuthor() %></td>
	      <td><%= b.getPrice() %></td>
	      <td><%= b.getPaymentType() %></td>
	    </tr>
	    
	    <% }
	  %>
	   
	  </tbody>
	</table>

</body>
</html>