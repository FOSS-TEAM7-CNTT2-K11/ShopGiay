function setChecked( index = 0){
	document.getElementsByClassName("color-group")[index].children[0].checked = true;
}

function getSizeIndex(){
	var selectedSize = document.getElementById("selectSize").selectedIndex;
	return selectedSize;
}

function setColor(){
	var selectedSizeIndex = getSizeIndex();
	var colorList = document.getElementsByClassName("color-group");
	for ( var i = 0; i <  colorList.length; i++) {
		if(i == selectedSizeIndex){
			console.log("pic: "  + i);
			colorList[i].style.display = "inline";
		} else{
			console.log(i);

			colorList[i].style.display = "none";
		}
	}
	setChecked(selectedSizeIndex);
}


function changeImages(path){
	console.log("da vao change image");
	document.getElementById("mainIamge").src=path;
}


