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
      <a href="" class="left">
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
              <button class="bid-btn" disabled>
                $5.15
              </button>
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
              <button class="bid-btn" disabled>
                $7.10
              </button>
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
              <button class="bid-btn" disabled>
                $3.00
              </button>
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
              <button class="bid-btn" disabled>
                $1.25
              </button>
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
              <button class="bid-btn" disabled>
                $29.00
              </button>
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
              <button class="bid-btn" disabled>
                $31.50
              </button>
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

              <button class="bid-btn" disabled>
                $40.30
              </button>
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
              <button class="bid-btn" disabled>
                $18.50
              </button>
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
              <button class="bid-btn" disabled>
                $115.00
              </button>
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
              <button class="bid-btn" disabled>
                $73.59
              </button>
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
              <button class="bid-btn" disabled>
                $24.50
              </button>
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
              <button class="bid-btn" disabled>
                $36.99
              </button>
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
                      />
                    </div>
                  </div>
                  <div class="input-group">
                    <div class="col-4">
                      <label for="lname">Last Name:</label>
                    </div>
                    <div class="col-8">
                      <input type="text" name="lname" placeholder="Last Name" />
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
