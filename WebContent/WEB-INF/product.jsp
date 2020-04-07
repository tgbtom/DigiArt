<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Edit Product</title>

    <link
      href="https://fonts.googleapis.com/css?family=Khand"
      rel="stylesheet"
    />
    <link rel="stylesheet" href="mainStyle.css" />

    <script src="js/home.js"></script>
    <script src="js/auction.js"></script>
    
    
    <%@ page import="com.fdmgroup.model.Product" %>
    <%@ page import="java.util.Base64" %>
    <% 
    Product product = (Product) request.getAttribute("product"); 
    String imageBase64 = new String(Base64.getEncoder().encode(product.getImage()));
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
        <div class="col-3"><!-- Spacer --></div>
        <div class="col-6">
          <div class="card card-float">
            <div class="card-top">
              <p><%= product.getName() %></p>
            </div>
            <div class="card-content">
              <img src="data:image/png;base64,<%= imageBase64 %>" class="product" alt="Product Image"/>
            </div>
          </div>
        </div>
        <div class="col-3"><!-- Spacer --></div>
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
                        <td>Product Name</td>
                        <td>
                            <input type="text" name="name" value="<%= product.getName() %>" />
                        </td>
                        </tr>
                        <td>Tags (Separated by ";")</td>
                        <td><input type="text" name="" id="" /></td>
                        </tr>
                    </tbody>
                    </table>
                </tr>
                </div>
                <div class="card-bottom">
                    <p class="desc-title">Description</p>
                    <textarea name="" id=""><%= product.getDescription() %></textarea>
    
                    <button class="bid-btn green-text">
                    Update Product
                    </button>
                </div>
              </div>
          </div>
        <div class="col-2"><!-- Spacer --></div>
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