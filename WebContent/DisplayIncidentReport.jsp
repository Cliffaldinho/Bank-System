<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="data.UserDAO" %>
    <%@ page import="data.IncidentDAO" %>
    <%@ page import="data.*" %>
     <%@ taglib
    prefix="c"
    uri="http://java.sun.com/jsp/jstl/core" 
%>

<%
	HttpSession aSession = request.getSession();
%>
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
//temporarily removed
//int theIndex=(int) request.getAttribute("indexOfIncident");%>

	<div class="container">
	
	<c:if test="${checkDuplicate}">
	The report you submitted seems to be a duplicate of this:
	</c:if>
	
		<h2>Incident Details</h2>
		<table style="table-layout:fixed">
			<tr>
				<td style="background: #dddddd">Incident ID:</td>
				<td>${incidentID}</td>
			</tr>
			<tr>
				<td style="background: #dddddd">Incident title:</td>
				<td>${incidentTitle}</td>
			</tr>
		
			<tr>
				<td style="background: #dddddd">Incident category:</td>
				<td>${incidentCategory}</td>
			</tr>
		
			<tr>
				<td style="background: #dddddd">Incident date:</td>
				<td>${incidentDate}</td>
			</tr>
		
			<tr>
				<td style="background: #dddddd">Description of incident:</td>
				<td>${incidentDescription }</td>
			</tr>
		
			<tr>
				<td style="background: #dddddd">Name of staff who reported this incident:</td>
				<td>${staffName}</td>
			</tr>
		
			<tr>
				<td style="background: #dddddd">Position of staff who reported this incident:</td>
				<td>${staffPosition}</td>
			</tr>
		
			<tr>
				<td style="background: #dddddd">ID of staff who reported this incident:</td>
				<td>${staffID}</td>
			</tr>
		
			<tr>
				<td style="background: #dddddd">Incident keywords:</td>
				<td>
				<c:forEach var="temp" items="${incidentKeywords}">
				${temp}
				<br>
				</c:forEach>
				</td>
			</tr>
		
			<tr>
				<td style="background: #dddddd">Priority rating:</td>
				<td>${incidentPriority}</td>
			</tr>
		
			<tr>
				<td style="background: #dddddd">Possible causes:</td>
				<td>${incidentPossibleCauses}</td>
			</tr>
		
			<tr>
				<td style="background: #dddddd">Possible solutions:</td>
				<td>${incidentPossibleSolutions}</td>
			</tr>
		
		</table>

		
		<!-- new uncompleted (for analysis) code. (functional) -->
	
		
		<c:if test="${checkDuplicate}">
		<p>Mark the submitted incident report as duplicate of this?</p>
		<form action="detectDuplicate" method="post">
		<input type="submit" name="duplicateDecision" value="Yes"> Merge submitted report into original.
		<br>
		<input type="submit" name="duplicateDecision" value="No">  Add as new report.
		</form>
		</c:if>
		
		
		<c:if test="${not checkDuplicate}">
		<form action="performAnalysis" method="post">
		<input type="submit" value="Analysis">
		<%
		//temporarily removed
		//out.println("<input type=\"hidden\" name=\"indexForAnalysis\" value=\""+theIndex+"\">"); %>
		</form>
		</c:if>
		<!-- finish new -->
	</div>
	
	
</body>
</html>
