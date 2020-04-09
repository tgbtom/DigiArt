<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Auctions - DigiArt</title>

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
      <a href="Navigate?loc=dashboard" class="left">
        <img class="small-logo" src="img/sub-logo-clear-back.png" alt="Logo" />
        <p>DigiArt</p>
      </a>

      <a class="right" id="logout-btn" href="Navigate?loc=logout">Logout</a>
      <a class="right" href="Navigate?loc=profile">Profile</a>
      <a class="right dropdown-btn"
        >Products
        <div class="dropdown-content">
          <div class="dropdown-link" onclick="product(1)">My Products</div>
          <div class="dropdown-link" onclick="product(2)">Upload Product</div>
          <div class="dropdown-link" onclick="product(3)">
            Available Products
          </div>
          <div class="dropdown-link" onclick="product(4)">Sold Products</div>
          <div class="dropdown-link" onclick="product(5)">Bought Products</div>
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
      <div class="row">
        <div class="col-12">
        <%
        if(request.getParameter("as").equals("mine")){
        	%>
        	 <p class="subtitle">My Auctions</p>
        <%
        }
        else{
        	%>
        	 <p class="subtitle">Browse Auctions</p>
        <%
        }
        %>
        
          <div class="col-6">
            <div class="input-group content-center">
              <label for="sort-by">Sort By: </label>
              <select name="sort-by" id="">
                <option value="">Product Name</option>
                <option value="">Time Remaining (low to high)</option>
                <option value="">Time Remaining (high to low)</option>
              </select>
            </div>
          </div>
          <div class="col-6">
            <div class="input-group content-center">
              <input type="search" name="" id="" />
            </div>
          </div>
        </div>
      </div>
      
      <%@ page import="java.util.List" %>
      <%@ page import="java.util.Base64" %>
      <%@ page import="com.fdmgroup.model.Auction" %>
      <%@ page import="com.fdmgroup.dao.JPAAuctionDao" %>
      
      <!-- Loop through auctions request.getAttribute("auctions") -->
      <div class="row">
      <%
      JPAAuctionDao jad = new JPAAuctionDao();
      List<Auction> auctions = (List<Auction>) request.getAttribute("auctions"); 
      int counter = 0;
      for (Auction a : auctions){
    	  
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
		              <span class="time-left"></span>
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
    		    <div class="col-4">
		          <div class="card">
		            <div class="card-top">
		              <p><%= a.getProduct().getName() %></p>
		            </div>
		            <div class="card-content">
		              <img src="img/sample1.png" alt="product 1" class="product" />
		            </div>
		            <div class="card-bottom">
		              <span class="time-left"></span>
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
      
      
<!--       <div class="row">
        <div class="col-4">
          <div class="card">
            <div class="card-top">
              <p>Product 1</p>
            </div>
            <div class="card-content">
              <img src="img/sample1.png" alt="product 1" class="product" />
            </div>
            <div class="card-bottom">
              <span class="time-left"></span>
              <a href="auctionpage.html?pid=3">
                <button class="bid-btn">
                  $5.15
                </button>
              </a>
            </div>
          </div>
        </div>
        <div class="col-4">
          <div class="card">
            <div class="card-top">
              <p>Product 2</p>
            </div>
            <div class="card-content">
              <img src="img/sample2.png" alt="product 2" class="product" />
            </div>
            <div class="card-bottom">
              <span class="time-left"></span>
              <a href="auctionpage.html?pid=3">
                <button class="bid-btn">
                  $7.10
                </button>
              </a>
            </div>
          </div>
        </div>
        <div class="col-4">
          <div class="card">
            <div class="card-top">
              <p>Product 3</p>
            </div>
            <div class="card-content">
              <img src="img/sample3.png" alt="product 3" class="product" />
            </div>
            <div class="card-bottom">
              <span class="time-left"></span>
              <a href="auctionpage.html?pid=3">
                <button class="bid-btn">
                  $3.00
                </button>
              </a>
            </div>
          </div>
        </div>
      </div> -->
    </div>

    <div class="full-modal" id="full-modal">
      <img src="img/exit.png" alt="close image" id="exit-modal" />

      <div id="image-modal-content">
        <img id="img-for-modal" src="img/sample4.png" alt="" />
      </div>
    </div>
  </body>
</html>