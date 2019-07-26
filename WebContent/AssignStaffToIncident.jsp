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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
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
	  <a href="prepareList">Incidents</a>
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
${incidentSelected.incidentTitle}
<br>
Incident category:
${incidentSelected.incidentCategory}
<br>
Incident date:
${incidentSelected.dateTimeFromTimeStamp}
<br>
Incident description:
${incidentSelected.descriptionOfIncident}
<br>
Staff who reported it:
${incidentSelected.userReportedIncident.name}
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
<td><input type="submit" value="Assign Staff"  onClick="staffClicked('${user.staffID}')"></td>
</tr>
</c:forEach>
</table>
<input type="hidden" name="clicked" id="storeStaffClicked" value="three"  > 
</form>
<script>
//alert("one");
function staffClicked(id) {
	var staff=id;
	//alert("Pass is "+staff);
	document.getElementById("storeStaffClicked").value=staff;
	//alert(document.getElementById("storeStaffClicked").value);
}
</script>
</body>
</html>
