
function validateForm() {
	//alert("password validation");
	var oldPassword=document.getElementById("oldPassword").value;
	var modifyPassword=document.getElementById("newPassword").value;
	var checkModifyPassword=document.getElementById("reenterNewPassword").value;
	
	var a= isBlank(oldPassword);
	var b=isBlank(modifyPassword);
	var c= isBlank(checkModifyPassword);
	
	//if one of the password fields are entered, make sure the other two are also entered
	if((a===false||b===false||c===false)&&(a===true||b===true||c===true)) {
		alert("Please fill all three password fields.");
		return false;
	}
	
	
	//alert("Password is "+modifyPassword);
	//alert("Reenter password is "+checkModifyPassword);
	
	//check if new password and re-entered new password match up
	if(modifyPassword!==checkModifyPassword) {
		alert("The new passwords don't match up. Please re-enter them.");
	return false;
	} 
	
	
	
}

function isBlank(checkString) {
	//if string is "", or has only whitespace in it
	if((checkString.length === 0)||(checkString.trim().length===0)) {
		
		//return true
		return true;
	} else {
		
		//else return false
		return false;
	}
}

$(function() {
	
	$("#name").click(nameClick);
	$("#address").click(addressClick);
	$("#contact").click(contactClick);
	$("#password").click(passwordClick);

})

function passwordClick() {
	//alert("Clicked password");
	
	//makes the input field disappear or appear
	$(".password").toggleClass("hidden visible");
	
	//makes the submit button appear if needed
	submitButtonVisibility();
	
	//make sure that if inputted field is set as hidden, any inputted text will be deleted 
	if($("#passwordInput").css("visibility")==="hidden") {
		document.getElementById("newPassword").value="";
		document.getElementById("reenterNewPassword").value="";
		document.getElementById("oldPassword").value="";
		}
	
}

function nameClick() {
	//alert("Clicked name");
	
	//makes the input field disappear or appear
	$(".name").toggleClass("hidden visible");
	
	
	//makes the submit button appear if needed
	submitButtonVisibility();
	
	//this erases user input if they decide to enter text, then hide the field
	if($("#nameInput").css("visibility")==="hidden") {
	document.getElementById("nameInput").value="";
	}

}

function addressClick() {
	//alert("Clicked address");
	
	//makes the input field disappear or appear
	$(".address").toggleClass("hidden visible");
	
	//makes the submit button appear if needed
	submitButtonVisibility();
	
	//this erases user input if they decide to enter text, then hide the field
	if($("#addressInput").css("visibility")==="hidden") {
		document.getElementById("addressInput").value="";
		}
}

//clicking on edit contact button 
function contactClick() {
	//alert("Clicked contact");
	
	//makes the input field disappear or appear
	$(".contact").toggleClass("hidden visible");
	
	//makes the submit button appear if needed
	submitButtonVisibility();
	
	//this erases user input if they decide to enter text, then hide the field
	if($("#contactInput").css("visibility")==="hidden") {
		document.getElementById("contactInput").value="";
		}
}

//if at least one of the edit fields are available for user to input, then set submit button to visible
//else set the submit button to hidden
function submitButtonVisibility() {
	
	if(($("#nameInput").css("visibility")==="hidden")
			&&($("#addressInput").css("visibility")==="hidden")
			&&($("#contactInput").css("visibility")==="hidden")
			&&($("#passwordInput").css("visibility")==="hidden")) {
   
	   document.getElementById("submitButton").style.visibility="hidden";
	   //alert("all are hidden");
    } else{
    	document.getElementById("submitButton").style.visibility="visible";
    	//alert("some are visible");
    }
    

}
