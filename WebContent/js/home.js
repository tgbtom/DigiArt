window.onload = function () {
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
  var pswd1 = document.getElementById("pswd1");
  var pswd2 = document.getElementById("pswd2");
  var pswdWarn = document.getElementById("pass-warning");
  var pageMessage = document.getElementById("page-message");
  var adminLocks = document.getElementsByClassName("admin-lock");
  var searchInput = document.getElementById("search-contains");

  if (typeof auctionHandler === "function") {
    auctionHandler();
  }

  if (pageMessage != null) {
    var msgClose = document.getElementById("msg-close");
    msgClose.addEventListener("click", function () {
      pageMessage.style.display = "none";
    });
  }

  if (pswd2 != null) {
    pswd1.addEventListener("change", function () {
      if (pswd1.value == pswd2.value) {
        pswdWarn.innerHTML = "";
      } else {
        pswdWarn.innerHTML = "*passwords must match*";
      }
    });

    pswd2.addEventListener("change", function () {
      if (pswd1.value == pswd2.value) {
        pswdWarn.innerHTML = "";
      } else {
        pswdWarn.innerHTML = "*passwords must match*";
      }
    });
  }

  if (fileUp != null) {
    fileUp.addEventListener("input", function (e) {
      console.log(fileUp);
      console.log(e);
      console.log(e.target.files[0]);

      document.getElementById("uploadedProd").src =
        "img/" + e.target.files[0].name;

      readURL(fileUp);
    });
  }

  for (let i = 0; i < adminLocks.length; i++) {
    console.log(adminLocks[i]);
    let lock = adminLocks[i];
    lock.addEventListener("click", function () {
      let userId = lock.id.substring(5, lock.id.length);
      var xhttp = new XMLHttpRequest();
      xhttp.open("POST", "LockUser");
      xhttp.setRequestHeader(
        "Content-type",
        "application/x-www-form-urlencoded"
      );
      xhttp.send("id=" + userId);
    });
  }

  if (loginBtn != null) {
    loginBtn.addEventListener("click", function () {
      imgModal.style.display = "none";
      registerModal.style.display = "none";
      fullModal.style.display = "block";
      loginModal.style.display = "block";
    });

    registerBtn.addEventListener("click", function () {
      imgModal.style.display = "none";
      loginModal.style.display = "none";
      fullModal.style.display = "block";
      registerModal.style.display = "block";
    });
  }

  for (let i = 0; i < products.length; i++) {
    products[i].addEventListener("click", function (e) {
      if (loginModal != null) {
        loginModal.style.display = "none";
        registerModal.style.display = "none";
      }
      image.src = e.target.src;
      fullModal.style.display = "block";
      imgModal.style.display = "block";
    });
  }

  exitModal.addEventListener("click", function () {
    if (loginModal != null) {
      registerModal.style.display = "none";
      loginModal.style.display = "none";
    }
    imgModal.style.display = "none";
    fullModal.style.display = "none";
  });

  var sortOption = document.getElementById("sort-by");
  if (sortOption != null) {
    sortOption.addEventListener("change", function () {
      var xhttp = new XMLHttpRequest();
      xhttp.onreadystatechange = function () {
        let container = document.getElementById("auction-container");
        container.innerHTML = this.responseText;
      };
      xhttp.open("GET", "SortAuctions?opt=" + sortOption.value, true);
      xhttp.send();
    });
  }

  if (searchInput != null) {
    searchInput.addEventListener("change", function () {
      var xhttp = new XMLHttpRequest();
      xhttp.onreadystatechange = function () {
          let container = document.getElementById("auction-container");
          container.innerHTML = this.responseText;
        };
      xhttp.open("GET", "SortAuctions?opt=search&val=" + searchInput.value, true);
      xhttp.send();
    });
  }
};

function readURL(input) {
  if (input.files && input.files[0]) {
    var reader = new FileReader();

    reader.onload = function (e) {
      $("#uploadedProd").attr("src", e.target.result);
    };

    reader.readAsDataURL(input.files[0]); // convert to base64 string
  }
}

function product(menuNum) {
  let loc = "";
  switch (menuNum) {
    case 1:
      loc = "Navigate?loc=products";
      break;
    case 2:
      loc = "Navigate?loc=uploadproduct";
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
      loc = "Navigate?loc=auctions&as=mine";
      break;
    case 2:
      loc = "Navigate?loc=auctions&as=all";
      break;
    default:
      break;
  }
  window.location.href = loc;
}
