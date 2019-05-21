<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ page import="data.UserDatabase" %>
    <%@ page import="data.IncidentDatabase" %>

<%HttpSession aSession = request.getSession();%>
<jsp:useBean id="logAuth" class="data.StaffBean" scope="session" />

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css" />
<title>Assign Staff to Incident</title>
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
	  <a href="UserLogin.jsp">Logout</a>
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
<%
int theIncidentIndex=(int) request.getAttribute("indexForAssignStaff");
//out.println(theIncidentIndex);

String incidentTitle,incidentCategory,incidentDate,incidentMonth,dayOfIncident,yearOfIncident,staffWhoReportedIt,incidentDescription;
int incidentDay,incidentYear;

incidentTitle=IncidentDatabase.getIncidentsList().get(theIncidentIndex).getIncidentTitle();
incidentCategory=IncidentDatabase.getIncidentsList().get(theIncidentIndex).getIncidentCategory().toString();
incidentDay=IncidentDatabase.getIncidentsList().get(theIncidentIndex).getIncidentDateOfMonth();
incidentMonth=IncidentDatabase.getIncidentsList().get(theIncidentIndex).getIncidentMonth();
incidentYear = IncidentDatabase.getIncidentsList().get(theIncidentIndex).getIncidentYear();
dayOfIncident=Integer.toString(incidentDay);
yearOfIncident=Integer.toString(incidentYear);
incidentDate=dayOfIncident+" "+incidentMonth+" "+incidentYear;
staffWhoReportedIt=IncidentDatabase.getIncidentsList().get(theIncidentIndex).getUserReportedIncident().getName();
incidentDescription=IncidentDatabase.getIncidentsList().get(theIncidentIndex).getDescriptionOfIncident();

String staffName,staffID,staffPosition,staffRoles;
int number;
%>
Incident title:
<%out.println(incidentTitle); %>
<br>
Incident category:
<%out.println(incidentCategory); %>
<br>
Incident date:
<%out.println(incidentDate); %>
<br>
Incident description:
<%out.println(incidentDescription); %>
<br>
Staff who reported it:
<%out.println(staffWhoReportedIt); %>
<br>
<br>
<br>


<form action="finishAssignStaff" method="get">
<table>
<tr>
<th>Number</th>
<th>Staff Name</th>
<th>Staff ID</th>
<th>Staff Position</th>
<th>Staff Roles</th>
<th>Assign this staff to Incident</th>
</tr>
<%
for(int i=0;i<UserDatabase.getUsersList().size();i++) {
	number=i+1;
	staffName=UserDatabase.getUsersList().get(i).getName();
	staffID=UserDatabase.getUsersList().get(i).getStaffID();
	staffPosition=UserDatabase.getUsersList().get(i).getPosition().toString();
	staffRoles=UserDatabase.getUsersList().get(i).getRolesToDo();
	String setNone="None";
	if(staffRoles==null) {
		staffRoles=setNone;
	}
	
	String assignStaff="Assign"+i;
%>
<tr>
<td><%out.println(number); %></td>
<td><%out.println(staffName); %></td>
<td><%out.println(staffID); %></td>
<td><%out.println(staffPosition); %></td>
<td><%out.println(staffRoles); %></td>
<td><%out.println("<input type=\"submit\" name=\""+assignStaff+"\" value=\"Assign Staff\">");%></td>
</tr>
<%} %>
</table>
<%out.println("<input type= \"hidden\" name=\"IncidentIndex\" value=\""+theIncidentIndex+"\">"); %>
</form>
<br>
<form action="ListOfIncidents.jsp">
<input type="submit" name="incidentsList" value="List">
</form>
</body>
</html>
