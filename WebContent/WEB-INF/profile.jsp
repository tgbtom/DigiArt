<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>My Profile</title>

    <link
      href="https://fonts.googleapis.com/css?family=Khand"
      rel="stylesheet"
    />
    <link rel="stylesheet" href="mainStyle.css" />

    <script src="js/home.js"></script>
    <script src="js/auctiontime.js"></script>
    <%@ page import="com.fdmgroup.model.User" %>
    <% 
    User user = (User) request.getSession().getAttribute("user"); 
    %>
    
  </head>
  <body>
    <nav id="navigate">
      <a href="" class="left">
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
          <p class="subtitle">My Profile</p>
          <div class="card">
            <div class="card-content">
              <table class="table-details">
                <tbody>
                  <tr>
                    <td>Name</td>
                    <td><%= user.getFirstname() %> <%= user.getLastname() %></td>
                  </tr>
                  <tr>
                    <td>Username</td>
                    <td><%= user.getUsername() %></td>
                  </tr>
                  <tr>
                    <td>Email</td>
                    <td>t<%= user.getEmail() %></td>
                  </tr>
                  <tr>
                    <td>Wallet (CAD)</td>
                    <td>$45<%= user.getWallet() %></td>
                  </tr>
                </tbody>
              </table>
            </div>
            <div class="card-bottom">
              <button class="bid-btn">Change My Email</button>
              <button class="bid-btn">Change my Password</button>
              <button class="bid-btn">Deposit Funds</button>
              <button class="bid-btn">Withdraw Funds</button>
            </div>
          </div>
        </div>
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