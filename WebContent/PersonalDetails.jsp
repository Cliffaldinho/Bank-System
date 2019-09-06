<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    	<%@ page import="java.util.*" %>
    <%@ page import="data.UserDAO" %>
    <%@ page import="data.IncidentDAO" %>
    <%@ page import = "data.UserBean" %>
    <%@ page import = "data.*" %>
         <%@taglib
    prefix="c"
    uri="http://java.sun.com/jsp/jstl/core" 
%>
<jsp:useBean id="logAuth" class="data.StaffBean" scope="session" />
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script type="text/javascript">
	function checkNotifications(){
		setInterval(checkForNotifications, 10000);
	}
	
	function checkForNotifications(){
		$.ajax({
			url: "notifications",
			type: "post",
			data: "<c:out value="${logAuth.username}"/>",
			success: function(results) {
				
				if(results!="") {
				alert(results); 
				}
			}
	});
	}
</script>
<script src="ModifyPersonalDetails.js"></script>
<style>
/*id is one element, class can identify more than one */


.hidden {
visibility:hidden;
}

.visible {
visibility:visible;
}

</style>
</head>
<body onload="checkNotifications();">



<p>
ID:${userid}
</p>
<form action="finishPersonalDetails" method="post" onSubmit="return validateForm()">
<p>
Name:${staffName}
<br>
<input  type="button" value="Edit Name" id="name" ></input>
<input class="name hidden" type="text" id="nameInput"  name="modifyName">
</p>

<p>
Address:${staffAddress}
<br>
<input  type="button" value="Edit Address" id="address" ></input>
<input class="address hidden" type="text" id="addressInput"  name="modifyAddress">
</p>

<p>
Contact Number:${staffContact}
<br>
<input  type="button" value="Edit Contact Number" id="contact" ></input>
<input class="contact hidden" type="text" id="contactInput"  name="modifyContact">
</p>
<c:if test="${incorrectPassword}">
<p>
Oops. The old password you entered is incorrect. Hence your password was not edited.
</p>
</c:if>
<p>
Password
<br>
<input type="button" value="Edit Password" id="password"></input>
</p>
<p class="password hidden" id="passwordInput" >
Please enter old password:
<input type="password" id="oldPassword" name="originalPassword" value="">
<br>
<br>
Please enter new password:
<input type="password" id="newPassword" name="modifyPassword" value="">
<br>
Please reenter new password:
<input type="password" id="reenterNewPassword" value="">
<br>
</p>
<p><input type="submit" value="Modify personal details" style="visibility: hidden" id="submitButton" ></p>
</form>
<div>
Incidents assigned:

<table>
<tr>
<th>Incident ID</th>
<th>Incident Title</th>
</tr>

<c:forEach items="${logAuth.assignedIncidents}" var = "assigned">
<tr>
<td>${assigned.incidentID}</td>
<td>${assigned.incidentTitle}</td>
</tr>
</c:forEach>


</table>

</div>


<form action="prepareList" method="post">
<input type="submit" value="List">
</form>
</body>
</html>