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
	
	<div class="horizonta_nav">
	  <a href="ListOfIncidents.jsp">Incidents</a>
	  <a href="CreateIncidentReport.jsp">Report</a>
	  <a href="RolesForStaff.jsp" class="active">Roles</a>
	  <a href="UserLogin.jsp">Logout</a>
	</div>
	<br>
	<br>

	<div class="container">	
		<h2>View Roles for Staff</h2>
		
		<!-- Staff name, Staff position, Staff id -->
		<%//int branchManagerAuthorization=logAuth.getAuthenticationLevel();  %>
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
						if(branchManagerAuthorization==1) {
							
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
						<%out.println("<input type=\"submit\" name=\""+staffMarker+"\" value=\"Set Roles\">");}} %>
					</td>
				</tr>
			</table>
		</form>
		
		<form action="ListOfIncidents.jsp">
			<input type="submit" name="theIncidentsList" value="List">
		</form>
	</div>
</body>
</html>
