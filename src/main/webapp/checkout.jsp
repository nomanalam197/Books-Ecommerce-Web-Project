<%@page import="com.DB.DBConnect"%>
<%@page import="com.DAO.cartDAOimpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cart Page</title>
<%@include file="all_component/allCss.jsp" %>
<%@page import="com.entity.*" %>
<%@page import="java.util.*" %>
</head>
<body style="background-color: #f0f1f2;">
<%@include file="all_component/navbar.jsp" %>

<c:if test="${empty userObj}">
	<c:redirect url="../login.jsp" />
</c:if>

<%User u = (User)session.getAttribute("userObj"); %>


<c:if test="${not empty failedMsg}">
	<div class="alert alert-danger text-center" role="alert">
			${failedMsg}
	</div>
	<c:remove var="failedMsg" scope="session" />
</c:if>

<c:if test="${not empty msg}">
	<div class="alert alert-danger text-center" role="alert">
			${msg}
	</div>
	<c:remove var="failedMsg" scope="session" />
</c:if>

<c:if test="${not empty succMsg}">
		<div class="alert alert-success text-center" role="alert">
			${succMsg}
		</div>
	<c:remove var="succMsg" scope="session" />
</c:if>

<div class="container">

	<div class="row p-2">
		<div class="col-md-6">
			
			<div class="card bg-white">
				<div class="card-body">
					<h3 class="text-center text-success">Your Selected Item</h3>
					
					<table class="table">
					  <thead>
					    <tr>
					      <th scope="col">Book Name</th>
					      <th scope="col">Author</th>
					      <th scope="col">Price</th>
					      <th scope="col">Action</th>
					    </tr>
					  </thead>
					  <tbody>
					  
					  <%
					  	cartDAOimpl dao = new cartDAOimpl(DBConnect.getConn());
					  	List<Cart> c = dao.getBookByUser(u.getId());
					  	Double totalPrice = 0.00;
					  	for(Cart ca : c){
					  		totalPrice = ca.getTotalPrice();
					  	%>
					  		
					  	 	<tr>
						      <th scope="row"><%=ca.getBookName()%></th>
						      <td><%=ca.getAuthor() %></td>
						      <td><%= ca.getPrice() %></td>
						      <td>
						      	<a href="remove_book?bid=<%=ca.getBid()%>&&uid=<%=ca.getUserId()%>&&cid=<%=ca.getCid()%>" class="btn btn-sm btn-danger">Remove</a>
						      </td>
					    	</tr>
					  		
					  <% }
					  %>
					  <tr>
						      <th scope="row">Total Price</th>
						      <td></td>
						      <td></td>
						      <td>
									<%=totalPrice %>
						      </td>
					    	</tr>
					  </tbody>
					</table>
					
				</div>
			</div>
			
		</div>
		
		<div class="col-md-6">
			
			<div class="card">
				<div class="card-body">
				<h3 class="text-center text-success">Your Details For Order</h3>
					<form action="order" method="POST">
						
						<input type="hidden" value="${userObj.id}" name="id" />
						
					  <div class="form-row">
					    <div class="form-group col-md-6">
					      <label for="inputEmail4">Name</label>
					      <input type="text" name="username" class="form-control" id="inputEmail4" value="<%=u.getName()%>" required>
					    </div>
					    <div class="form-group col-md-6">
					      <label for="inputPassword4">Email</label>
					      <input type="email" name="email" class="form-control" id="inputPassword4" value="<%=u.getEmail()%>" readonly="readonly">
					    </div>
					  </div>
					  
					  <div class="form-row">
					    <div class="form-group col-md-6">
					      <label for="inputEmail4">Phone Number</label>
					      <input type="number" name="phno" class="form-control" id="inputEmail4" value="<%=u.getPhno()%>" required>
					    </div>
					    <div class="form-group col-md-6">
					      <label for="inputPassword4">Address</label>
					      <input name="address" type="text" class="form-control" id="inputPassword4" required>
					    </div>
					  </div>
					  
					  <div class="form-row">
					    <div class="form-group col-md-6">
					      <label for="inputEmail4">Landmark</label>
					      <input name="landmark" type="text" class="form-control" id="inputEmail4"  required>
					    </div>
					    <div class="form-group col-md-6">
					      <label for="inputPassword4">City</label>
					      <input type="text" name="city" class="form-control" id="inputPassword4"  required>
					    </div>
					  </div>
					  
					  <div class="form-row">
					    <div class="form-group col-md-6">
					      <label for="inputEmail4">State</label>
					      <input type="text" name="state" class="form-control" id="inputEmail4" required>
					    </div>
					    <div class="form-group col-md-6">
					      <label for="inputPassword4">Pin code</label>
					      <input type="text" name="pincode" class="form-control" id="inputPassword4"  required>
					    </div>
					  </div>
					 
					 <div class="form-group">
					 	<label>Payment Mode</label>
					 	<select class="form-control" name="payment">
					 		<option value="noselect"> --Select--</option>
					 		<option value="cod"> Cash On Delivery </option>
					 	</select>
					 	
					 </div>
					 <div class="text-center">
					 
					 	<button class="btn btn-warning">Order Now</button>
					 	<a href="index.jsp"><button class="btn btn-success">Continue Shopping</button> </a> 
					 	
					 </div>
					 </form>
				</div>
			</div>
			
		</div>
	</div>
	
</div>

</body>
</html>