<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="data.IncidentDAO" %>
    <%@ taglib
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
<title>Insert title here</title>
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

<!-- 
Name of staff performing analysis
Incident analyzing
 -->
 
 <div class="container"> 
	 
	<h2>Analysis</h2>
	
	<table style="table-layout:fixed">
		<tr>
			<td style="background: #dddddd">Incident ID:</td>
			<td>${incidentID}</td>
		</tr>
		<tr>
			<td style="background: #dddddd">Incident title:</td>
			<td>${incidentSelected.incidentTitle}</td>
		</tr>
		
		<tr>
			<td style="background: #dddddd">Reported possible causes:</td>
			<td>${incidentSelected.possibleCausesOfIncident}</td>
		</tr>
				 
		<tr>
			<td style="background: #dddddd">Reported possible solutions:</td>
			<td>${incidentSelected.possibleSolutionsOfIncident}</td>
		</tr>
	
	</table>
	
	<br/>
	<br/>
	
	<div class="container">
	<h3>Simulations done for this incident</h3>
	<c:choose>
	<c:when test="${empty incidentSelected.simulations}">
	None
	</c:when>
	<c:otherwise>
	<c:forEach var="temp" items="${incidentSelected.simulations}">
	${temp}
	<br>
	<br>
	</c:forEach>
	</c:otherwise>
	</c:choose>
	</div>
	<br>
	<br>
	<div class="container">
		<h3>Root Cause Analysis</h3>
		<form action="postAnalysis" method="post">
		
			<div class="row">
			    <div class="col-25">
			    	<label>Root Cause found:</label>
			    </div>
			    <div class="col-75">
	    			<textarea name="causes" placeholder="Please write the root cause.." style="height:200px"></textarea>
			    </div>
			</div>
		
		<br>
		<input type="submit" name="finishAnalysis" value="Submit">
		</form>
	</div>
	<br>
	<br>
	
	<div class="container">
	
		<h3>Simulate incident with Root Cause changed</h3>		
		
		<form action="lessonsLearnt" method="post">
		
			<div class="row">
			    <div class="col-25">
			    	<label>Root cause(s) targeted:</label>
			    </div>
			    <div class="col-75">
			    	<input type="text" name="targetCause">
			    </div>
			</div>
		
			<div class="row">
			    <div class="col-25">
			    	<label>Actions taken:</label>
			    </div>
			    <div class="col-75">
			    	<input type="text" name="actions">
			    </div>
			</div>
		
			<div class="row">
			    <div class="col-25">
			    	<label>Results found:</label>
			    </div>
			    <div class="col-75">
			    	<textarea name="results" placeholder="Please write results found.." style="height:200px"></textarea>
			    </div>
			</div>

		<input type="submit" name="lessonsLearnt" value="submit">
		</form>
	</div>
	<form action="DisplayIncidentReport.jsp" method="post">
	<input type="submit" value="View Report">
	</form>
</div>
</body>
</html>
