window.onload = function() {
  var loginBtn = document.getElementById("login-btn");
  var registerBtn = document.getElementById("register-btn");
  var products = document.getElementsByClassName("product");
  var fullModal = document.getElementById("full-modal");
  var imgModal = document.getElementById("image-modal-content");
  var loginModal = document.getElementById("login-modal-content");
  var registerModal = document.getElementById("register-modal-content");
  var exitModal = document.getElementById("exit-modal");
  var image = document.getElementById("img-for-modal");
  var fileUp = document.getElementById("file-up");

  if (typeof showTimes === "function") {
    showTimes();
  }

  if (typeof auctionHandler === "function") {
    auctionHandler();
  }

  if (fileUp != null) {
    fileUp.addEventListener("input", function(e) {
      console.log(fileUp);
      console.log(e);
      console.log(e.target.files[0]);

      document.getElementById("uploadedProd").src =
        "img/" + e.target.files[0].name;
    });
  }

  if (loginBtn != null) {
    loginBtn.addEventListener("click", function() {
      imgModal.style.display = "none";
      registerModal.style.display = "none";
      fullModal.style.display = "block";
      loginModal.style.display = "block";
    });

    registerBtn.addEventListener("click", function() {
      imgModal.style.display = "none";
      loginModal.style.display = "none";
      fullModal.style.display = "block";
      registerModal.style.display = "block";
    });
  }

  for (let i = 0; i < products.length; i++) {
    products[i].addEventListener("click", function(e) {
      if (loginModal != null) {
        loginModal.style.display = "none";
        registerModal.style.display = "none";
      }
      image.src = e.target.src;
      fullModal.style.display = "block";
      imgModal.style.display = "block";
    });
  }

  exitModal.addEventListener("click", function() {
    if (loginModal != null) {
      registerModal.style.display = "none";
      loginModal.style.display = "none";
    }
    imgModal.style.display = "none";
    fullModal.style.display = "none";
  });
};

function product(menuNum) {
  let loc = "";
  switch (menuNum) {
    case 1:
      loc = "products.html?cat=mine";
      break;
    case 2:
      loc = "uploadproduct.html";
      break;
    case 3:
      loc = "products.html?cat=sold";
      break;
    case 4:
      loc = "products.html?cat=bought";
      break;
    case 5:
      loc = "products.html?cat=bought";
      break;
    default:
      break;
  }

  window.location.href = loc;
}

function auction(menuNum) {
  let loc = "";
  switch (menuNum) {
    case 1:
      loc = "auctions.html?cat=mine";
      break;
    case 2:
      loc = "auctions.html?cat=all";
      break;
    default:
      break;
  }

  window.location.href = loc;
}
