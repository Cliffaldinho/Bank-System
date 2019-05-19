<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="data.IncidentDatabase" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
int original= (int) request.getAttribute("theOriginalIndex");

int duplicateListSize;
duplicateListSize=IncidentDatabase.getDuplicatesList().size();
int mostRecentIndex;
mostRecentIndex=duplicateListSize-1;

IncidentDatabase.getDuplicatesList().get(mostRecentIndex).setOriginalIndexForDuplicateChecking(original);
%>
The Incident Report you submitted seems to be a duplicate. View which report it is a duplicate of?

<!-- View Original Incident Report  -->
<form action="detectDuplicate" method="get">
<input type="submit" value="yes">
</form>

<!-- Duplicated report remains marked as duplicate. Return to List of Incidents -->
<form action="ListOfIncidents.jsp">
<input type="submit" value="no">
</form>
</body>
</html>