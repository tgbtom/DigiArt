<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="ISO-8859-1">
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
      <a href="" class="left">
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
        <div class="col-12">
          <p class="subtitle">Top Auctions</p>
        </div>
      </div>
      <div class="row">
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
      </div>

      <div class="row">
        <div class="col-4">
          <div class="card">
            <div class="card-top">
              <p>Product 4</p>
            </div>
            <div class="card-content">
              <img src="img/sample4.png" alt="product 4" class="product" />
            </div>
            <div class="card-bottom">
              <span class="time-left"></span>
              <a href="auctionpage.html?pid=3">
                <button class="bid-btn">
                  $1.25
                </button>
              </a>
            </div>
          </div>
        </div>
        <div class="col-4">
          <div class="card">
            <div class="card-top">
              <p>Product 5</p>
            </div>
            <div class="card-content">
              <img src="img/sample5.png" alt="product 5" class="product" />
            </div>
            <div class="card-bottom">
              <span class="time-left"></span>
              <a href="auctionpage.html?pid=3">
                <button class="bid-btn">
                  $29.00
                </button>
              </a>
            </div>
          </div>
        </div>
        <div class="col-4">
          <div class="card">
            <div class="card-top">
              <p>Product 6</p>
            </div>
            <div class="card-content">
              <img src="img/sample6.png" alt="product 6" class="product" />
            </div>
            <div class="card-bottom">
              <span class="time-left"></span>
              <a href="auctionpage.html?pid=3">
                <button class="bid-btn">
                  $31.50
                </button>
              </a>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="container">
      <div class="row">
        <div class="col-12">
          <p class="subtitle">Most Recent Auctions</p>
        </div>
      </div>
      <div class="row">
        <div class="col-4">
          <div class="card">
            <div class="card-top">
              <p>Product 7</p>
            </div>
            <div class="card-content">
              <img src="img/sample7.png" alt="product 7" class="product" />
            </div>
            <div class="card-bottom">
              <span class="time-left"></span>
              <a href="auctionpage.html?pid=3">
                <button class="bid-btn">
                  $40.30
                </button>
              </a>
            </div>
          </div>
        </div>
        <div class="col-4">
          <div class="card">
            <div class="card-top">
              <p>Product 8</p>
            </div>
            <div class="card-content">
              <img src="img/sample8.png" alt="product 8" class="product" />
            </div>
            <div class="card-bottom">
              <span class="time-left"></span>
              <a href="auctionpage.html?pid=3">
                <button class="bid-btn">
                  $18.50
                </button>
              </a>
            </div>
          </div>
        </div>
        <div class="col-4">
          <div class="card">
            <div class="card-top">
              <p>Product 9</p>
            </div>
            <div class="card-content">
              <img src="img/sample9.png" alt="product 3" class="product" />
            </div>
            <div class="card-bottom">
              <span class="time-left"></span>
              <a href="auctionpage.html?pid=3">
                <button class="bid-btn">
                  $115.00
                </button>
              </a>
            </div>
          </div>
        </div>
      </div>

      <div class="row">
        <div class="col-4">
          <div class="card">
            <div class="card-top">
              <p>Product 10</p>
            </div>
            <div class="card-content">
              <img src="img/sample10.png" alt="product 4" class="product" />
            </div>
            <div class="card-bottom">
              <span class="time-left"></span>
              <a href="auctionpage.html?pid=3">
                <button class="bid-btn">
                  $73.59
                </button>
              </a>
            </div>
          </div>
        </div>
        <div class="col-4">
          <div class="card">
            <div class="card-top">
              <p>Product 11</p>
            </div>
            <div class="card-content">
              <img src="img/sample11.png" alt="product 5" class="product" />
            </div>
            <div class="card-bottom">
              <span class="time-left"></span>
              <a href="auctionpage.html?pid=3">
                <button class="bid-btn">
                  $24.50
                </button>
              </a>
            </div>
          </div>
        </div>
        <div class="col-4">
          <div class="card">
            <div class="card-top">
              <p>Product 12</p>
            </div>
            <div class="card-content">
              <img src="img/sample12.png" alt="product 6" class="product" />
            </div>
            <div class="card-bottom">
              <span class="time-left"></span>
              <a href="auctionpage.html?pid=3">
                <button class="bid-btn">
                  $36.99
                </button>
              </a>
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