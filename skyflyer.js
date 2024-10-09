var myPlane;
var mySky;
var myStart;
var myWin;
var x;
var y;
var maxX;
var maxY;
var won;
var screenW;
var screenH;
var minWidth;
var startTime;
var endTime;


function init () {
	minWidth = 640;
	screenW = document.body.clientWidth;	
	won = "starting";
	myPlane = document.getElementById('airplane').style;
	mySky = document.getElementById('sky').style;
	myStart = document.getElementById('startScreen').style;
	myWin = document.getElementById('winScreen').style;

	
	myPlane.color = "black";
	myPlane.background = "none";
	myPlane.position = "absolute";
	myPlane.height = "50px";
	myPlane.width = "50px";
	
	myStart.display = "block";
	myStart.border = "thin solid black";
	myStart.width = screenW - 200;
	document.startButtonForm.startButton.focus();
	//alert ("The object of the game is to fly to the other end of the sky. " + myStart.width);
	
}
function startGame () {
	screenH = document.body.clientHeight;
	screenW = document.body.clientWidth;
	myWin.width = (screenW - 400);
	won = "notyet";
	mySky.width = screenW - 200;
	maxX = (screenW - 200 - 58);
	maxY = 290;
	x = maxX/2;
	y = maxY;
	setPos (x, y, myPlane);
	myStart.display = "none";
	mySky.display = "block";
	myPlane.display = "block";
	myWin.display = "none";
	startTime = new Date ();

}
function setPos (x, y, ref) {
	ref.left = x;
	ref.top = y;
}

function checkKey (e) {
	if (won == "notyet") {
		var KeyID = (window.event) ? event.keyCode : e.keyCode;
		//alert ("Checking key because won is " + KeyID);
		switch(KeyID) { 
			case 37:
				if (x > 0) {
					x= x - 10;
					if (x < 0) x = 0;
					setPos (x, y, myPlane);
				}
				break;
			case 38:
				if (y > 0) {
					y = y -10;
					setPos (x, y, myPlane);
					if (y <= 0) youWin ();
				}
				break;
			case 39:
				if (x < maxX) {
					x = x + 10;
					if (x > maxX) x = maxX;
					setPos (x, y, myPlane);
				}
				break;
			case 40:
				if (y < maxY) {
					y = y + 10;
					setPos (x, y, myPlane);
				}
				break;
		}
	} else {
		//alert ("Not checking keys because won is " + won);
	}
}
function youWin () {
	won = "yup";
	endTime = new Date ();
	var timeE = Math.round((endTime.getTime() - startTime.getTime())/1000);
	document.getElementById('winScreenText').innerHTML = "You win!  Your score was " + timeE  + " seconds.<br>Would you like to play again?";
	myWin.display = "block";
	document.replayButtonForm.replayButton.focus();
	//alert ("You win!  Your score was " + timeE  + " seconds.");
}

window.addEventListener("keypress", checkKey, false);
window.addEventListener("load", init, false);
