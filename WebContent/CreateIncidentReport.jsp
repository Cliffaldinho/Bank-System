<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="data.*" %>
    
<%HttpSession aSession = request.getSession();%>
<jsp:useBean id="logAuth" class="data.StaffBean" scope="session" />
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css" />
<title>Create Incident Report</title>
<script>
<script>
function validateName(){
	window.alert("Please enter a name."); 
}
function validateID(){
	window.alert("Please enter a valid ID."); 
}
function validateDescription(){
	window.alert("Please enter a description."); 
}
function validateTitle(){
	window.alert("Please enter a title."); 
}
function validateVerified(){
	window.alert("Please enter a correct combination of name, ID and job title."); 
}
</script>
</script>
</head>
<%
System.out.println(request.getAttribute("errorType"));
if (request.getAttribute("errorType") != null){
	String errorType = (String)request.getAttribute("errorType");
	System.out.println(errorType);
	if (errorType.equals("name")){
		out.print("<body onload=\"validateName()\">");
	} else if (errorType.equals("description")){
		out.print("<body onload=\"validateDescription()\">");
	} else if (errorType.equals("id")){
		out.print("<body onload=\"validateID()\">");
	} else if (errorType.equals("title")){
		out.print("<body onload=\"validateTitle()\">");
	} else if (errorType.equals("verified")){
		out.print("<body onload=\"validateVerified()\">");
	}
} else {
	out.print("<body>");
}%>
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
		<form action="createIncidentReport" method="post">
			<!-- Compulsory -->
			<div class="container">
				<div class="row">
				    <div class="col-25">			    
				      <label for="incidentTitle">Incident title</label>
				    </div>
				    <div class="col-75">
				      <input type="text" name="incidentTitle" value="<%if(request.getAttribute("errorType") != null){out.print(request.getParameter("incidentTitle"));}%>">
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
				      <label for="incidentDay">Incident date</label>
				    </div>
				    <div class="col-75">
						<div class="row">
						    <div class="col-25" style="margin:10px">			    
						      <input type="text" name="incidentDay" value="01">
						    </div>
						    <div class="col-25" style="margin:10px">		
						      <input type="text" name="incidentMonth" value="January">
						    </div>
						    <div class="col-25" style="margin:10px">		
						      <input type="text" name="incidentYear" value="2019">
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
      				<textarea name="incidentDescription" placeholder="Please write incident description.." style="height:200px" value="<%if(request.getAttribute("errorType") != null){out.print(request.getParameter("incidentDescription"));}%>"></textarea>
			    </div>
			</div>
		</div>
		

		<!-- Compulsory -->
		<div class="container">
			<div class="row">
			    <div class="col-25">			    
			      <label for="incidentTitle">Name of Staff reporting this Incident</label>
			    </div>
			    <div class="col-75">
			      <input type="text" name="staffName" value="<%if(request.getAttribute("errorType") != null){out.print(request.getParameter("staffName"));}%>">
			    </div>
			</div>
		</div>

		<div class="container">
			<div class="row">
			    <div class="col-25">			    
			      <label>Position of Staff reporting this Incident</label>
			    </div>
			    <div class="col-75">

						<div class="row2">
						    <div class="col-75">			    
						      <label for="staffPosition">Branch Manager</label>
						    </div>
						    <div class="col-25">
						      <input type="radio" name="staffPosition" value="branchManager" checked>
						    </div>
						</div>
						
						<div class="row2">
						    <div class="col-75">			    
						      <label for="staffPosition">Data Processing Officer</label>
						    </div>
						    <div class="col-25">
						      <input type="radio" name="staffPosition" value="DPO">
						    </div>
						</div>
						
						<div class="row2">
						    <div class="col-75">			    
						      <label for="staffPosition">IT</label>
						    </div>
						    <div class="col-25">
						      <input type="radio" name="staffPosition" value="IT">
						    </div>
						</div>
						
						<div class="row2">
						    <div class="col-75">			    
						      <label for="staffPosition">Financial Analyst</label>
						    </div>
						    <div class="col-25">
						      <input type="radio" name="staffPosition" value="finance">
						    </div>
						</div>
						
						<div class="row2">
						    <div class="col-75">			    
						      <label for="staffPosition">Internal Auditor</label>
						    </div>
						    <div class="col-25">
						      <input type="radio" name="staffPosition" value="audit">
						    </div>
						</div>

			    </div>
			</div>
		</div>
				 		
		<!-- Compulsory -->		
		<div class="container">
			<div class="row">
			    <div class="col-25">			    
			      <label for="theStaffID">Staff ID</label>
			    </div>
			    <div class="col-75">
			      <input type="text" name="theStaffID" value="<%if(request.getAttribute("errorType") != null){out.print(request.getParameter("theStaffID"));}%>">
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
			      <input type="text" name="incidentKeywords" value="<%if(request.getAttribute("errorType") != null){out.print(request.getParameter("incidentKeywords"));}%>">
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
			      <textarea name="possibleCausesOfIncident" placeholder="Please write possible causes.." style="height:200px" value="<%if(request.getAttribute("errorType") != null){out.print(request.getParameter("possibleCausesOfIncident"));}%>"></textarea>
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
			      <textarea name="possibleSolutionsOfIncident" placeholder="Please write possible solutions.." style="height:200px" value="<%if(request.getAttribute("errorType") != null){out.print(request.getParameter("possibleSolutionsOfIncident"));}%>"></textarea>
			    </div>
			</div>
		</div>
		
		
		
		<br>
		
		<input type="submit">
		</form>
	</div>
</body>
</html>
