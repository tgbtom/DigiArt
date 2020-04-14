function auctionHandler() {
  var bidBtn = document.getElementById("placeBid");
  var bidAmt = document.getElementById("bidAmt");

  var prices = document.getElementsByClassName("price");
  var startDate = document.getElementById("start-date");
  var endDate = document.getElementById("end-date");
  var endTime = document.getElementById("end-time");
  if (bidBtn != null) {
    bidBtn.addEventListener("click", function() {
      confirm("Are you sure you want to place this bid?" + bidAmt.value);
    });

    bidAmt.addEventListener("focusout", function() {
      var value = parseFloat(bidAmt.value).toFixed(2);
      bidAmt.value = value;
    });
  }

  if (startDate != null) {
    var curDate = new Date();
    startDate.value =
      curDate.getFullYear() +
      "-" +
      padNum(curDate.getMonth() + 1) +
      "-" +
      padNum(curDate.getDate());

    startDate.min =
      curDate.getFullYear() +
      "-" +
      padNum(curDate.getMonth() + 1) +
      "-" +
      padNum(curDate.getDate());

    startDate.max =
      curDate.getFullYear() +
      5 +
      "-" +
      padNum(curDate.getMonth() + 1) +
      "-" +
      padNum(curDate.getDate());

    endDate.value =
      curDate.getFullYear() +
      "-" +
      padNum(curDate.getMonth() + 1) +
      "-" +
      padNum(curDate.getDate() + 1);

    endDate.min =
      curDate.getFullYear() +
      "-" +
      padNum(curDate.getMonth() + 1) +
      "-" +
      padNum(curDate.getDate());

    endDate.max =
      curDate.getFullYear() +
      5 +
      "-" +
      padNum(curDate.getMonth() + 1) +
      "-" +
      padNum(curDate.getDate());

    var changeStart = document.getElementById("change-start");
    var timeBox = document.getElementById("start-time-box");

    changeStart.addEventListener("change", function() {
      console.log("hi");
      if (timeBox.style.display == "block") {
        timeBox.style.display = "none";
      } else {
        timeBox.style.display = "block";
      }
    });

    for (let i = 0; i < prices.length; i++) {
      prices[i].addEventListener("focusout", function() {
        var value = parseFloat(prices[i].value).toFixed(2);
        prices[i].value = value;
      });
    }
  }

  function padNum(n) {
    return n < 10 ? "0" + n : n;
  }
}
