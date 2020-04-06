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

    <script src="js/auctiontime.js"></script>
    <script src="js/home.js"></script>
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
        <div class="col-12">
          <p class="subtitle">My Products</p>
        </div>
      </div>
      <div class="row">
        <div class="col-4">
          <div class="card">
            <div class="card-top">
              <p><a href="Navigate?loc=product">Product 1</a></p>
            </div>
            <div class="card-content">
              <img src="img/sample1.png" alt="product 1" class="product" />
            </div>
            <div class="card-bottom">
              <a href="createauction.html?pid=3">
                <button class="bid-btn green-text">
                  Start Auction
                </button>
              </a>
            </div>
          </div>
        </div>
        <div class="col-4">
          <div class="card">
            <div class="card-top">
              <p><a href="product.html">Product 2</a></p>
            </div>
            <div class="card-content">
              <img src="img/sample2.png" alt="product 2" class="product" />
            </div>
            <div class="card-bottom">
              <a href="auctionpage.html?pid=3">
                <button class="bid-btn red-text">
                  <span class="time-left"></span>
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
              <a href="auctionpage.html?pid=3">
                <button class="bid-btn red-text">
                  <span class="time-left"></span>
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
              <a href="createauction.html?pid=3">
                <button class="bid-btn green-text">
                  Start Auction
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
              <a href="auctionpage.html?pid=3">
                <button class="bid-btn red-text">
                  <span class="time-left"></span>
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
              <a href="createauction.html?pid=3">
                <button class="bid-btn green-text">
                  Start Auction
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
              <p>Product 9</p>
            </div>
            <div class="card-content">
              <img src="img/sample9.png" alt="product 4" class="product" />
            </div>
            <div class="card-bottom">
              <a href="createauction.html?pid=3">
                <button class="bid-btn green-text">
                  Start Auction
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
              <a href="auctionpage.html?pid=3">
                <button class="bid-btn red-text">
                  <span class="time-left"></span>
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
              <img src="img/sample8.png" alt="product 6" class="product" />
            </div>
            <div class="card-bottom">
              <a href="createauction.html?pid=3">
                <button class="bid-btn green-text">
                  Start Auction
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