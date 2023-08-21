<%@page import="com.entity.BookDtls"%>
<%@page import="com.DAO.BookDAOImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Book Page</title>
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

<%User u = (User)session.getAttribute("userObj"); %>
	<div class="container">
	
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<div class="card">
					<div class="card-body">
						<h5 class="text-center text-primary p-1">Edit Profile</h5>
						
						<c:if test="${ not empty succMsg }">
							<p class="text-center text-success">${succMsg }</p>
							<c:remove var="succMsg" scope="session"/>
						</c:if>
						
						<c:if test="${ not empty failedMsg }">
							<p class="text-center text-danger">${failedMsg }</p>
							<c:remove var="failedMsg" scope="session"/>
						</c:if>
						
						<form action="update_profile" method="post">
							<input type="hidden" value="${userObj.id}" name="id" >
							<div class="form-group">
								<label for="exampleInputEmail1">Name*</label>
								<input name="name" type="text" class="form-control" id="exampleInputEmail1" aria-describedly="emailHelp" value="${userObj.name}" >
							</div>
							<div class="form-group">
								<label for="exampleInputEmail1">Email*</label> 
								<input name="email" type="email" class="form-control" id="exampleInputEmail1" aria-describedly="emailHelp" value="<%= u.getEmail() %>">
							</div>
							<div class="form-group">
								<label for="exampleInputEmail1">Phone No*</label> 
								<input name="phno" type="number" class="form-control" id="exampleInputEmail1" aria-describedly="emailHelp" value="${userObj.phno}">
							</div>
							<div class="form-group">
								<label for="exampleInputEmail1">Password*</label> 
								<input name="password" type="password" class="form-control" id="exampleInputEmail1" aria-describedly="emailHelp">
							</div>
							
							
							<div class="text-center">
								<button type="submit" class="btn btn-primary">Update</button>
							</div>
						</form>
						
					</div>
				</div>
			</div>
		</div>
		
	</div>
		<%@include file="all_component/footer.jsp" %>
</body>
</html>