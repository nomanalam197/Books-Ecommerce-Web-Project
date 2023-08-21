<%@page import="com.entity.BookDtls"%>
<%@page import="com.DAO.BookDAOImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ebook: Index</title>
<%@ include file="all_component/allCss.jsp" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.util.*" %>
<%@page import="com.DB.DBConnect" %>
<%@page import="com.entity.*" %>
<style type=text/css>
	.back-img{
		height: 53vh;
		width: 100%;
		overflow: hidden;
	}
	.carousel-content {
	  position: absolute;
	  top: 20%;
	  left: 50%;
	  transform: translate(-50%, -50%);
	  z-index: 20;
	  color: white;
	  text-shadow: 0 1px 2px rgba(0,0,0,.6);
	}
	.card-ho{
		width: 250px;
	}
	.card-ho:hover{
		background-color: #fcf7f7;
	}
	::-webkit-scrollbar {
    display: none;
	}
</style>

</head>
<body style="background-color: #f7f7f7;">

<%User u = (User)session.getAttribute("userObj"); %>

	<%@include file="all_component/navbar.jsp" %>
	
	<div class="container-fluid back-img">
		<div id="carouselExampleControls" class="carousel slide position-relative" data-ride="carousel">
			<div class="carousel-content">
				<h2>Ebook Management System</h2>
	      	</div>
		  <div class="carousel-inner">
		    <div class="carousel-item active">
		      <img class="d-block w-100" src="img/a.jpg" alt="First slide">
		    </div>
		    <div class="carousel-item">
		      <img class="d-block w-100" src="img/f.jpg" alt="Second slide">
		    </div>
		    <div class="carousel-item">
		      <img class="d-block w-100" src="img/h.jpg" alt="Third slide">
		    </div>
		  </div>
		  <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
		    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
		    <span class="sr-only">Previous</span>
		  </a>
		  <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
		    <span class="carousel-control-next-icon" aria-hidden="true"></span>
		    <span class="sr-only">Next</span>
		  </a>
		</div>
		
	</div>

	
	<!-- Start recent book -->
	
	<div class="container-fluid" >
		<h3 class="text-center">Recent Book</h3>
		
		<div class="row d-flex align-items-center">
		
			<%
				BookDAOImpl dao2 = new BookDAOImpl(DBConnect.getConn());
				List<BookDtls> list2 = dao2.getRecentBookDtls();
				for(BookDtls b : list2){
			%>	
				
			<div class="col-md-3 m-0.5">
				
				<div class="card card-ho">
					<div class="card-body text-center">
						<img src="book/<%= b.getPhotoName() %>" style="height:200px; width: 150px" class="img-thumblin"/>
						<p><%= b.getBookName() %></p>
						<p><%= b.getAuthor() %></p>
						<p> Category: <%= b.getBookCategory() %> </p>
						
						<%
							if(b.getBookCategory().equals("Old")){ %>
							<div class="row d-flex justify-content-around" style="width: 240px;">
								<a href="view_books.jsp?bid=<%= b.getBookId() %>" class="btn btn-success btn-sm mr-1">View Details</a>
								<a href="" class="btn btn-danger btn-sm"><i class="fa-solid fa-indian-rupee-sign fa-xs"></i> <%= b.getPrice() %></a>
							</div>
							<%}else{ %>
							<div class="row " style="width: 240px;">
							
							<% if(u == null){ %>
								<a href="login.jsp" class="btn btn-danger btn-sm mr-1"><i class="fa-solid fa-cart-plus fa-2xs"></i> Add Cart</a>
							<%}else{ %>
								<a href="cart?bid=<%=b.getBookId()%>&&uid=<%=u.getId()%>" class="btn btn-danger btn-sm mr-1"><i class="fa-solid fa-cart-plus fa-2xs"></i> Add Cart</a>
							<%} %>	
						
							
								<a href="view_books.jsp?bid=<%= b.getBookId() %>" class="btn btn-success btn-sm mr-1">View Details</a>
								<a href="" class="btn btn-danger btn-sm"><i class="fa-solid fa-indian-rupee-sign fa-xs"></i> <%= b.getPrice() %></a>
							</div>
							
						<%	}
						%>
						
						
					</div>
				</div>
					
			</div>
			
			<%	}
			%>
			
		</div>
		
		
			<div class="text-center mt-1">
				<a href="all_recent_book.jsp" class="btn btn-danger text-center btn-sm text-white">View All</a>
			</div>
	</div>
	
		<!-- End recent book -->
		<hr/>
	<!-- Start New book -->
	
	<div class="container-fluid" >
		<h3 class="text-center">New Book</h3>
		
		<div class="row d-flex align-items-center">
		
			
			
			<%
				BookDAOImpl dao = new BookDAOImpl(DBConnect.getConn());
				List<BookDtls> list = dao.getNewBookDtls();
				for(BookDtls b : list){
			%>	
			<div class="col-md-3 m-0.5">
				
				<div class="card card-ho">
					<div class="card-body text-center">
						<img src="book/<%= b.getPhotoName() %>" style="height:200px; width: 150px" class="img-thumblin"/>
						<p><%= b.getBookName() %></p>
						<p><%= b.getAuthor() %></p>
						<p> Category: <%= b.getBookCategory() %> </p>
						<div class="row " style="width: 240px;">
						
						<% if(u == null){ %>
							<a href="login.jsp" class="btn btn-danger btn-sm mr-1"><i class="fa-solid fa-cart-plus fa-2xs"></i> Add Cart</a>
						<%}else{ %>
							<a href="cart?bid=<%=b.getBookId()%>&&uid=<%=u.getId()%>" class="btn btn-danger btn-sm mr-1"><i class="fa-solid fa-cart-plus fa-2xs"></i> Add Cart</a>
						<%} %>
						
							
							<a href="view_books.jsp?bid=<%= b.getBookId() %>" class="btn btn-success btn-sm mr-1">View Details</a>
							<a href="" class="btn btn-danger btn-sm"><i class="fa-solid fa-indian-rupee-sign fa-xs"></i> <%= b.getPrice() %></a>
						</div>
					</div>
				</div>
					
			</div>
					
			<%	}
			%>
				
			
		</div>
		
		
			<div class="text-center mt-1">
				<a href="all_new_book.jsp" class="btn btn-danger text-center btn-sm text-white">View All</a>
			</div>
	</div>
	
		<!-- End New book -->
		<hr/>
	<!-- Start old book -->
	
	<div class="container-fluid mb-5" >
		<h3 class="text-center">Old Book</h3>
		
		<div class="row d-flex align-items-center">
		
			
			
			<%
				BookDAOImpl dao3 = new BookDAOImpl(DBConnect.getConn());
				List<BookDtls> list3 = dao3.getOldBookDtls();
				for(BookDtls b : list3){
			%>	
			<div class="col-md-3 m-0.5">
				
				<div class="card card-ho">
					<div class="card-body text-center">
						<img src="book/<%= b.getPhotoName() %>" style="height:200px; width: 150px" class="img-thumblin"/>
						<p><%= b.getBookName() %></p>
						<p><%= b.getAuthor() %></p>
						<p> Category: <%= b.getBookCategory() %> </p>
						<div class="row d-flex justify-content-around" style="width: 240px;">
							<a href="view_books.jsp?bid=<%= b.getBookId() %>" class="btn btn-success btn-sm mr-1">View Details</a>
							<a href="" class="btn btn-danger btn-sm"><i class="fa-solid fa-indian-rupee-sign fa-xs"></i> <%= b.getPrice() %></a>
						</div>
					</div>
				</div>
					
			</div>
					
			<%	}
			%>
				
			
		</div>
		
		
			<div class="text-center mt-1">
				<a href="all_old_book.jsp" class="btn btn-danger text-center btn-sm text-white">View All</a>
			</div>
	</div>
	
		<!-- End old book -->
	<div class="container-fluid" style="padding-top: 150px;">	
		<%@include file="all_component/footer.jsp" %>
	</div>
	
</body>
</html>