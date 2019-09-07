<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="data.UserDAO" %>
    <%@ page import="data.IncidentDAO" %>   
             <%@taglib
    prefix="c"
    uri="http://java.sun.com/jsp/jstl/core" 
%>
    
<%
       	HttpSession aSession = request.getSession();
       %>
<jsp:useBean id="logAuth" class="data.StaffBean" scope="session" /> 


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css" />
<title>Roles for Staff</title>
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
</head>
<body onload="checkNotifications();">
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
	  	<a href="RolesForStaff.jsp" class="active">Roles</a>
	  </c:if>
	  <form>
	  	<a href="#" onclick="document.getElementById('statistics').submit();"> Statistics </a>
	  </form>	
	  <form>
	  	<a href="#" onclick="document.getElementById('account').submit();"> Account </a>
	  </form>	
	  <form>
	  	<a href="#" onclick="document.getElementById('logOut').submit();"> Logout </a>
	  </form>	  
	</div>
	
	<div class="container">	
		<h2>View Staff</h2>
		
		<!-- Staff name, Staff position, Staff id -->

		<form name="staffRole" action="defineRolesForStaff" method="post">
			<table>
				<tr>
					
					<th>
						Staff ID
					</th>
					<th>
						Staff name
					</th>
					<th>
						Staff position
					</th>
					<th>
						Current roles
					</th>
					<th>
						Modify staff/role
					</th>
					<th>
						Delete staff
					</th>
				</tr>
				
				<c:forEach items="${listOfStaff }" var="user">
				<tr>
					<td>
						<c:out value="${user.staffID}"/>
					</td>
					<td>
						<c:out value="${user.name}"/>
					</td>

					<td>
						<c:out value="${user.position}"/>
					</td>
					<td>
						<c:out value="${user.rolesToDo}"/>
					</td>
					<td>
					<input type="submit" value="Modify Staff Role" id="Modify" onClick="userClicked('${user.staffID}',this.id)">
					</td>
					<td>
					<input type="submit" value="Delete User" id="Delete" onClick="userClicked('${user.staffID}',this.id)">
					</td>
				</tr>
				</c:forEach>
			</table>
		<input type="hidden" name="userChosen" id="storeUser" value="three"  > 
		<input type="hidden" name="actionChosen" id="storeAction" value="five">
		</form>
		
		<form action="CreateUser.jsp">
			<input type="submit" name="newUser" value="Create New User">
		</form>
				
		<!-- 
			Naneth: I removed the button to go to the incident list coz we already
					have it in the horizontal navigation at the top of the page. 
													
					<form action="ListOfIncidents.jsp">
						<input type="submit" name="theIncidentsList" value="List">
					</form>
		-->
	</div>
	
	<form id="logOut" action="userLogout" method="post"></form>
	<form id="account" action="personalDetails" method="post"></form>
	<form id="statistics" action="showStatistics" method="post"></form>
	
<script>
function userClicked(staff,action) {
	var staffID=staff;
	var actionOnStaff=action;
	
	document.getElementById("storeUser").value= staffID;
	document.getElementById("storeAction").value= actionOnStaff;
}
</script>
</body>
</html>
