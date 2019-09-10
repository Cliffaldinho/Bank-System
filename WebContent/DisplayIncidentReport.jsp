<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="data.UserDAO" %>
    <%@ page import="data.IncidentDAO" %>
    <%@ page import="data.*" %>
     <%@ taglib
    prefix="c"
    uri="http://java.sun.com/jsp/jstl/core" 
%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
	
	<div class="container">
	
	<c:if test="${checkDuplicate}">
	The report you submitted seems to be a duplicate of this:
	</c:if>
	
		<h2>Incident Details</h2>
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
				<td style="background: #dddddd">Incident category:</td>
				<td>${incidentSelected.incidentCategory.toString()}</td>
			</tr>
		
			<tr>
				<td style="background: #dddddd">Incident date:</td>
				<td>${incidentSelected.dateTimeFromTimeStamp}</td>
			</tr>
			
			<tr>
			<td style="background: #dddddd">Incident Status</td>
			<td>${incidentSelected.incidentStatus.toString()}</td>
			</tr>
		
			<tr>
				<td style="background: #dddddd">Description of incident:</td>
				<td>${incidentSelected.descriptionOfIncident}</td>
			</tr>
		
			<tr>
				<td style="background: #dddddd">Name of staff who reported this incident:</td>
				<td>${incidentSelected.userReportedIncident.name}</td>
			</tr>
		
			<tr>
				<td style="background: #dddddd">Position of staff who reported this incident:</td>
				<td>${incidentSelected.userReportedIncident.position.toString()}</td>
			</tr>
		
			<tr>
				<td style="background: #dddddd">ID of staff who reported this incident:</td>
				<td>${incidentSelected.userReportedIncident.staffID}</td>
			</tr>
		
			<tr>
				<td style="background: #dddddd">Incident keywords:</td>
				<td>
				<c:forEach var="keyword" items="${incidentSelected.incidentKeywords}">
				${keyword}
				<br>
				</c:forEach>
				</td>
			</tr>
			
	
		
			<tr>
				<td style="background: #dddddd">Priority rating:</td>
				<td>${incidentSelected.priorityRating}</td>
			</tr>
			
			<tr>
			<td style="background: #dddddd">Staff Assigned:</td>
			<td>${incidentSelected.assignedStaffNameAndPosition}</td>
			</tr>
		
			<tr>
			<td style="background: #dddddd">Solution implemented:</td>
			<td>${incidentSelected.solutionImplemented}</td>
			</tr>
			
			<tr>
				<td style="background: #dddddd">Possible causes:</td>
				<td>${incidentSelected.postIncident.possibleCausesOfIncident}</td>
			</tr>
		
			<tr>
				<td style="background: #dddddd">Possible solutions:</td>
				<td>${incidentSelected.postIncident.possibleSolutionsOfIncident}</td>
			</tr>
			
			<tr>
				<td style="background: #dddddd">Future risk foreseen</td>
				<td>${incidentSelected.postIncident.riskForeseen}</td>
			</tr>
			
			<tr>
				<td style="background: #dddddd">Evaluation of risk</td>
				<td>${incidentSelected.postIncident.riskEvaluationAsString}</td>
			</tr>
			
			<tr>
				<td style="background: #dddddd">Strategy Implemented</td>
				<td>${incidentSelected.postIncident.strategyImplemented}</td>
			</tr>
			
			
		
		</table>
		
		<c:if test="${checkDuplicate}">
		<p>Mark the submitted incident report as duplicate of this?</p>
		<form action="detectDuplicate" method="post">
		<input type="submit" name="duplicateDecision" value="Yes"> Merge submitted report into original.
		<br>
		<input type="submit" name="duplicateDecision" value="No">  Add as new report.
		</form>
		</c:if>
		
		
		<c:if test="${not checkDuplicate}">
			<c:if test="${logAuth.authenticationLevel==1}">
				<c:if test="${incidentSelected.incidentStatus.toString()=='New Incident'||
				incidentSelected.incidentStatus.toString()=='Staff Assigned'||
				incidentSelected.incidentStatus.toString()=='Incident fixed'}">
					<form action="AssignStaffToIncident.jsp" method="post">
						<input type="submit" value="Assign Staff">
					</form>
				</c:if>
				<br>
			</c:if>
			
			<form action="updateIncidentStatus" method="post" onSubmit="return validateForm()">
			
			<!-- Test if Incident has been assigned to a staff -->
			<c:if test="${incidentSelected.incidentStatus.toString()=='Staff Assigned'}">
			<!-- Test if the user viewing this report is one of the staff assigned, or is the branch manager -->
				<c:if test="${(fn:containsIgnoreCase(incidentSelected.assignedStaffNameAndPosition,logAuth.staffName))||
				(logAuth.authenticationLevel==1)}">
					Solution implemented:
					<input type="text" name="solution" id="solutionProvided">
					<br>
					<input type="submit" name="status" id="fixedIncident" value="Incident fixed">
				</c:if>
			</c:if>
			
			<c:if test="${incidentSelected.incidentStatus.toString()=='Incident fixed'&&
			logAuth.authenticationLevel==1}">
				<input type="submit" name="status" value="Solution verified">
				<input type="submit" name="status" value="Solution not verified">
			</c:if>
			
			</form>
			
			<br>
			
			<c:if test="${incidentSelected.incidentStatus.toString()!='Strategy implemented'&&
			incidentSelected.incidentStatus.toString()!='Archived'}">
			<form action="PerformAnalysis.jsp" method="post">
				<input type="submit" value="Analysis">
			</form>
			</c:if>
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
		</c:if>
	</div>
	
	<form id="logOut" action="userLogout" method="post"></form>
	<form id="account" action="personalDetails" method="post"></form>
	<form id="statistics" action="showStatistics" method="post"></form>

<script>
function validateForm() {
	if(document.activeElement.getAttribute('value')=="Incident fixed") {
		var solved = document.getElementById('solutionProvided').value;
			if(solved=="") {
			alert("Please enter the solution.");
			return false;
			}
	}
	
	return true;
}
</script>
</body>
</html>