<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	<%@ page import="java.util.*" %>
    <%@ page import="data.UserDAO" %>
    <%@ page import="data.IncidentDAO" %>
    <%@ page import = "data.UserBean" %>
    <%@ page import = "data.*" %>
         <%@taglib
    prefix="c"
    uri="http://java.sun.com/jsp/jstl/core" 
%>

<jsp:useBean id="logAuth" class="data.StaffBean" scope="session" />
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css" />
<title>View List of Incidents</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<!--  <script src="js/scripts.js"></script>-->
<script type="text/javascript">
	function checkNotifications(){
		setInterval(checkForNotifications, 10000);
	}
	
	function checkForNotifications(){
		$.ajax({
			url: "notifications",
			type: "post",
			data: "<c:out value="${logAuth.username}"/>",
			success: function(results) {
				
				if(results!="") {
				alert(results); 
				}
			}
	});
	}
</script>


</head>
<body onload="checkNotifications();">
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
	  <a href="ListOfIncidents.jsp" class="active">Incidents</a>
	  <a href="CreateIncidentReport.jsp">Report</a>
	  <c:if test="${logAuth.authenticationLevel==1}">
	  	<a href="RolesForStaff.jsp">Roles</a>
	  </c:if>
	   <form>
	  	<a href="#" onclick="document.getElementById('account').submit();"> Account </a>
	  </form>	
	  <form>
	  	<a href="#" onclick="document.getElementById('logOut').submit();"> Logout </a>
	  </form>	  
	</div>

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
						<form action="searchIncidentReports" method="post">
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
						<form action="sortIncidentReports" id="sort" method="post">
						    <div class="col-75">
						    	<select id="sortBy" name="sortBy">
								    <option value="Title">Title</option>
								    <option value="Category">Category</option>
								    <option value="Year">Year</option>
							  	</select>
						    </div>
						    
						    <div class="col-25">
						    	<button type="submit" form="sort" value="Submit" method="post">Sort</button>
						    </div>
						</form>		
					</div>
			    </div>
		</div>
		
		<!-- new feature to refresh page -->
		<div>
		<form action="prepareList" method="post">
		<input type="submit" value="Refresh">
		</form>
		</div>

<form name="list" action="displayIncidentReport" method="post">

	<div>
		<table>
			<tr>
				<th>Incident ID</th>
				<th>Title</th>
				<th>Category</th>
				<th>Day</th>
				<th>Month</th>
				<th>Year</th>
				<th>Reporter</th>
				<th>Staff Position</th>
				<th>Keywords</th>
				<th>Details</th>
				<c:if test="${logAuth.authenticationLevel==1}">
					<th>Close Incident</th>
				</c:if>
			</tr>
			<c:forEach items="${listOfIncidents}" var="report" varStatus="loop">
			<tr>
				<td><c:out value="${report.incidentID}"/></td>
				<td><c:out value="${report.incidentTitle}"/></td>
				<td><c:out value="${report.incidentCategory}"/></td>
				<td><c:out value="${report.incidentDateOfMonth}"/></td>
				<td><c:out value="${report.incidentMonth}"/></td>
				<td><c:out value="${report.incidentYear}"/></td>
				<td><c:out value="${report.userReportedIncident.name}"/></td>
				<td><c:out value="${report.userReportedIncident.position}"/></td>
				<td><c:out value="${report.incidentKeywordsInString}"/></td>
				<td><input type="submit" value="View Incident" id="View" onClick="incidentClicked(${report.incidentID},this.id)"></td>
				<c:if test="${logAuth.authenticationLevel==1}">
					<td><input type="submit" value="Close Incident" id="Close" onClick="incidentClicked(${report.incidentID},this.id)"></td>
				</c:if>
			</tr>
			</c:forEach>
			
		</table>
		<input type="hidden" name="chosen" id="storeOptionChosen" value="three"  > 
		<input type="hidden" name="clicked" id="storeIncidentClicked" value="five">
	</div>

</form>
<br>

<br>
<form action="CreateIncidentReport.jsp" method="post">
	<input type="submit" name="addIncident" value="Add Incident">
</form>
		
<br>
		
</div>
<!-- 
	Naneth: replaced buttons from bottom of the page to horizontal navigation at the top of the page
			but still need the forms for onclick event
-->
<form id="logOut" action="userLogout" method="post"></form>
<form id="account" action="personalDetails" method="post"></form>
	
<script>
function incidentClicked(id,option) {
var incidentID=id;
var optionChosen=option;
document.getElementById("storeIncidentClicked").value=incidentID;
document.getElementById("storeOptionChosen").value=optionChosen;
}

</script>
</body>
</html>
