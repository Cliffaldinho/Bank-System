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
<%
int theIndex=(int) request.getAttribute("indexOfIncident");
//out.println(theIndex);

//To print title
String theTitle;
theTitle=IncidentDatabase.getIncidentsList().get(theIndex).getIncidentTitle();

//To print category
String theCategory;
theCategory=IncidentDatabase.getIncidentsList().get(theIndex).getIncidentCategory().toString();

//To print date
String theDate, theDay, theMonth, theYear;
int day = IncidentDatabase.getIncidentsList().get(theIndex).getIncidentDateOfMonth();
theDay = Integer.toString(day);
theMonth = IncidentDatabase.getIncidentsList().get(theIndex).getIncidentMonth();
int year = IncidentDatabase.getIncidentsList().get(theIndex).getIncidentYear();
theYear = Integer.toString(year);
theDate = theDay+" "+theMonth+" "+ theYear;

//To print incident description
String theIncidentDescription;
theIncidentDescription = IncidentDatabase.getIncidentsList().get(theIndex).getDescriptionOfIncident();

//To print staff details
String staffName,positionName,staffID;
staffName=IncidentDatabase.getIncidentsList().get(theIndex).getUserReportedIncident().getName();
positionName=IncidentDatabase.getIncidentsList().get(theIndex).getUserReportedIncident().getPosition().toString();
staffID = IncidentDatabase.getIncidentsList().get(theIndex).getUserReportedIncident().getStaffID();

//To print keywords
String tempKeywords= "";
String lastKeyword;
String keywords;

if(IncidentDatabase.getIncidentsList().get(theIndex).getIncidentKeywords().length<2) {
	keywords=IncidentDatabase.getIncidentsList().get(theIndex).getIncidentKeywords()[0];
} else {

	for(int i=0;i<IncidentDatabase.getIncidentsList().get(theIndex).getIncidentKeywords().length-1;i++) {
	tempKeywords=tempKeywords+IncidentDatabase.getIncidentsList().get(theIndex).getIncidentKeywords()[i]+", ";
}
lastKeyword=IncidentDatabase.getIncidentsList().get(theIndex).getIncidentKeywords()[IncidentDatabase.getIncidentsList().get(theIndex).getIncidentKeywords().length-1];
keywords=tempKeywords+lastKeyword;}

String priority;
if(IncidentDatabase.getIncidentsList().get(theIndex).getPriorityRating()!=null) {
	priority=IncidentDatabase.getIncidentsList().get(theIndex).getPriorityRating().toString();
} else {
	priority="N/A";
}

boolean checkStateIsDuplicate=false;
int duplicateListSize;
duplicateListSize=IncidentDatabase.getDuplicatesList().size();
int mostRecentDuplicateIndex=duplicateListSize-1;
if(!IncidentDatabase.getDuplicatesList().isEmpty()) {
	checkStateIsDuplicate=IncidentDatabase.getDuplicatesList().get(mostRecentDuplicateIndex).getDuplicateCheckInProcess();
}

String possibleCauses;
possibleCauses=IncidentDatabase.getIncidentsList().get(theIndex).getPossibleCausesOfIncident();


String possibleSolutions;
possibleSolutions=IncidentDatabase.getIncidentsList().get(theIndex).getPossibleSolutionsOfIncident();


%>
<!-- 
Incident title
Incident category
Incident date
Description of incident
Name of staff reporting this incident
Position of staff reporting this incident
Staff ID
Incident keywords
Priority rating
Possible causes
Possible solutions
 -->
Incident title:
<%out.println(theTitle); %>
<br>
Incident category:
<%out.println(theCategory); %>
<br>
Incident date:
<%out.println(theDate); %>
<br>
Description of incident:
<%out.println(theIncidentDescription); %>
<br>
Name of staff who reported this incident:
<%out.println(staffName); %>
<br>
Position of staff who reported this incident:
<%out.println(positionName); %>
<br>
ID of staff who reported this incident:
<%out.println(staffID); %>
<br>
Incident keywords:
<%out.println(keywords); %>
<br>
Priority rating:
<%out.println(priority); %>
<br>
Possible causes:
<%out.println(possibleCauses); %>
<br>
Possible solutions:
<%out.println(possibleSolutions); %>





<%
if(checkStateIsDuplicate==true) {
%>
<p>Mark the submitted incident report as duplicate of this?</p>
<!-- Yes: Incident report remains as duplicate -->
<form action="ListOfIncidents.jsp">
<input type="submit" value="Yes">
</form>
<!-- No: Incident report is marked as original -->
<form action="detectDuplicate" method="post">
<input type="submit" value="No">
</form>
<%} else { %>
<form action="performAnalysis" method="get">
<input type="submit" value="Analysis">
<%out.println("<input type=\"hidden\" name=\"indexForAnalysis\" value=\""+theIndex+"\">"); %>
</form>
<br>
<form action="ListOfIncidents.jsp">
<input type="submit" name="incidentsList" value="List">
</form>
<%} %>
</body>
</html>
