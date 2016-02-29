//这个js的功能是，创建一个div，并且把这个div附加到另外一个已经存在的div上
//参数1.新建div的id  2.新建div的class  3.新建div的onclick 4.新建div的onmouseover  5.已经存在的div的id 
function CreateDivNodeAppendToAnotherDiv(NewID, NewClass, NewOnclick,
		NewOnmouseover,NewOnmouseout, ExistedID) {
	var NodeString = "<div id=" + NewID + ">";
	var Node = $(NodeString);
	Node.attr("class", NewClass);

	var ExistedNode = $("#" + ExistedID);
	Node.appendTo(ExistedNode);

	document.getElementById(NewID).setAttribute('onclick', NewOnclick);
	document.getElementById(NewID).setAttribute('onmouseover', NewOnmouseover);
	document.getElementById(NewID).setAttribute('onmouseout', NewOnmouseout);


}

function CreateSpanNodeAppendToAnotherDiv(NewID, NewClass, NewOnclick,
		NewOnmouseover,NewOnmouseout, ExistedID) {
	var NodeString = "<span id=" + NewID + ">";
	var Node = $(NodeString);
	Node.attr("class", NewClass);

	var ExistedNode = $("#" + ExistedID);
	Node.appendTo(ExistedNode);

	document.getElementById(NewID).setAttribute('onclick', NewOnclick);
	document.getElementById(NewID).setAttribute('onmouseover', NewOnmouseover);
	document.getElementById(NewID).setAttribute('onmouseout', NewOnmouseout);


}

// 这个js的功能是，创建一个input，并且把这个input附加到另外一个已经存在的div上
// 参数1.新建input的id 2.新建input的class 3.新建input的type 4.新建input的onclick 5.已经存在的div的id
// 6.value
function CreateInputNodeAppendToAnotherDiv(NewID, NewClass, NewOnclick,
		NewType, ExistedID, value) {

	var NodeString = "<input type=" + NewType + " onclick=" + NewOnclick + ">";
	var Node = $(NodeString);
	Node.attr("class", NewClass);
	Node.attr("id", NewID);
	Node.attr("value", value);

	var ExistedNode = $("#" + ExistedID);
	Node.appendTo(ExistedNode);

}

// 这个js的功能是，创建一个img，并且把这个img附加到另外一个已经存在的div上
// 参数1.新建img的id 2.新建img的class 3.新建img的src 4.新建的omclick 5.已经存在的div的id
function CreateImgNodeAppendToAnotherDiv(NewID, NewClass, NewSrc, NewOnclick,
		ExistedID) {
	var NodeString = "<img id='" + NewID + "'" + " src='" + NewSrc + "'"
			+ " onClick='" + NewOnclick + "'" + " class='" + NewClass + "'"
			+ ">";
	var Node = $(NodeString);
	var ExistedNode = $("#" + ExistedID);
	Node.appendTo(ExistedNode);

}