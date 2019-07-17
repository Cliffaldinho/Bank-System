<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="data.IncidentDatabase" %>

<%HttpSession aSession = request.getSession();%>
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
	  <a href="ListOfIncidents.jsp">Incidents</a>
	  <a href="CreateIncidentReport.jsp">Report</a>
	  <a href="RolesForStaff.jsp">Roles</a>
	  <a href="index.jsp">Logout</a>
	</div>
	<br>
	<br>

<%
int incidentIndex;
int analysisIndex;
incidentIndex=(int) request.getAttribute("incidentDatabaseIndex");
analysisIndex=(int) request.getAttribute("analysisDatabaseIndex");

String incidentTitle,incidentDescription,incidentCategory,incidentStaffName;
incidentTitle=IncidentDatabase.getIncidentsList().get(incidentIndex).getIncidentTitle();
incidentCategory=IncidentDatabase.getIncidentsList().get(incidentIndex).getIncidentCategory().toString();
incidentDescription=IncidentDatabase.getIncidentsList().get(incidentIndex).getDescriptionOfIncident();
incidentStaffName=IncidentDatabase.getIncidentsList().get(incidentIndex).getUserReportedIncident().getName();

String possibleCauses,possibleSolutions;
possibleCauses=IncidentDatabase.getIncidentsList().get(incidentIndex).getPossibleCausesOfIncident();
possibleSolutions=IncidentDatabase.getIncidentsList().get(incidentIndex).getPossibleSolutionsOfIncident();


%>
<!-- 
Name of staff performing analysis
Incident analyzing
 -->
 
 <div class="container"> 
	 
	<h2>Analysis</h2>
	
	<table style="table-layout:fixed">
	
		<tr>
			<td style="background: #dddddd">Incident title:</td>
			<td><%out.println(incidentTitle); %></td>
		</tr>
		
		<tr>
			<td style="background: #dddddd">Incident category:</td>
			<td><%out.println(incidentCategory); %></td>
		</tr>

		<tr>
			<td style="background: #dddddd">Incident description:</td>
			<td><%out.println(incidentDescription); %> </td>
		</tr>
		
		<tr>
			<td style="background: #dddddd">Staff who reported this incident:</td>
			<td><%out.println(incidentStaffName); %></td>
		</tr>

		<tr>
			<td style="background: #dddddd">Reported possible causes:</td>
			<td><%out.println(possibleCauses); %></td>
		</tr>
				 
		<tr>
			<td style="background: #dddddd">Reported possible solutions:</td>
			<td><%out.println(possibleSolutions); %></td>
		</tr>
				
	</table>
	
	<br/>
	<br/>
	
	<div class="container">
		<h3>Root Cause Analysis</h3>
		<form action="postAnalysis" method="post">
			<div class="row">
			    <div class="col-25">
			    	<label>Staff name:</label>
			    </div>
			    <div class="col-75">
			    	<input type="text" name="staffNameWriting">
			    </div>
			</div>
			<div class="row">
			    <div class="col-25">
			    	<label>Staff id:</label>
			    </div>
			    <div class="col-75">
			    	<input type="text" name="staffIDWriting">
			    </div>
			</div>
			<div class="row">
			    <div class="col-25">
			    	<label>Root Cause found:</label>
			    </div>
			    <div class="col-75">
	    			<textarea name="causes" placeholder="Please write the root cause.." style="height:200px"></textarea>
			    </div>
			</div>
		
		<br>
		<%out.println("<input type=\"hidden\" name=\"theAnalysisDatabaseIndex\" value=\""+analysisIndex+"\">"); %>
		<%out.println("<input type=\"hidden\" name=\"theIncidentDatabaseIndex\" value=\""+incidentIndex+"\">"); %>
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
			    	<label>Date:</label>
			    </div>
			    <div class="col-75">
			    	<input type="text" name="date">
			    </div>
			</div>
		
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

		<%out.println("<input type=\"hidden\" name=\"anAnalysisDatabaseIndex\" value=\""+analysisIndex+"\">"); %>
		<%out.println("<input type=\"hidden\" name=\"anIncidentDatabaseIndex\" value=\""+incidentIndex+"\">"); %>
		<input type="submit" name="lessonsLearnt" value="submit">
		</form>
	</div>
</div>
</body>
</html>
