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
<jsp:useBean id="logAuth" class="data.StaffBean" scope="session" />

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css" />
<title>Assign Staff to Incident</title>
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
	
	<!-- if user is branch manager, show this  -->
	<div class="horizonta_nav">
	  <a href="ListOfIncidents.jsp">Incidents</a>
	  <a href="CreateIncidentReport.jsp">Report</a>
	  <a href="RolesForStaff.jsp">Roles</a>
	  <a href="index.jsp">Logout</a>
	</div>
	<br>
	<br>
	
Assign Staff to Incident
<br>
<!-- 
Incident:
Incident title
Incident category
Incident date
Name of staff who reported the incident

Staff:
Staff name
Staff id
Staff position
Staff roles
 -->
Incident id:
${incidentID}
<br>
Incident title:
${incidentTitle}
<br>
Incident category:
${incidentCategory}
<br>
Incident date:
${incidentDate}
<br>
Incident description:
${incidentDescription }
<br>
Staff who reported it:
${staffName}
<br>
<br>
<br>
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
<td><input type="submit" name="${user.staffID}" value="Assign Staff"></td>
</tr>
</c:forEach>
</table>
</form>
<br>
<form action="prepareList">
<input type="submit" name="incidentsList" value="List">
</form>
</body>
</html>
