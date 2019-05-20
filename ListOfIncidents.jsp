<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="data.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css" />
<title>View List of Incidents</title>
</head>
<body>
<%
int incidentsListSize=IncidentDatabase.getIncidentsList().size();
int branchManagerAuthorization=1;
%>
<!-- 
List to have
1. Incident title
2. Incident category
3. Incident date
4. Name of Staff who reported the incident
5. Position of Staff who reported the incident
6. Incident Keywords
-->
<form action="displayIncidentReport" method="get">
<table>
<tr>
<th>Number</th>
<th>Incident Title</th>
<th>Incident Category</th>
<th>Incident Day</th>
<th>Incident Month</th>
<th>Incident Year</th>
<th>Staff who reported it</th>
<th>Staff position</th>
<th>Main three keywords</th>
<th>View More Details</th>
<%if(branchManagerAuthorization==1) { %>
<th>Handle Incident</th>
<%} %>
</tr>
<% for(int i=0;i<incidentsListSize;i++) {
	int number=i+1;
	
	String firstKeyword,secondKeyword,thirdKeyword;
	
	firstKeyword="N/A";
	secondKeyword="N/A";
	thirdKeyword="N/A";
	
	String keywords="N/A";
	
	firstKeyword=IncidentDatabase.getIncidentsList().get(i).getIncidentKeywords()[0];
	keywords=firstKeyword;
	
	if(IncidentDatabase.getIncidentsList().get(i).getIncidentKeywords().length>1) {
		secondKeyword=IncidentDatabase.getIncidentsList().get(i).getIncidentKeywords()[1];
		keywords=firstKeyword+", "+secondKeyword;
	}
	
	if(IncidentDatabase.getIncidentsList().get(i).getIncidentKeywords().length>2) {
		thirdKeyword=IncidentDatabase.getIncidentsList().get(i).getIncidentKeywords()[2];
		keywords=firstKeyword+", "+secondKeyword+", "+thirdKeyword;
	}
	
	String incidentMarker="Show"+i; 
	String handleIncident="Handle"+i;
%>
<tr>
<td><%out.println(number); %></td>
<td><%out.println(IncidentDatabase.getIncidentsList().get(i).getIncidentTitle()); %></td>
<td><%out.println(IncidentDatabase.getIncidentsList().get(i).getIncidentCategory()); %></td>
<td><%out.println(IncidentDatabase.getIncidentsList().get(i).getIncidentDateOfMonth()); %></td>
<td><%out.println(IncidentDatabase.getIncidentsList().get(i).getIncidentMonth()); %></td>
<td><%out.println(IncidentDatabase.getIncidentsList().get(i).getIncidentYear());%></td>
<td><%out.println(IncidentDatabase.getIncidentsList().get(i).getUserReportedIncident().getName());%></td>
<td><%out.println(IncidentDatabase.getIncidentsList().get(i).getUserReportedIncident().getPosition());%></td>
<td><%out.println(keywords); %></td>
<td><%out.println("<input type=\"submit\" name=\""+incidentMarker+"\" value=\"View Incident\">"); %></td>
<%if(branchManagerAuthorization==1) { %>
<td><%out.println("<input type=\"submit\" name=\""+handleIncident+"\" value=\"Handle Incident\">"); %></td>
<%} %>


</tr>
<%} %>

</table>
</form>
<br>


<form action="CreateIncidentReport.jsp">
<label for="addIncident">Add an Incident</label>
<input type="submit" name="addIncident" value="Add">
</form>
<br>

Search Incidents
<form action="searchIncidentReport" method="get">
  <select id="searchTopic">
    <option value="Incident">Incident</option>
    <option value="RootCause">Root Cause</option>
    <option value="Keywords">Keywords</option>
    <option value="Category">Category</option>
  </select>
  <input type="text" id="search" placeholder="Search..">
  <input type="submit">
</form>
<br>

<form>
<label for="sort">Sort Incidents</label>
<input name="sort">
</form>
<br>

<form>
<label for="count">Count Incidents</label>
<input name="count">
</form>
<br>
<% 

if(branchManagerAuthorization==1) {
%>
View Staff Roles
<form action="RolesForStaff.jsp">
<input type="submit" name="viewRoles" value="View Roles">
</form>
<%} %>
<table>
</table>
</body>
</html>
