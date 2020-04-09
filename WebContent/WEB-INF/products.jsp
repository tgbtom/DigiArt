<%@page import="com.fdmgroup.model.ProductStatus"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Products - DigiArt</title>

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
      <%@ page import="com.fdmgroup.model.Product" %>
      <%@ page import="com.fdmgroup.model.User" %>
      <%@ page import="com.fdmgroup.model.ProductStatus" %>
      <%@ page import="java.util.List" %>
      <%@ page import="java.util.Base64" %>
      <%@ page import="com.fdmgroup.dao.JPAProductDao" %>
      <%! JPAProductDao jpd = new JPAProductDao(); %>
      <%! int counter = 0; %>
      <!-- JSP OUTPUT ROWS OF UP TO 3 PRODUCTS --> 
      <% 
      User user = (User) request.getSession().getAttribute("user");
      List<Product> products = jpd.findMine(user);
      %>
      
      <div class="row">
        <div class="col-12">
          <p class="subtitle">My Products</p>
        </div>
      </div>
      
      <div class="row">
      <% for (Product p : products){ 
    	  String imageBase64 = new String(Base64.getEncoder().encode(p.getImage()));
    	  if(counter % 3 != 0 && counter != 0){%>
			<div class="col-4">
				<div class="card">
					<div class="card-top">
						<p><a href="Navigate?loc=product&pid=<%= p.getProduct_id() %>"><%= p.getName() %></a></p>
					</div>
					<div class="card-content">
						<img src="data:image/png;base64,<%= imageBase64 %>" class="product" alt="Product Image"/>
					</div>
					<div class="card-bottom">
					
					
					<% 
					if(p.getStatus() == ProductStatus.AVAILABLE){
						%>
							
						<a href="Navigate?loc=createAuction&pid=<%= p.getProduct_id() %>">
							<button class="bid-btn green-text">
								Start Auction
							</button>
						</a>
							
						<%
					} else if(p.getStatus() == ProductStatus.AUCTIONED) {
						%>
						
						<a href="Navigate?loc=auction&aid=<%= p.getAuction().getAuctionId() %>">
							<button class="bid-btn green-text">
								View Auction
							</button>
						</a>
						
						<%
					} else {
						%>
						
						<a>
							<button class="bid-btn green-text">
								<%= p.getStatus() %>
							</button>
						</a>
						
						<%
					}
					%>			
					</div>
				</div>
			</div>
    	  <% }
    	  else{%>
    	  </div>
    	  <div class="row">
  			<div class="col-4">
				<div class="card">
					<div class="card-top">
						<p><a href="Navigate?loc=product&pid=<%= p.getProduct_id() %>"><%= p.getName() %></a></p>
					</div>
					<div class="card-content">
						<img src="data:image/png;base64,<%= imageBase64 %>" class="product" alt="Product Image"/>
					</div>
					<div class="card-bottom">
											
					<% 
					if(p.getStatus() == ProductStatus.AVAILABLE){
						%>
							
						<a href="Navigate?loc=createAuction&pid=<%= p.getProduct_id() %>">
							<button class="bid-btn green-text">
								Start Auction
							</button>
						</a>
							
						<%
					} else if(p.getStatus() == ProductStatus.AUCTIONED) {
						%>
						
						<a href="Navigate?loc=createAuction&pid=<%= p.getProduct_id() %>">
							<button class="bid-btn green-text">
								View Auction
							</button>
						</a>
						
						<%
					} else {
						%>
						
						<a>
							<button class="bid-btn green-text">
								<%= p.getStatus() %>
							</button>
						</a>
						
						<%
					}
					%>	
					</div>
				</div>
			</div>
    	  <%}
    	  counter++;
      	} counter = 0; %>
      	
    </div>

    <div class="full-modal" id="full-modal">
      <img src="img/exit.png" alt="close image" id="exit-modal" />

      <div id="image-modal-content">
        <img id="img-for-modal" src="img/sample4.png" alt="" />
      </div>
    </div>
  </body>
</html>