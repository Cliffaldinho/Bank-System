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
int original= (int) request.getAttribute("theOriginalIndex");

int duplicateListSize;
duplicateListSize=IncidentDatabase.getDuplicatesList().size();
int mostRecentIndex;
mostRecentIndex=duplicateListSize-1;

IncidentDatabase.getDuplicatesList().get(mostRecentIndex).setOriginalIndexForDuplicateChecking(original);
%>
	
	<div class="container">
		
		<h2>Duplicate Report Detected</h2>
		
		<!-- View Original Incident Report  -->
		<table>
			<tr>
				<th colspan="2">The Incident Report you submitted seems to be a duplicate. View which report it is a duplicate of?</th>
			</tr>
			
			<tr>
				<td>
					<form action="detectDuplicate" method="get">
						<input type="submit" value="yes">
					</form>
				</td>
				<td>
					<!-- Duplicated report remains marked as duplicate. Return to List of Incidents -->
					<form action="ListOfIncidents.jsp">
						<input type="submit" value="no">
					</form>
				</td>
			</tr>
		
		</table>

		
		
	</div>
</body>
</html>
