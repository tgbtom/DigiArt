<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Admin - Control Panel</title>

    <link
      href="https://fonts.googleapis.com/css?family=Khand"
      rel="stylesheet"
    />
    <link rel="stylesheet" href="mainStyle.css" />

    <script src="js/auctiontime.js"></script>
    <script src="js/home.js"></script>
  </head>
  <body>
    <nav id="navigate">
      <a href="Navigate?loc=dashboard" class="left">
        <img class="small-logo" src="img/sub-logo-clear-back.png" alt="Logo" />
        <p>DigiArt</p>
      </a>
      <a class="right" id="logout-btn" href="Navigate?loc=logout">Logout</a>
    </nav>
    <header id="banner">
      <img src="img/DigiArtBanner.png" alt="DigiArt Banner" />
    </header>

    <div class="container">  
      <div class="row">
        <div class="col-12">
          <p class="subtitle">Admin Control Panel</p>
        </div>
      </div>
      
	<div class="row">
		<%@ page import="java.util.List" %>
    	<%@ page import="com.fdmgroup.model.User" %>
    	<%@ page import="com.fdmgroup.dao.JPAUserDao" %>
    	
    	<div class="col-1"><!-- SPACER --></div>
    	<div class="col-10">
    			<div class="row head-table">
    				<div class="col-3">User ID</div>
    				<div class="col-3">Username</div>
    				<div class="col-3">First & Last Name</div>
    				<div class="col-3">Lock?</div>
    			</div>
    			<% 
    			JPAUserDao jud = new JPAUserDao();
    			List<User> users = jud.findAll();
    			for (User u : users){
    				%>
    				<div class="row">
    					<div class="col-3"><%=u.getId() %></div>
    					<div class="col-3"><%=u.getUsername() %></div>
    					<div class="col-3"><%=u.getFirstname() %> <%=u.getLastname() %></div>
    					<div class="col-3">
    					<% 
    					if(u.getLocked() == 1){
    						%>
    						<input type="checkbox" class="admin-lock" id="lock-<%=u.getId()%>" checked>
    						<%
    					} else{
    						%>
    						<input type="checkbox" class="admin-lock" id="lock-<%=u.getId()%>">
    						<%
    					}
    					%>
    					</div>
    				</div>
    				<%
    			}
    			%>
    	</div>
    	<div class="col-1"><!-- SPACER --></div>
    </div>

  </body>
</html>