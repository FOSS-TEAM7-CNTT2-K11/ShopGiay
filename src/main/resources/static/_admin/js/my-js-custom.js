function setSalePriceNumber() {
	var range = document.getElementById("salePriceRange");
	var number = document.getElementById("salePriceNumber");

	number.value = range.value;
}
function setSalePriceRange() {
	var range = document.getElementById("salePriceRange");
	var number = document.getElementById("salePriceNumber");

	range.value = number.value;
}

function abc(i) {
	setTimeout(function() {
		var liList = document.getElementsByClassName("myli");

		var divList = document.getElementsByClassName("mydiv");
		for (var j = 0; j < liList.length; j++) {
			console.log("lan thu " + j + liList[j].classList.value + "\n")
			if (liList[j].classList.value == "myli active") {
				liList[j].classList.value = "myli";
				divList[j].classList.value = "carousel-item mydiv";
				liList[i].classList.value = "myli active";
				divList[i].classList.value = "carousel-item mydiv active";
				break;
			}
		}
	}, 200);

}

function changeColorLable(){
	console.log("Chang color lable");
	var lableList = document.getElementsByClassName("my-lable");
	
	var inputList = document.getElementsByClassName("my-input");
	for (var j = 0; j < inputList.length; j++) {
		if(inputList[j].checked){
			lableList[j].style.background = "#9dff00";
		}
		else
			lableList[j].style.background = "white";

		
	}
}