<%@page import="com.DAO.BookOrderImpl"%>
<%@page import="com.entity.BookDtls"%>
<%@page import="com.DAO.BookDAOImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Order Details</title>
<%@ include file="all_component/allCss.jsp" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.util.*" %>
<%@page import="com.DB.DBConnect" %>
<%@page import="com.entity.*" %>

</head>
<body style="background-color: #f7f7f7;">
<%@include file="all_component/navbar.jsp" %>
		
<c:if test="${empty userObj}">
	<c:redirect url="login.jsp" />
</c:if>


<div class="container p-1">

<h3 class="text-center text-primary">Your Order</h3>
	
	<table class="table table-striped mt-3">
	  <thead class="bg-primary text-white">
	    <tr>
	      <th scope="col">Order Id</th>
	      <th scope="col">Name</th>
	      <th scope="col">Book Name</th>
	      <th scope="col">Author</th>
	      <th scope="col">Price</th>
	      <th scope="col">Payment Type</th>
	    </tr>
	  </thead>
	  <tbody>
	  
	  <%
		User u = (User)session.getAttribute("userObj");
	  BookOrderImpl dao = new BookOrderImpl(DBConnect.getConn());
		List<Book_Order> blist =  dao.getBook(u.getEmail());
		for(Book_Order b: blist){ %>
			
		<tr>
	      <th scope="row"><%= b.getOrderId() %></th>
	      <td><%= b.getUserName() %></td>
	      <td><%= b.getBookname() %></td>
	      <td><%= b.getAuthor() %></td>
	      <td><%= b.getPrice() %></td>
	      <td><%= b.getPaymentType() %></td>
	    </tr>
					
		<% }
	  %>
	  
	    
	    
	  </tbody>
	</table>
	
</div>

		<%@include file="all_component/footer.jsp" %>
</body>
</html>