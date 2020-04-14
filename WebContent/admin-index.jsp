<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Login</title>

<link rel="stylesheet" type="text/css" href="mainStyle.css">
    <link
      href="https://fonts.googleapis.com/css?family=Khand"
      rel="stylesheet"
    />
    
</head>
<body>
    <% if (request.getSession().getAttribute("message") != null){  %>
    <div class="page-message" id="page-message">
      <%= request.getSession().getAttribute("message") %>
      <img src="img/small-x.png" id="msg-close" alt="close message" />
    </div>
    <% request.getSession().removeAttribute("message");} %>

        <div class="row">
          <div class="col-2"><!-- spacer --></div>
          <div class="col-8">
            <div class="card-float card-modal">
              <div class="card-top"><h4>Admin Login</h4></div>
              <div class="card-content">
                <form action="AdminLogin" method="POST">
                  <div class="input-group">
                    <div class="col-4">
                      <label for="admin-user">Username: </label>
                    </div>
                    <div class="col-8">
                      <input
                        type="text"
                        name="admin-user"
                        id="login-username"
                        placeholder="Username"
                        required
                      />
                    </div>
                  </div>
                  <div class="input-group">
                    <div class="col-4">
                      <label for="admin-pass">Password: </label>
                    </div>
                    <div class="col-8">
                      <input
                        type="password"
                        name="admin-pass"
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
</body>
</html>