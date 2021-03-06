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
			<td>${incidentSelected.postIncident.possibleCausesOfIncident}</td>
		</tr>
				 
		<tr>
			<td style="background: #dddddd">Reported possible solutions:</td>
			<td>${incidentSelected.postIncident.possibleSolutionsOfIncident}</td>
		</tr>
	
	</table>
	
	<br/>
	<br/>
	
	<div class="container">
	<h3>Simulations done for this incident</h3>
	<c:choose>
	<c:when test="${empty incidentSelected.postIncident.simulations}">
	None
	</c:when>
	<c:otherwise>
	<c:forEach var="temp" items="${incidentSelected.postIncident.simulations}">
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
	<br>
		<!-- Test if person logged in is general staff during period when strategy is implemented, or if person is branch manager -->
		<c:if test="${(logAuth.authenticationLevel!=1&&incidentSelected.postIncident.strategyImplementedAlready==true)||
		logAuth.authenticationLevel==1}">
			<!-- Test if incident is in Analysis phase or Strategy phase or Archived phase -->
			<c:if test="${incidentSelected.incidentStatus.toString()=='Undergoing analysis'||
			incidentSelected.incidentStatus.toString()=='Strategy implemented'||
			incidentSelected.incidentStatus.toString()=='Archived'}">
				<form action="ImplementStrategy.jsp" method="post">
					<input type="submit" value="Strategy"> 
					<!--  <input type="button" value="Strategy" onclick="window.open('ImplementStrategy.jsp')">-->
				</form>
			</c:if>
		</c:if>
</div>

<form id="logOut" action="userLogout" method="post"></form>
<form id="account" action="personalDetails" method="post"></form>
<form id="statistics" action="showStatistics" method="post"></form>

</body>
</html>