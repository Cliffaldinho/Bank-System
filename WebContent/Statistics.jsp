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
<%    pageContext.setAttribute("incidentCategoriesStatistics", data.IncidentBean.Category.values()); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
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

<table>
<tr>
<th>Category</th>
<th>Amount of Incidents</th>
<th>Percentage among all Incidents</th>
</tr>
<c:set var="categoryAmount" value="${stats.categoryAmount}"/>
<c:set var="categoryPercentage" value="${stats.categoryPercentage}"/>
<c:forEach begin="0" end="5" varStatus="loop">
<tr>
<td>${incidentCategoriesStatistics[loop.index].toString()}</td>
<td>${stats.categoryAmount[loop.index]}</td>
<td>${stats.categoryPercentage[loop.index]}</td>
</tr>
</c:forEach>
</table>
<br>
<br>

<form name="list" action="displayIncidentReport" method="post">
<table>
<tr>
	<th>Incident ID</th>
	<th>Incident Title</th>
	<th>Incident Category</th>
	<th>Incident Causes</th>
	<th>Incident Solutions</th>
	<th>Risk Prevention Strategy</th>
	<th>Strategy Rating (Overall)</th>
	<th>Strategy Effectiveness</th>
	<th>Situation Improvement from Strategy</th>
	<th>Strategy Practicality</th>
	<th>Strategy Relevance to Incident</th>
	<th>Satisfaction of Strategy</th>
	<th>Incident Details</th>
</tr>
<c:forEach items="${archivedList}" var="report" varStatus="loop">
	<td><c:out value="${report.incidentID}"/></td>
	<td><c:out value="${report.incidentTitle}"/></td>
	<td><c:out value="${report.incidentCategory}"/></td>
	<td><c:out value="${report.postIncident.possibleCausesOfIncident}"/></td>
	<td><c:out value="${report.postIncident.possibleSolutionsOfIncident}"/></td>
	<td><c:out value="${report.postIncident.strategyImplemented}"/></td>
	<td><c:out value="${report.postIncident.ratingOverall}"/></td>
	<td><c:out value="${report.postIncident.ratingEffectiveness}"/></td>
	<td><c:out value="${report.postIncident.ratingImprovementFromSituationBefore}"/></td>
	<td><c:out value="${report.postIncident.ratingPractical}"/></td>
	<td><c:out value="${report.postIncident.ratingRelevanceToIncident}"/></td>
	<td><c:out value="${report.postIncident.ratingSatisfactionOfStrategy}"/></td>
	<td><input type="submit" value="View Incident" id="View" onClick="incidentClicked(${report.incidentID},this.id)"></td>
</c:forEach>
</table>
<input type="hidden" name="chosen" id="storeOptionChosen" value="three"  > 
<input type="hidden" name="clicked" id="storeIncidentClicked" value="five">
</form>
<br>

<form action="prepareList" method="post">
<input type="submit" name="incidentsList" value="Incidents List">
</form>

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