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
<h1>View Roles for Staff</h1>

<!-- Staff name, Staff position, Staff id -->
<%//int branchManagerAuthorization=1;  %>

<form action="defineRolesForStaff" method="get">
<table>

<tr>
<th>
Number
</th>
<th>
Staff name
</th>
<th>
Staff ID
</th>
<th>
Staff position
</th>
<th>
Current roles
</th>
<th>
Set roles
</th>
</tr>
<%

String staffName,staffPosition,staffID,currentRoles;
int number;
		//if(branchManagerAuthorization==1) {
			
		for(int i=0;i<UserDatabase.getUsersList().size();i++) { 

	    	staffName=UserDatabase.getUsersList().get(i).getName();
	    	staffPosition=UserDatabase.getUsersList().get(i).getPosition().toString();
	    	staffID=UserDatabase.getUsersList().get(i).getStaffID();
	    	number=i+1;
	    	
	    	currentRoles=UserDatabase.getUsersList().get(i).getRolesToDo();
	    	String setNone="None";
	    	if(currentRoles==null) {
	    		currentRoles=setNone;
	    	}
	    	

	    	String staffMarker="Staff"+i;%>
<tr>
<td>
<%out.println(number); %>
</td>
<td>
<%out.println(staffName); %>
</td>
<td>
<%out.println(staffID); %>
</td>
<td>
<%out.println(staffPosition); %>
</td>
<td>
<%out.println(currentRoles); %>
</td>
<td>
<%out.println("<input type=\"submit\" name=\""+staffMarker+"\" value=\"Set Roles\">");} %>
</td>
</tr>
</table>
</form>
<form action="ListOfIncidents.jsp">
<input type="submit" name="theIncidentsList" value="List">
</form>
</body>
</html>