<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="data.*" %>
        <%@ taglib
    prefix="c"
    uri="http://java.sun.com/jsp/jstl/core" 
%>
    
<%HttpSession aSession = request.getSession();%>
<jsp:useBean id="logAuth" class="data.StaffBean" scope="session" />
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css" />
<title>Create Incident Report</title>
<script>

function validateForm() {
	
	if(document.getElementById("theIncidentTitle").value=="") {
		window.alert("Please enter a title."); 
		return false;
	}
	
	if(document.getElementById("theIncidentDescription").value=="") {
		window.alert("Please enter a description."); 
		return false;
	}
	
	if(document.getElementById("theIncidentKeywords").value=="") {
		window.alert("Please enter keywords for the incident.");
		return false;
	}
	
	return true;
}


</script>

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
	  <a href="CreateIncidentReport.jsp" class="active">Report</a>
	  <a href="RolesForStaff.jsp">Roles</a>
	  <a href="index.jsp">Logout</a>
	</div>
	<br>
	<br>

	<div class="container">	
		
		<h2>Report Incident</h2>
		<h3><%out.println(logAuth.getUsername()); //username is staff id%></h3>
		<form action="createIncidentReport" method="post" id="createReport" onSubmit="return validateForm()">
			<!-- Compulsory -->
			<div class="container">
				<div class="row">
				    <div class="col-25">			    
				      <label for="incidentTitle">Incident title</label>
				    </div>
				    <div class="col-75">
				      <input type="text" name="incidentTitle" id="theIncidentTitle" >
				    </div>
				</div>
			</div>
			
			<!-- Compulsory -->
			<div class="container">
				<div class="row">
				    <div class="col-25">			    
				      <label for="incidentCategory">Incident category</label>
				    </div>
				    <div class="col-75">
			    
						<div class="row2">
						    <div class="col-75">			    
						      <label for="incidentCategory">Law</label>
						    </div>
						    <div class="col-25">
						      <input type="radio" name="incidentCategory" value="regulatoryLaw" checked>
						    </div>
						</div>				
						<div class="row2">
						    <div class="col-75">			    
						      <label for="incidentCategory">Cyber security</label>
						    </div>
						    <div class="col-25">
						      <input type="radio" name="incidentCategory" value="cyberSecurity">
						    </div>
						</div>		
						<div class="row2">
						    <div class="col-75">			    
						      <label for="incidentCategory">Human issues</label>
						    </div>
						    <div class="col-25">
						      <input type="radio" name="incidentCategory" value="humanIssues">
						    </div>
						</div>
						<div class="row2">
						    <div class="col-75">			    
						      <label for="incidentCategory">Bank equipment</label>
						    </div>
						    <div class="col-25">
						      <input type="radio" name="incidentCategory" value="bankEquipment">
						    </div>
						</div>
						<div class="row2">
						    <div class="col-75">			    
						      <label for="incidentCategory">Bank algorithms</label>
						    </div>
						    <div class="col-25">
						      <input type="radio" name="incidentCategory" value="bankAlgorithms">
						    </div>
						</div>
						<div class="row2">
						    <div class="col-75">			    
						      <label for="incidentCategory">Other (please specify in Incident description)</label>
						    </div>
						    <div class="col-25">
						      <input type="radio" name="incidentCategory" value="other">
						    </div>
						</div>

				    </div>
				</div>	
			</div>	

			
		<!-- Compulsory -->		
		<div class="container">
			<div class="row">
			    <div class="col-25">			    
			      <label for="incidentDescription">Description of Incident</label>
			    </div>
			    <div class="col-75">
      				<textarea name="incidentDescription"  id="theIncidentDescription" placeholder="Please write incident description.." style="height:200px" ></textarea>
			    </div>
			</div>
		</div>
		

		
		<!-- Compulsory (to facilitate Sort By Keywords) -->
		<div class="container">
			<div class="row">
			    <div class="col-25">			    
			      <label for="incidentKeywords">Incident Keywords (Please separate with commas)</label>
			    </div>
			    <div class="col-75">
			      <input type="text" name="incidentKeywords" id="theIncidentKeywords" >
			    </div>
			</div>
		</div>
		
		<!-- Optional -->
		<div class="container">
			<div class="row">
			    <div class="col-25">			    
			      <label for="thePriority">Priority Rating (optional)</label>
			    </div>
			    <div class="col-75">
			    
						<div class="row2">
						    <div class="col-25">			    
						      <label for="thePriority">Low</label>
						    </div>
						    <div class="col-75">
						      <input type="radio" name="thePriority" value="Low">
						    </div>
						</div>						
						
						<div class="row2">
						    <div class="col-25">			    
						      <label for="thePriority">Medium</label>
						    </div>
						    <div class="col-75">
						      <input type="radio" name="thePriority" value="Medium" checked>
						    </div>
						</div>
						
						<div class="row2">
						    <div class="col-25">			    
						      <label for="thePriority">High</label>
						    </div>
						    <div class="col-75">
						      <input type="radio" name="thePriority" value="High">
						    </div>
						</div>

			    </div>
			</div>
		</div>

		<!-- Optional -->
		<div class="container">
			<div class="row">
			    <div class="col-25">			    
			      <label for="possibleCausesOfIncident">Possible Causes of Incident (optional)</label>
			    </div>
			    <div class="col-75">
			      <textarea name="possibleCausesOfIncident" placeholder="Please write possible causes.." style="height:200px" ></textarea>
			    </div>
			</div>
		</div>
		
		<!-- Optional -->
		<div class="container">
			<div class="row">
			    <div class="col-25">			    
			      <label for="possibleCausesOfIncident">Possible Solutions of Incident (optional)</label>
			    </div>
			    <div class="col-75">
			      <textarea name="possibleSolutionsOfIncident" placeholder="Please write possible solutions.." style="height:200px" ></textarea>
			    </div>
			</div>
		</div>
		
		
		
		<br>
		
		<input type="submit">
		</form>
	</div>
</body>
</html>
