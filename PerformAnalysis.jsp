<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="data.IncidentDatabase" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
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
<h1>Analysis</h1>
Incident title:
<%out.println(incidentTitle); %>
<br>

Incident category: 
<%out.println(incidentCategory); %>
<br>

Incident description:
<%out.println(incidentDescription); %> 
<br>

Staff who reported this incident:
<%out.println(incidentStaffName); %>
<br>
 
Reported possible causes:
<%out.println(possibleCauses); %>
<br>
 
Reported possible solutions:
<%out.println(possibleSolutions); %>
<br>
 
Root Cause Analysis
<form action="postAnalysis" method="get">
Staff name:
<input type="text" name="staffNameWriting">
<br>
Staff id:
<input type="text" name="staffIDWriting">
<br>
Root Cause found:
<input type="text" name="causes">
<br>
<%out.println("<input type=\"hidden\" name=\"theAnalysisDatabaseIndex\" value=\""+analysisIndex+"\">"); %>
<%out.println("<input type=\"hidden\" name=\"theIncidentDatabaseIndex\" value=\""+incidentIndex+"\">"); %>
<input type="submit" name="finishAnalysis">
</form>
<br>
<br>

Simulate incident with Root Cause changed
<form action="lessonsLearnt" method="get">
Date:
<input type="text" name="date">
<br>
Root cause(s) targeted:
<input type="text" name="targetCause">
<br>
Actions taken:
<input type="text" name="actions">
<br>
Results found:
<input type="text" name="results">
<br>
<%out.println("<input type=\"hidden\" name=\"anAnalysisDatabaseIndex\" value=\""+analysisIndex+"\">"); %>
<%out.println("<input type=\"hidden\" name=\"anIncidentDatabaseIndex\" value=\""+incidentIndex+"\">"); %>
<input type="submit" name="lessonsLearnt">
</form>
<br>
<br>

List of Incidents
<form action="ListOfIncidents.jsp">
<input type="submit" name="incidentList" value="List">
</form>

</body>
</html>