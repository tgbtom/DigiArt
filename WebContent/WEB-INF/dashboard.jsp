<%@page import="java.time.temporal.ChronoUnit"%>
<%@page import="com.fdmgroup.dao.JPAUserDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Dashboard - DigiArt</title>

    <link
      href="https://fonts.googleapis.com/css?family=Khand"
      rel="stylesheet"
    />
    <link rel="stylesheet" href="mainStyle.css" />

    <script src="js/home.js"></script>
    <script src="js/auctiontime.js"></script>
  </head>
  <body>
  
    <% if (request.getSession().getAttribute("message") != null){  %>
   		<div class="page-message" id="page-message">
    <%= request.getSession().getAttribute("message") %>
   			<img src="img/small-x.png" id="msg-close" alt="close message" />
   		</div>
   	<% request.getSession().removeAttribute("message");} %>
  
    <nav id="navigate">
      <a href="#" class="left">
        <img class="small-logo" src="img/sub-logo-clear-back.png" alt="Logo" />
        <p>DigiArt</p>
      </a>
      <!-- <a href="">Auctions</a>
          <a href="">Products</a> -->
      <a class="right" id="logout-btn" href="Navigate?loc=logout">Logout</a>
      <a class="right" href="Navigate?loc=profile">Profile</a>
      <a class="right dropdown-btn"
        >Products
        <div class="dropdown-content">
          <div class="dropdown-link" onclick="product(1)">My Products</div>
          <div class="dropdown-link" onclick="product(2)">Upload Product</div>
        </div>
      </a>
      <a class="right dropdown-btn"
        >Auctions
        <div class="dropdown-content">
          <div class="dropdown-link" onclick="auction(1)">My Auctions</div>
          <div class="dropdown-link" onclick="auction(2)">Browse Auctions</div>
        </div>
      </a>
    </nav>
    <header id="banner">
      <img src="img/DigiArtBanner.png" alt="DigiArt Banner" />
    </header>

    <div class="container">
    
    <%@ page import="com.fdmgroup.dao.JPAAuctionDao" %>
	<%@ page import="com.fdmgroup.model.Auction" %>
	<%@ page import="java.util.List" %>
	<%@ page import="java.util.Base64" %>
	
	<%
		JPAAuctionDao jad = new JPAAuctionDao();
		List<Auction> topAuctions = jad.findTopAuctions();
		List<Auction> recentAuctions = jad.findRecentAuctions();
		List<Auction> expiringAuctions = jad.findExpiringAuctions();
	%>
    
      <div class="row">
        <div class="col-12">
          <p class="subtitle">Top Auctions</p>
          
          <div class="row">
      <% 
      int counter = 0;
      for (Auction a : topAuctions){
    	  
    	  String base64Image = new String(Base64.getEncoder().encode(a.getProduct().getImage()));
    	  
    	  if(counter % 3 != 0 || counter == 0){
    		  %>
    		  <div class="col-4">
		          <div class="card">
		            <div class="card-top">
		              <p><%= a.getProduct().getName() %></p>
		            </div>
		            <div class="card-content">
		              <img src="data:image/png;base64,<%=base64Image%>" alt="Product Image" class="product" />
		            </div>
		            <div class="card-bottom">
		              <span class="time-left">
						<%= jad.getTimeTillExpire(a) %>
		              </span>
		              <a href="Navigate?loc=auction&aid=<%=a.getAuctionId()%>">
		                <button class="bid-btn">
		                  $<%= jad.getHighestBid(a).getValue() + a.getMinIncrease() %>
		                </button>
		              </a>
		            </div>
		          </div>
		        </div>
    		  <%
    	  }
    	  else{
    		  %>
    		  </div>
    		    <div class="col-4">
		          <div class="card">
		            <div class="card-top">
		              <p><%= a.getProduct().getName() %></p>
		            </div>
		            <div class="card-content">
		              <img src="data:image/png;base64,<%=base64Image%>" alt="Product Image" class="product" />
		            </div>
		            <div class="card-bottom">
		              <span class="time-left">
						<%= jad.getTimeTillExpire(a) %>
		              </span>
		              <a href="Navigate?loc=auction&aid=<%=a.getAuctionId()%>">
		                <button class="bid-btn">
		                  $<%= jad.getHighestBid(a).getValue() + a.getMinIncrease() %>
		                </button>
		              </a>
		            </div>
		          </div>
		        </div>
    		<%
    	  	}
    	  	counter++;
      	}%>
          
          
        </div>
      </div>


      <div class="row">
        <div class="col-12">
          <p class="subtitle">Most Recent Auctions</p>
          
          <div class="row">
	      <% 
	      counter = 0;
	      for (Auction a : recentAuctions){
	    	  
	    	  String base64Image = new String(Base64.getEncoder().encode(a.getProduct().getImage()));
	    	  
	    	  if(counter % 3 != 0 || counter == 0){
	    		  %>
	    		  <div class="col-4">
			          <div class="card">
			            <div class="card-top">
			              <p><%= a.getProduct().getName() %></p>
			            </div>
			            <div class="card-content">
			              <img src="data:image/png;base64,<%=base64Image%>" alt="Product Image" class="product" />
			            </div>
			            <div class="card-bottom">
			              <span class="time-left"><%= jad.getTimeTillExpire(a) %></span>
			              <a href="Navigate?loc=auction&aid=<%=a.getAuctionId()%>">
			                <button class="bid-btn">
			                  $<%= jad.getHighestBid(a).getValue() + a.getMinIncrease() %>
			                </button>
			              </a>
			            </div>
			          </div>
			        </div>
	    		  <%
	    	  }
	    	  else{
	    		  %>
	    		  </div>
	    		    <div class="col-4">
			          <div class="card">
			            <div class="card-top">
			              <p><%= a.getProduct().getName() %></p>
			            </div>
			            <div class="card-content">
			              <img src="data:image/png;base64,<%=base64Image%>" alt="Product Image" class="product" />
			            </div>
			            <div class="card-bottom">
			              <span class="time-left"><%= jad.getTimeTillExpire(a) %></span>
			              <a href="Navigate?loc=auction&aid=<%=a.getAuctionId()%>">
			                <button class="bid-btn">
			                  $<%= jad.getHighestBid(a).getValue() + a.getMinIncrease() %>
			                </button>
			              </a>
			            </div>
			          </div>
			        </div>
	    		<%
	    	  	}
	    	  	counter++;
      	}%>
          
        </div>
    </div>
    
      <div class="row">
        <div class="col-12">
          <p class="subtitle">Ending Soon!</p>
          
        	<div class="row">
		      <% 
		      counter = 0;
		      for (Auction a : expiringAuctions){
		    	  
		    	  String base64Image = new String(Base64.getEncoder().encode(a.getProduct().getImage()));
		    	  
		    	  if(counter % 3 != 0 || counter == 0){
		    		  %>
		    		  <div class="col-4">
				          <div class="card">
				            <div class="card-top">
				              <p><%= a.getProduct().getName() %></p>
				            </div>
				            <div class="card-content">
				              <img src="data:image/png;base64,<%=base64Image%>" alt="Product Image" class="product" />
				            </div>
				            <div class="card-bottom">
				              <span class="time-left"><%= jad.getTimeTillExpire(a) %></span>
				              <a href="Navigate?loc=auction&aid=<%=a.getAuctionId()%>">
				                <button class="bid-btn">
				                  $<%= jad.getHighestBid(a).getValue() + a.getMinIncrease() %>
				                </button>
				              </a>
				            </div>
				          </div>
				        </div>
		    		  <%
		    	  }
		    	  else{
		    		  %>
		    		  </div>
		    		    <div class="col-4">
				          <div class="card">
				            <div class="card-top">
				              <p><%= a.getProduct().getName() %></p>
				            </div>
				            <div class="card-content">
				              <img src="data:image/png;base64,<%=base64Image%>" alt="Product Image" class="product" />
				            </div>
				            <div class="card-bottom">
				              <span class="time-left"><%= jad.getTimeTillExpire(a) %></span>
				              <a href="Navigate?loc=auction&aid=<%=a.getAuctionId()%>">
				                <button class="bid-btn">
				                  $<%= jad.getHighestBid(a).getValue() + a.getMinIncrease() %>
				                </button>
				              </a>
				            </div>
				          </div>
				        </div>
		    		<%
		    	  	}
		    	  	counter++;
		      	}%>
        </div>


    </div>

    <div class="full-modal" id="full-modal">
      <img src="img/exit.png" alt="close image" id="exit-modal" />

      <div id="image-modal-content">
        <img id="img-for-modal" src="img/sample4.png" alt="" />
      </div>
    </div>
  </body>
</html>