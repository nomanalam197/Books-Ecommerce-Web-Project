<%@page import="com.entity.BookDtls"%>
<%@page import="com.DAO.BookDAOImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Help 24*7</title>
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

	<div class="container">
	
		<div class="row p-5">
			
			<div class="col-md-4 offset-md-4 text-center">
				
				<div class="text-success">				
					<i class="fa-solid fa-phone-volume fa-5x" style="color: #1ac15a;"></i>
				</div>
				<h3>24*7 Service</h3>
				<h4>Help Line Number</h4>
				<h4>+91 6204966199</h4>
				<a href="index.jsp" class="btn btn-primary">Home</a>
			</div>
			
		</div>
	</div>
		<%@include file="all_component/footer.jsp" %>
</body>
</html>