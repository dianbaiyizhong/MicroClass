lastScrollY = 0;

var InterTime = 1;
var maxWidth = -1;
var minWidth = -815;
var numInter = 8;

var BigInter;
var SmallInter;

var WindowNode;
var WindowLeft;

$(document).ready(function() {
	WindowNode = document.getElementById("CompileWindow");
	WindowLeft = parseInt(WindowNode.style.left);
	LoadWindow('CompileWindow', 100, -815);

});

function Big() {
	if (parseInt(WindowNode.style.left) < maxWidth) {
		WindowLeft = parseInt(WindowNode.style.left);
		WindowLeft += numInter;
		WindowNode.style.left = WindowLeft + "px";
		if (WindowLeft == maxWidth)
			clearInterval(BigInter);
	}
}
function toBig() {
	clearInterval(SmallInter);
	clearInterval(BigInter);
	BigInter = setInterval(Big, InterTime);
}
function Small() {
	if (parseInt(WindowNode.style.left) > minWidth) {
		WindowLeft = parseInt(WindowNode.style.left);
		WindowLeft -= numInter;
		WindowNode.style.left = WindowLeft + "px";

		if (WindowLeft == minWidth)
			clearInterval(SmallInter);
	}
}
function toSmall() {
	clearInterval(SmallInter);
	clearInterval(BigInter);
	SmallInter = setInterval(Small, InterTime);

}

function LoadWindow(id, _top, _left) {
	var me = id.charAt ? document.getElementById(id) : id, d1 = document.body,

	d2 = document.documentElement;
	d1.style.height = d2.style.height = '100%';
	me.style.top = _top ? _top

	+ 'px' : 0;
	me.style.left = _left + "px";
	me.style.position = 'absolute';
	setInterval(function() {
		me.style.top = parseInt(me.style.top) + (Math.max

		(d1.scrollTop, d2.scrollTop) + _top - parseInt(me.style.top)) * 0.1
				+ 'px';
	}, 10 + parseInt

	(Math.random() * 20));
	return arguments.callee;
}
