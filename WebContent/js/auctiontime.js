function showTimes() {
  var timeSpans = document.getElementsByClassName("time-left");
  for (let i = 0; i < timeSpans.length; i++) {
    var d = new Date();
    var curD = new Date();
    d.setMinutes(d.getMinutes() + Math.random() * 15);
    d.setSeconds(d.getSeconds() + Math.random() * 59);

    var timeDiff = d - curD;
    if (timeDiff < 60000) {
      timeSpans[i].style.color = "red";
    }

    let dayRem = 0;
    let hourRem = 0;
    let minRem = Math.floor(timeDiff / 1000 / 60);
    let secRem = timeDiff / 1000 - minRem * 60;

    timeSpans[i].innerHTML =
      "Ends in: 00:00:" + padNum(minRem) + ":" + padNum(secRem);
  }

  function padNum(n) {
    return n < 10 ? "0" + n : n;
  }
}
