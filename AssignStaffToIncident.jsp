<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ page import="data.UserDatabase" %>
    <%@ page import="data.IncidentDatabase" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
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

</body>
</html>