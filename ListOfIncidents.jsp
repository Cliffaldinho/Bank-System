<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	<%@ page import="java.util.*" %>
    <%@ page import="data.UserDatabase" %>
    <%@ page import="data.IncidentDatabase" %>
    <%@ page import = "data.User" %>
    <%@ page import = "data.Incident" %>

<%HttpSession aSession = request.getSession();%>
<jsp:useBean id="logAuth" class="data.StaffBean" scope="session" />
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css" />
<title>View List of Incidents</title>
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
	  <a href="ListOfIncidents.jsp" class="active">Incidents</a>
	  <a href="CreateIncidentReport.jsp">Report</a>
	  <a href="RolesForStaff.jsp">Roles</a>
	  <a href="UserLogin.jsp">Logout</a>
	</div>
	<br>
	<br>

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

	<div class="container">
		
		<h2>Incidents</h2>
		
		<div class="row2">
			    <div class="col-25">
			    	<label>Search Incidents</label>
			    </div>
			    <div class="col-75">
			    	<div class="row">
						<form action="searchIncidentReports">
						    <div class="col-25">
						    	<select id="searchTopic" name="searchTopic">
								    <option value="Incident">Incident</option>
								    <option value="RootCause">Root Cause</option>
								    <option value="Keywords">Keywords</option>
								    <option value="Category">Category</option>
							  	</select>
						    </div>
						    
						    <div class="col-75">
						    	<input type="text" name="search" placeholder="Search..">
						    </div>
						</form>		
					</div>
			    </div>
		</div>
		
		<div class="row2">
			    <div class="col-25">
			    	<label>Sort Incidents</label>
			    </div>
			    <div class="col-75">
			    	<div class="row">
						<form action="sortIncidentReports" id="sort">
						    <div class="col-75">
						    	<select id="sortBy" name="sortBy">
								    <option value="Title">Title</option>
								    <option value="Category">Category</option>
								    <option value="Year">Year</option>
							  	</select>
						    </div>
						    
						    <div class="col-25">
						    	<button type="submit" form="sort" value="Submit">Sort</button>
						    </div>
						</form>		
					</div>
			    </div>
		</div>
		
		
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
		
		<div>
		
			<table>
			<tr>
			<th>#</th>
			<th>Title</th>
			<th>Category</th>
			<th>Day</th>
			<th>Month</th>
			<th>Year</th>
			<th>Reporter</th>
			<th>Staff Position</th>
			<th>Keywords</th>
			<th>Details</th>
			<%if(branchManagerAuthorization==1) { %>
			<th>Handle Incident</th>
			<th>Staff assigned</th>
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
					<td><input type="submit" name="<% out.println(i);%>" value="Handle Incident"></td>
			    <td><%out.println("Assigned staff"); %></td>
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
						<td></td>
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
						<td><%out.println(IncidentDatabase.getIncidentsList().get(index).getUserReportedIncident().getPosition());%></td>
						<td><%out.println(keywords); %></td>
						<td><%out.println("<input type=\"submit\" name=\""+incidentMarker+"\" value=\"View Incident\">"); %></td>
						<td><input type="submit" name="<% out.println(i);%>" value="Handle Incident"></td>
			      <td><%out.println("Assigned staff"); %></td>
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
						<td><input type="submit" name="<% out.println(i);%>" value="Handle Incident"></td>
			      <td><%out.println("Assigned staff"); %></td>
						</tr>
					<%}
				}%>
			<%} %>
			
			</table>
		
		</div>	
		</form>
		<br>
		
		
		<form action="CreateIncidentReport.jsp">
	
		<label for="addIncident">Add an Incident</label>
		<input type="submit" name="addIncident" value="Add">
		</form>
		<br>
		
		
		<table>
		</table>
	</div>
</body>
</html>
