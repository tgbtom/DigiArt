<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Upload Product - DigiArt</title>

    <link
      href="https://fonts.googleapis.com/css?family=Khand"
      rel="stylesheet"
    />
    <link rel="stylesheet" href="mainStyle.css" />
    
    <script src="js/home.js"></script>
    <script src="js/auction.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  </head>
  <body>
    <nav id="navigate">
      <a href="indexLogged.html" class="left">
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
        <div class="col-12"><div class="subtitle">Upload a Product</div></div>
      </div>
      <div class="row">
        <div class="col-1"><!-- Spacer --></div>
        <div class="col-5">
          <div class="card card-float">
            <div class="card-top">
              <p>Product 1</p>
            </div>
            <div class="card-content">
              <img src="img/placeholder.png" alt="product 1" class="product" id="uploadedProd" />
            </div>
          </div>
        </div>
        <div class="col-1"><!-- spacer --></div>
        <form action="CreateProduct" method="POST" enctype="multipart/form-data" runat="server">
        <div class="col-4"><input type="file" accept="image/*" name="image" id="file-up" /></div>
        <div class="col-1"><!-- spacer --></div>
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
                      <input type="text" name="name" id="" />
                    </td>
                  </tr>
                  <tr>
                    <td>Category</td>
                    <td>
                    <%@ page import="com.fdmgroup.model.ProductCategory" %>
                    	<select name="category">
                    		<% 
                    		for(ProductCategory cat: ProductCategory.values()){
                    		%>
                    			<option value="<%= cat %>"><%= cat %></option>
                    		<%	
                    		}
                    		%>
                    	</select>
 
                    </td>
                  </tr>
                </tbody>
              </table>
            </tr>
            </div>
            <div class="card-bottom">
              <p class="desc-title">Description</p>
              <textarea name="description" id=""></textarea>
				<input class="bid-btn green-text" type="submit" value="Save Product">
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