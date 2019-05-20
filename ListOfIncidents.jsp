<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="data.UserDatabase" %>
    <%@ page import="data.IncidentDatabase" %>
    <%@ page import = "data.User" %>
    <%@ page import = "data.Incident" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View List of Incidents</title>
</head>
<body>
<%
int incidentsListSize=IncidentDatabase.getIncidentsList().size();
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
<th>Handle Incident Use Case</th>
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
<td><input type="submit" name="<% out.println(i);%>" value="Handle Incident"></td>


</tr>
<%} %>

</table>
</form>

<form action="CreateIncidentReport.jsp">
<input type="submit" name="addIncident" value="Add">
</form>
<br>

<form>
<input name="search">
</form>
<br>

<form>
<input name="sort">
</form>
<br>

<form>
<input name="count">
</form>
<table>
</table>
</body>
</html>
