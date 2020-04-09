<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Create Auction - DigiArt</title>

    <link
      href="https://fonts.googleapis.com/css?family=Khand"
      rel="stylesheet"
    />
    <link rel="stylesheet" href="mainStyle.css" />

    <script src="js/home.js"></script>
    <script src="js/auction.js"></script>
    
   	<%@ page import="com.fdmgroup.model.Product" %>
   	<%@ page import="com.fdmgroup.dao.JPAProductDao" %>
   	<%@ page import="java.util.Base64" %>
   	<% 
   	JPAProductDao jpd = new JPAProductDao();
   	Product product = (Product) request.getAttribute("product");
   	String base64String = new String(Base64.getEncoder().encode(product.getImage()));
   	%>
    
  </head>
  <body>
    <nav id="navigate">
      <a href="indexLogged.html" class="left">
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
              <p><%= product.getName() %></p>
            </div>
            <div class="card-content">
            	<img src="data:img/png;base64,<%= base64String %>" alt="Product Image" class="product">
            </div>
          </div>
        </div>
        <div class="col-1"><!-- Spacer --></div>
        <div class="col-4">
          <div class="card card-float">
            <div class="card-top">
              <p>Auction Details</p>
            </div>
            <form action="CreateAuction" method="POST">
            <input type="hidden" name="product-id" value="<%=product.getProduct_id()%>">
            <div class="card-content extendable">
              <table class="table-details">
                <tbody>
                  <tr>
                    <td>Start Price ($CAD)</td>
                    <td>
                      <input
                        type="number"
                        class="create-num price"
                        name="start-price"
                        step="0.01"
                        value="1.00"
                        min="1.00"
                      />
                    </td>
                  </tr>
                  <tr>
                    <td>Min. Increase</td>
                    <td>
                      <input
                        type="number"
                        class="create-num price"
                        name="min-increase"
                        step="0.01"
                        value="0.10"
                        min="0.01"
                      />
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
            <div class="card-bottom">
              <div class="input-group group-expands">
                <label for="change-start">Custom <u>Start Date/Time</u></label>
                <input type="checkbox" name="change-start" id="change-start" />
                <div class="hidden-field" id="start-time-box">
                
                <%@ page import="java.time.LocalDateTime" %>
                <% 
                LocalDateTime d1 = LocalDateTime.now();
        		String time = d1.getHour() + ":" + d1.getMinute();
        		%>
                
                  <input type="date" name="start-date" id="start-date" class="datetime" />
                  <input type="time" name="start-time" id="start-time" value="<%=time %>" class="datetime" />
                </div>
              </div>

              <div class="input-group">
                <label for="end-date"><u>End Date/Time</u></label
                ><br />
                <input
                  type="date"
                  name="end-date"
                  id="end-date"
                  class="datetime"
                />
                <input type="time" name="end-time" id="end-time" value="<%=time %>" class="datetime" />
                <p>* default auction will be 24 hours long*</p>
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
                    <td>
                      <select name="contract">
                        <option value="unlimited" default>Exclusive / Permanent</option>
                        <option value="ten" default>Exclusive / 10 Years</option>
                        <option value="five" default>Exclusive / 5 Years</option>
                        <option value="three" default>Exclusive / 3 Years</option>
                        <option value="one" default>Exclusive / 1 Year</option>
                      </select>
                    </td>
                  </tr>
                  <tr>
                    <td>File Extension</td>
                    <td>PNG</td>
                  </tr>
                  <tr>
                    <td>Dimensions</td>
                    <td>640 x 656</td>
                  </tr>
                </tbody>
              </table>
            </div>
            <div class="card-bottom">
              <p class="desc-title">Comments from Seller</p>
              <textarea name="" id=""></textarea>

              <button class="bid-btn green-text">
                Create Auction
              </button>
              </form>
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