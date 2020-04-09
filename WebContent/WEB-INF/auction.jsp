<%@page import="com.fdmgroup.model.Product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Auction x - DigiArt</title>

    <link
      href="https://fonts.googleapis.com/css?family=Khand"
      rel="stylesheet"
    />
    <link rel="stylesheet" href="mainStyle.css" />

    <script src="js/home.js"></script>
    <script src="js/auction.js"></script>
    
    
    <%@ page import="com.fdmgroup.model.Auction" %>
    <%@ page import="com.fdmgroup.dao.JPAAuctionDao" %>
    <%@ page import="java.util.Base64" %>
    <% 
    JPAAuctionDao jad = new JPAAuctionDao();
    Auction auction = (Auction) request.getAttribute("auction");
    String base64Image = new String(Base64.getEncoder().encode(auction.getProduct().getImage()));
    %>
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
      <div class="row">
        <div class="col-1"><!-- Spacer --></div>
        <div class="col-5">
          <div class="card card-float">
            <div class="card-top">
              <p><%= auction.getProduct().getName() %></p>
            </div>
            <div class="card-content">
              <img src="data:image/png;base64,<%=base64Image%>" alt="Product Image" class="product">
            </div>
            <div class="card-bottom">
              <p>
                <strong><%= auction.getSeller().getFirstname() + " " + auction.getSeller().getLastname() %></strong> |
                <b><%= auction.getSeller().getUsername() %></b>
              </p>
            </div>
          </div>
        </div>
        <div class="col-1"><!-- Spacer --></div>
        <div class="col-4">
          <div class="card card-float">
            <div class="card-top">
              <p>Auction Details</p>
            </div>
            <div class="card-content extendable">
              <table class="table-details">
                <tbody>
                  <tr>
                    <td>Listed Price</td>
                    <td>$ <%= jad.getInitialPrice(auction) + auction.getMinIncrease() %></td>
                  </tr>
                  <tr>
                    <td>Current Bid</td>
                    <td>$ <%= jad.getHighestBid(auction).getValue() %></td>
                  </tr>
                  <tr>
                    <td>Bid Holder</td>
                    <td><%= jad.getHighestBid(auction).getBidder().getUsername() %></td>
                  </tr>
                  <tr>
                    <td>Min. Increase</td>
                    <td>$ <%= auction.getMinIncrease() %></td>
                  </tr>
                  <tr>
                    <td>Min. Bid</td>
                    <td>$ <%= jad.getHighestBid(auction).getValue() + auction.getMinIncrease() %></td>
                  </tr>
                </tbody>
              </table>
              <div class="card-bottom">
              <form action="PlaceBid" method="POST">
              <input type="hidden" name="auction_id" value="<%=auction.getAuctionId()%>">
                <span class="left">$</span
                ><input
                  type="number"
                  name="bid_amt"
                  id="bidAmt"
                  value="<%= jad.getHighestBid(auction).getValue() + auction.getMinIncrease() %>"
                  step="0.01"
                  min="<%= jad.getHighestBid(auction).getValue() + auction.getMinIncrease() %>"
                />
                <input type="submit" value="Place Bid" id="placeBid" class="bid-sm bid-dtn">
              </form>
              </div>
            </div>
          </div>
        </div>
        <div class="col-1"><!-- Spacer --></div>
      </div>
      <div class="row">
        <div class="col-2"><!-- Spacer --></div>
        <div class="col-8">
          <div class="card card-float">
            <div class="card-top">
              <p>Product Details</p>
            </div>
            <div class="card-content extandable">
              <table class="table-details">
                <tbody>
                  <tr>
                    <td>Contract Details</td>
                    <td><%= auction.getContractString() %></td>
                  </tr>
                  <tr>
                    <td>File Extension</td>
                    <td>PNG</td>
                  </tr>
                  <tr>
                    <td>Category</td>
                    <td><%= auction.getProduct().getCategory() %></td>
                  </tr>
                </tbody>
              </table>
            </div>
            <div class="card-bottom">
              <p class="desc-title">Comments from Seller</p>
              <p class="desc-content">
					<%= auction.getProduct().getDescription() %>
              </p>
            </div>
          </div>
        </div>
        <div class="col-2"><!-- Spacer --></div>
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