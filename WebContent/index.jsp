<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8 /" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Homepage - DigiArt</title>

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
      <a class="right" id="login-btn">Login</a>
      <a class="right" id="register-btn">Register</a>
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
    	  if(counter == 6){
    		  break;
    	  }
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


      <div class="row">
        <div class="col-12">
          <p class="subtitle">Most Recent Auctions</p>
          
          <div class="row">
	      <% 
	      counter = 0;
	      for (Auction a : recentAuctions){
	    	  
	    	  String base64Image = new String(Base64.getEncoder().encode(a.getProduct().getImage()));
	    	  if(counter == 6){
	    		  break;
	    	  }
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
    
      <div class="row">
        <div class="col-12">
          <p class="subtitle">Ending Soon!</p>
          
        	<div class="row">
		      <% 
		      counter = 0;
		      for (Auction a : expiringAuctions){
		    	  
		    	  String base64Image = new String(Base64.getEncoder().encode(a.getProduct().getImage()));
		    	  if(counter == 6){
		    		  break;
		    	  }
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
    </div>

    <div class="full-modal" id="full-modal">
      <img src="img/exit.png" alt="close image" id="exit-modal" />

      <div id="image-modal-content">
        <img id="img-for-modal" src="img/sample4.png" alt="" />
      </div>

      <div id="login-modal-content">
        <div class="row">
          <div class="col-2"><!-- spacer --></div>
          <div class="col-8">
            <div class="card-float card-modal">
              <div class="card-top"><h4>Login</h4></div>
              <div class="card-content">
                <form action="Login" method="POST">
                  <div class="input-group">
                    <div class="col-4">
                      <label for="login-user">Username: </label>
                    </div>
                    <div class="col-8">
                      <input
                        type="text"
                        name="login-user"
                        id="login-username"
                        placeholder="Username"
                        required
                      />
                    </div>
                  </div>
                  <div class="input-group">
                    <div class="col-4">
                      <label for="login-pass">Password: </label>
                    </div>
                    <div class="col-8">
                      <input
                        type="password"
                        name="login-pass"
                        id="login-password"
                        placeholder="Password"
                        required
                      />
                    </div>
                  </div>

                  <input type="submit" value="Login" />
                </form>
              </div>
            </div>
          </div>
          <div class="col-2"><!-- spacer --></div>
        </div>
      </div>

      <div id="register-modal-content">
        <div class="row">
          <div class="col-2"><!-- spacer --></div>
          <div class="col-8">
            <div class="card-float card-modal">
              <div class="card-top"><h4>Register</h4></div>
              <div class="card-bottom">
                <form action="Register" method="POST">
                  <div class="input-group">
                    <div class="col-4">
                      <label for="fname">First Name:</label>
                    </div>
                    <div class="col-8">
                      <input
                        type="text"
                        name="fname"
                        placeholder="First Name"
                        required
                      />
                    </div>
                  </div>
                  <div class="input-group">
                    <div class="col-4">
                      <label for="lname">Last Name:</label>
                    </div>
                    <div class="col-8">
                      <input type="text" name="lname" placeholder="Last Name" required />
                    </div>
                  </div>
                  <div class="input-group">
                    <div class="col-4">
                      <label for="username">Username:</label>
                    </div>
                    <div class="col-8">
                      <input
                        type="text"
                        name="username"
                        placeholder="Username"
                        required
                      />
                    </div>
                  </div>
                  <div class="input-group">
                    <div class="col-4">
                      <label for="pswd1">Password:</label>
                    </div>
                    <div class="col-8">
                      <input
                        type="password"
                        name="pswd1"
                        id="pswd1"
                        placeholder="Password"
                        required
                      />
                    </div>
                  </div>
                  <div class="input-group">
                    <span><small id="pass-warning"></small></span>
                    <div class="col-4"><label for="pswd2">Repeat:</label></div>
                    <div class="col-8">
                      <input
                        type="password"
                        name="pswd2"
                        id="pswd2"
                        placeholder="Repeat Password"
                        required
                      />
                    </div>
                  </div>
                  <div class="input-group">
                    <div class="col-4"><label for="email">E-mail:</label></div>
                    <div class="col-8">
                      <input
                        type="email"
                        name="email"
                        id="email"
                        placeholder="Email Address"
                        required
                      />
                    </div>
                  </div>
                  <input type="submit" value="Register" />
                </form>
              </div>
            </div>
          </div>
          <div class="col-2"><!-- spacer --></div>
        </div>
      </div>
    </div>
  </body>
</html>
