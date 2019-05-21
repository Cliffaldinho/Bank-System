<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	<%@ page import="java.util.*" %>
    <%@ page import="data.UserDatabase" %>
    <%@ page import="data.IncidentDatabase" %>
    <%@ page import = "data.User" %>
    <%@ page import = "data.Incident" %>
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
<%ArrayList<Integer> searchList = (ArrayList<Integer>)request.getAttribute("listOfSearchIndexes");
ArrayList<Integer> sortList = (ArrayList<Integer>)request.getAttribute("sortReportsIndexes");
int searchSize = -1;
int sortSize = -1;
if(searchList!=null)
	searchSize = searchList.size();
if(sortList!=null)
	sortSize = sortList.size();

//COUNT
if(searchList==null) {
	if(IncidentDatabase.getIncidentsList().size()==1)
		out.println(IncidentDatabase.getIncidentsList().size() + " incident");
	else
		out.println(IncidentDatabase.getIncidentsList().size() + " incidents");
} else if(searchList.size()==0) {
	out.println("No search results to show.");
} else {
	if(searchList.size()==1)
		out.println(searchList.size() + " search result");
	else
		out.println(searchList.size() + " search results");
}%>
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
	<th>Staff Assigned</th>
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
	
	String theStaffAssigned,setNoStaff;
	String tempStaffAssigned;
	setNoStaff="No staff assigned";
	tempStaffAssigned=IncidentDatabase.getIncidentsList().get(i).getIdOfStaffAssigned();
	if(tempStaffAssigned==null) {
		theStaffAssigned=setNoStaff;
	} else {
		
		theStaffAssigned=UserDatabase.findUserObjectByStaffID(tempStaffAssigned).getName();
	}
%>
	<%
	if(searchList==null && sortList==null) {%>
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
			<td><%out.println(theStaffAssigned); %></td>
			<td><%out.println("<input type=\"submit\" name=\""+handleIncident+"\" value=\"Handle Incident\">"); %></td>
		<%} %>
		</tr>
	<%} else if(searchSize==0 || sortSize==0) {%>
			<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<%if(branchManagerAuthorization==1) { %>
				<td></td>
				<td></td>
			<%} %>
			</tr>
			<%break;
	} else if(sortList.size()!=0) {

		if(sortList!=null) {
			out.println("Yofirst elem: " + sortList.get(0));
			out.println("Yosize: " + sortList.size());
		}
		int index;
		for(int j=0; j<sortList.size();j++)
		{%>
			<%index = sortList.get(j);%>
			<tr>
			<td><%out.println(number); %></td>
			<td><%out.println(IncidentDatabase.getIncidentsList().get(index).getIncidentTitle()); %></td>
			<td><%out.println(IncidentDatabase.getIncidentsList().get(index).getIncidentCategory()); %></td>
			<td><%out.println(IncidentDatabase.getIncidentsList().get(index).getIncidentDateOfMonth()); %></td>
			<td><%out.println(IncidentDatabase.getIncidentsList().get(index).getIncidentMonth()); %></td>
			<td><%out.println(IncidentDatabase.getIncidentsList().get(index).getIncidentYear());%></td>
			<td><%out.println(IncidentDatabase.getIncidentsList().get(index).getUserReportedIncident().getName());%></td>
			<td><%out.println(IncidentDatabase.getIncidentsList().get(i).getUserReportedIncident().getPosition());%></td>
			<td><%out.println(keywords); %></td>
			<td><%out.println("<input type=\"submit\" name=\""+incidentMarker+"\" value=\"View Incident\">"); %></td>
			<%if(branchManagerAuthorization==1) { %>
				<td><%out.println(theStaffAssigned); %></td>
				<td><%out.println("<input type=\"submit\" name=\""+handleIncident+"\" value=\"Handle Incident\">"); %></td>
			<%} %>
			</tr>
		<%	number++;
		}
		break;
	} else {
		if(searchList.contains(number-1)) {%>
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
				<td><%out.println(theStaffAssigned); %></td>
				<td><%out.println("<input type=\"submit\" name=\""+handleIncident+"\" value=\"Handle Incident\">"); %></td>
			<%} %>
			</tr>
		<%}
	}%>
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
<form action="searchIncidentReports">
  <select id="searchTopic" name="searchTopic">
    <option value="Incident">Incident</option>
    <option value="RootCause">Root Cause</option>
    <option value="Keywords">Keywords</option>
    <option value="Category">Category</option>
  </select>
  <input type="text" name="search" placeholder="Search..">
</form>
<br>

Sort Incidents
<form action="sortIncidentReports" id="sort">
	<select id="sortBy" name="sortBy">
    <option value="Title">Title</option>
    <option value="Category">Category</option>
    <option value="Year">Year</option>
  </select>
</form>
<button type="submit" form="sort" value="Submit">Sort</button>
<br>

<table>
</table>
</body>
</html>
