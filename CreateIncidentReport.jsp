<!--This JSP Page is done. Naneth could you do the front end on this please? And also for the ListOfIncidents one too please?-->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="data.Incident" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Incident Report</title>
</head>
<body>
<form action="createIncidentReport" method="get">
<!-- Compulsory -->
Incident title
<input type="text" name="incidentTitle">
<br>

<!-- Compulsory -->
Incident category
Law<input type="radio" name="incidentCategory" value="regulatoryLaw">
Cyber security<input type="radio" name="incidentCategory" value="cyberSecurity">
Human issues<input type="radio" name="incidentCategory" value="humanIssues">
Bank equipment<input type="radio" name="incidentCategory" value="bankEquipment">
Bank algorithms<input type="radio" name="incidentCategory" value="bankAlgorithms">
Other (please Elaborate in Incident description) <input type="radio" name="incidentCategory" value="other">
<br>

<!-- Compulsory -->
<!-- Might need to redecorate incident date -->
Incident date
<input type="text" name="incidentDay" value="01">
<input type="text" name="incidentMonth" value="January">
<input type="text" name="incidentYear" value="1000">
<br>

<!-- Compulsory --><!-- Maybe make this into a textbox? -->
Description of Incident
<input type="text" name="incidentDescription">
<br>

<!-- Compulsory -->
Name of Staff reporting this Incident
<input type="text" name="staffName" value="John Doe">
<br>

<!-- Compulsory -->
Position of Staff reporting this Incident
Branch Manager <input type="radio" name="staffPosition" value="branchManager">
Data Processing Officer <input type="radio" name="staffPosition" value="DPO">
IT <input type="radio" name="staffPosition" value="IT">
Financial Analyst <input type="radio" name="staffPosition" value="finance">
Internal Auditor <input type="radio" name="staffPosition" value="audit">
<br>

<!-- Compulsory -->
Staff ID
<input type="text" name="theStaffID">
<br>

<!-- Compulsory (to facilitate Sort By Keywords) -->
Incident Keywords (Please separate with commas) 
<input type="text" name="incidentKeywords">
<br>

<!-- Optional -->
Priority Rating (optional)
Low<input type="radio" name="thePriority" value="Low"><!--Is an enum. Could be a dropdown menu?  -->
Medium<input type="radio" name="thePriority" value="Medium">
High<input type="radio" name="thePriority" value="High">
<br>

<!-- Optional --><!-- And maybe into textbox too? -->
Possible Causes of Incident (optional)
<input type="text" name="possibleCausesOfIncident">
<br>

<!-- Optional --><!-- And also this into textbox? lol -->
Possible Solutions of Incident (optional)
<input type="text" name="possibleSolutionsOfIncident">
<br>

<input type="submit">
</form>
</body>
</html>