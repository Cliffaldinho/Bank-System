<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ page import="data.UserDAO" %>
    <%@ page import="data.IncidentDAO" %>

<%
	HttpSession aSession = request.getSession();
%>
         <%@taglib
    prefix="c"
    uri="http://java.sun.com/jsp/jstl/core" 
%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:useBean id="logAuth" class="data.StaffBean" scope="session" />

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css" />
<title>Assign Staff to Incident</title>
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
	
	<!-- if user is branch manager, show this  -->
	<div class="horizonta_nav">
	  <a href="prepareList">Incidents</a>
	  <a href="CreateIncidentReport.jsp">Report</a>
	  <c:if test="${logAuth.authenticationLevel==1}">
	  	<a href="RolesForStaff.jsp">Roles</a>
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
	<h2>Assign Staff to Incident</h2>
	<table style="table-layout:fixed">
		<tr>
			<td class="td2">Incident id:</td>
			<td>${incidentID}</td>
		</tr>
		<tr>
			<td class="td2">Incident title:</td>
			<td>${incidentSelected.incidentTitle}</td>
		</tr>
		<tr>
			<td class="td2">Incident category:</td>
			<td>${incidentSelected.incidentCategory.toString()}</td>
		</tr>
		<tr>
			<td class="td2">Incident date:</td>
			<td>${incidentSelected.dateTimeFromTimeStamp}</td>
		</tr>
		<tr>
			<td class="td2">Incident description:</td>
			<td>${incidentSelected.descriptionOfIncident}</td>
		</tr>
		<tr>
			<td class="td2">Staff who reported it:</td>
			<td>${incidentSelected.userReportedIncident.name}</td>
		</tr>
	</table>
	
	<br />
	
	<form action="finishAssignStaff" method="post">
	<table>
	<tr>
	<th>Staff ID</th>
	<th>Staff Name</th>
	<th>Staff Position</th>
	<th>Staff Roles</th>
	<th>Assign this staff to Incident</th>
	</tr>
	
	
	<c:forEach items="${listOfStaff}" var="user">
	<tr>
	<td><c:out value="${user.staffID}"/></td>
	<td><c:out value="${user.name}"/></td>
	<td><c:out value="${user.position}"/></td>
	<td><c:out value="${user.rolesToDo}"/></td>
	<td>
	<input type="checkbox"  name="staff" value="${user.staffID}" <c:if test="${fn:containsIgnoreCase(incidentSelected.assignedStaffIDInString,user.staffID)}">checked</c:if>>
	</td>
	</tr>
	</c:forEach>
	
	
	</table>
	<input type="submit" value="Assign Staff">
	</form>
</div>

<form id="logOut" action="userLogout" method="post"></form>
<form id="account" action="personalDetails" method="post"></form>
<form id="statistics" action="showStatistics" method="post"></form>

</body>
</html>