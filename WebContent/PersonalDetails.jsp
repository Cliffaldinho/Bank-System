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
	<link rel="stylesheet" type="text/css" href="style.css" />
	<title>Personal Details</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
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
<body>

	<div class="top-banner">
	  <div class="row">
	    <div class="col-75">
	      <h1 id="attBuff">SaiYan Bank Incident Management</h1>
	    </div>
	    <div>
	      <img src="images/logo.png" alt="logo" class="logo"/>
	    </div>
	  </div>
	</div>
	
	<div class="horizonta_nav">
	  <a href="ListOfIncidents.jsp">Incidents</a>
	  <a href="CreateIncidentReport.jsp">Report</a>
	  <c:if test="${logAuth.authenticationLevel==1}">
	  	<a href="RolesForStaff.jsp">Roles</a>
	  </c:if>
	   <form>
	  	<a href="#" class="active" onclick="document.getElementById('account').submit();"> Account </a>
	  </form>	
	  <form>
	  	<a href="#" onclick="document.getElementById('logOut').submit();"> Logout </a>
	  </form>	  
	</div>

<div class="container">
	<h2>ID:${userid}</h2>
	<form action="finishPersonalDetails" method="post" onSubmit="return validateForm()">
				
		<div class="container">
			<div class="row">
				<div class="col-25">
					<label for="Edit Name">Name:</label>
				</div>
				<div class="col-75">
					<input type="text" id="nameInput"  name="modifyName" value="${staffName}" />
				</div>
			</div>
		</div>		
			
		<div class="container">
			<div class="row">
				<div class="col-25">
					<label for="Edit Name">Address:</label>
				</div>
				<div class="col-75">
					<input type="text" id="addressInput"  name="modifyAddress" value="${staffAddress}"/>
				</div>
			</div>
		</div>	
	
		<div class="container">
			<div class="row">
				<div class="col-25">
					<label for="Edit Name">Contact Number:</label>
				</div>
				<div class="col-75">
					<input type="text" id="contactInput"  name="modifyContact" value="${staffContact}"/>
				</div>
			</div>
		</div>	
		
		<div class="container">	
			
			<c:if test="${incorrectPassword}">
				<div class="col-center">
					<label for="Edit Name">
						Oops. The old password you entered was incorrect. 
						Hence your password was not edited.
					</label>
				</div>	
			</c:if>	
			
			<div class="row">
				<div class="col-25">
					<label for="Edit Name">Old Password:</label>
				</div>
				<div class="col-75">
					<input type="password" id="oldPassword" name="originalPassword" value="" />
				</div>
			</div>
			
			<div class="row">
				<div class="col-25">
					<label for="Edit Name">New Password:</label>
				</div>
				<div class="col-75">
					<input type="password" id="newPassword" name="modifyPassword" value="" />
				</div>
			</div>
			
			<div class="row">
				<div class="col-25">
					<label for="Edit Name">Re-enter New Password:</label>
				</div>
				<div class="col-75">
					<input type="password" id="reenterNewPassword" value="">
				</div>
			</div>
		</div>	
		<input type="submit" value="Modify personal details" id="submitButton" >
	</form>

</div>


<div class="container">
	<h2>Incidents assigned:</h2>
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

<div class="container">
	<form action="prepareList" method="post">
		<input type="submit" value="List">
	</form>
</div>

<form id="logOut" action="userLogout" method="post"></form>
<form id="account" action="personalDetails" method="post"></form>

</body>
</html>
