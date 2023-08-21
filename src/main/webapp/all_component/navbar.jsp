<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false"%>

<div class="container-fluid p-3 bg-light">

	<div class="row">

		<div class="co-md-3 text-success">
			<h3 class="px-2"><i class="fa-solid fa-book"></i> Ebooks</h3>
		</div>
		<div class="col-md-6">
			<form class="form-inline my-2 my-lg-0" action="search.jsp" method="post">
				<input class="form-control mr-sm-2" type="search" name="ch"
					placeholder="Search" aria-label="Search">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
			</form>
		</div>
		
		<c:if test="${not empty userObj }">
		
			<div class="col-md-4">
			
				<div class="row">
				
					<a href="checkout.jsp" class="pt-1 mr-1 "> 
				. . .<i class="fa-solid fa-dolly fa-2x"></i> Cart
			</a>
			
				<a href="login.jsp" class="btn btn-success ">${userObj.name}</a>
				 <a href="registration.jsp"
					class="btn btn-primary ml-1" data-toggle="modal" data-target="#exampleModalCenter" ><i class="fa-solid fa-right-to-bracket"></i>  Logout</a>
				
				</div>
			
			</div>
			
		</c:if>
		
		
		<!-- Modal -->

<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle"></h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <div class="text-center">
        	<h4>Do You want to logout</h4>
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
	        <a href="logout"> <button type="button" class="btn btn-primary text-white">Logout</button> </a> 
        </div>
      </div>
      <div class="modal-footer">
        
      </div>
    </div>
  </div>
</div>
		
		
		<c:if test="${empty userObj }">
			<div class="col-md-3">
				<a href="login.jsp" class="btn btn-success"><i class="fa-solid fa-right-to-bracket"></i> Login</a>
				 <a href="registration.jsp"
					class="btn btn-primary"><i class="fa-solid fa-user-plus"></i> Registration</a>
			</div>
		</c:if>
		

	</div>

</div>

<nav class="navbar navbar-expand-lg navbar-dark bg-custom ">
	<a class="navbar-brand" href="#"><i class="fa-solid fa-house"></i></a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active"><a class="nav-link" href="index.jsp">Home
					<span class="sr-only">(current)</span>
			</a></li>
			<li class="nav-item active"><a class="nav-link" href="all_recent_book.jsp"> <i class="fa-solid fa-book-open"></i> Recent
					Book</a></li>
			<li class="nav-item active"><a class="nav-link" href="all_new_book.jsp"> <i class="fa-solid fa-book-open"></i> New
					Book</a></li>
			<li class="nav-item active"><a class="nav-link disabled"
				href="all_old_book.jsp"> <i class="fa-solid fa-book-open"></i> Old Book</a></li>
		</ul>
		<form class="form-inline my-2 my-lg-0">
			<a href="setting.jsp" class="btn btn-light my-2 my-sm-0" type="submit"><i class="fa-solid fa-gear"></i> Setting</a>
			<a href="helpline.jsp" class="btn btn-light my-2 ml-1 my-sm-0" type="submit"><i class="fa-regular fa-address-book"></i> Contact Us</a>
		</form>
	</div>
</nav>