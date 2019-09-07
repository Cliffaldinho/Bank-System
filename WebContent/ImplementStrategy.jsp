<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
         <%@taglib
    prefix="c"
    uri="http://java.sun.com/jsp/jstl/core" 
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" 
    prefix="fn" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css" />
<title>Implement Strategy</title>
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
	  <c:if test="${logAuth.authenticationLevel==1}">
	  	<a href="RolesForStaff.jsp">Roles</a>
	  </c:if>
	  <form>
	  	<a href="#" onclick="document.getElementById('statistics').submit();"> Statistics </a>
	  </form>	
	  <form>
	  	<a href="#" onclick="document.getElementById('account').submit();"> Account </a>
	  </form>	
	  <form>
	  	<a href="#" onclick="document.getElementById('logOut').submit();"> Logout </a>
	  </form>	  
	</div>

	
	<div class="container">
		<h2>Strategy</h2>
		<table style="table-layout:fixed">
			<tr>
				<td class="td2">Risk Foreseen:</td>
				<td>${incidentSelected.postIncident.riskForeseen}</td>
			</tr>		
			<tr>
				<td class="td2">Risk Evaluated:</td>
				<td>${incidentSelected.postIncident.riskEvaluationAsString}</td>
			</tr>		
			<tr>
				<td class="td2">Strategy Implemented:</td>
				<td>${incidentSelected.postIncident.strategyImplemented}</td>
			</tr>
		</table>
	</div>

	<div class="container">
		<h2>People ratings of Strategy</h2>
		<table style="table-layout:fixed">
			<tr>
				<td class="td2">Overall:</td>
				<td>${incidentSelected.postIncident.ratingOverall}</td>
			</tr>
			<tr>
				<td class="td2">Effectiveness:</td>
				<td>${incidentSelected.postIncident.ratingEffectiveness}</td>
			</tr>
			<tr>
				<td class="td2">Improvement:</td>
				<td>${incidentSelected.postIncident.ratingImprovementFromSituationBefore}</td>
			</tr>
			<tr>
				<td class="td2">Practical:</td>
				<td>${incidentSelected.postIncident.ratingPractical}</td>
			</tr>
			<tr>
				<td class="td2">Relevance:</td>
				<td>${incidentSelected.postIncident.ratingRelevanceToIncident}</td>
			</tr>
			<tr>
				<td class="td2">Satisfaction:</td>
				<td>${incidentSelected.postIncident.ratingSatisfactionOfStrategy}</td>
			</tr>
		</table>
	</div>

<!-- The Risk Foreseen, Risk Evaluation, and Strategy Implemented fields, can only be filled out once.-->
<!--  And only can be filled out by branch manager-->
<!-- if condition below is to test this -->
<c:if test="${logAuth.authenticationLevel==1&&incidentSelected.postIncident.strategyImplementedAlready==false}">
	<div class="container">
		<h2>Please fill out the strategy you want to implement:</h2>
		<form action="implementStrategy" method="post" onSubmit="return validateStrategyForm()">			
			
			<div class="container">				
				<div class="row">				
				    <div class="col-25">			    
				    	<label for="Foreseen">Risk Foreseen:</label>
				    </div>
				    <div class="col-75">
				    	<input type="text" name="Foreseen" id="riskForeseen" >
				    </div>
				</div>
			</div>
			
			<div class="container">	
				<!-- Used radio button because important that user sees all the choices. Select option is when user doesn't have to see all the options -->
				<div class="row">				
				    <div class="col-25">			    
				    	<label for="Likelihood">Risk Likelihood:</label>
				    </div>
				    <div class="col-75">
					    <c:set var="probabilityArray" scope="page">Rare,Unlikely,Possible,Likely,Almost Certain</c:set>
						<c:forEach var="probability" items="${probabilityArray}" varStatus="loop">
						<div class="row2">
							<div class="col-75">			    
								<label for="Likelihood">${probability}:</label>
							</div>
							<div class="col-25">
								<input type="radio" name="Likelihood" value="${loop.index}">
							</div>
						</div>							    	
						</c:forEach>
				    </div>
				</div>
			</div>
			
			<div class="container">		
				<div class="row">			
					<!-- @Naneth could you align the radio buttons (like in CreateIncidentReport) for this form please? Coz I saw it was aligned in CreateIncidentReport.jsp, but not sure how so yeh lol.. -->
				
				    <div class="col-25">			    
				    	<label for="Consequences">Risk Consequence:</label>
				    </div>
				    <div class="col-75">
				    	<c:set var="consequenceArray" scope="page">Insignificant,Minor,Moderate,Major,Critical</c:set>
						<c:forEach var="consequence" items="${consequenceArray}" varStatus="loop">
						<div class="row2">
							<div class="col-75">			    
								<label for="Consequences">${consequence}:</label>
							</div>
							<div class="col-25">
								<input type="radio" name="Consequences" value="${loop.index}">
							</div>
						</div>
						</c:forEach>
				    </div>
				</div>				
			</div>
			
			<div class="container">
				<div class="row">
				    <div class="col-25">			    
				    	<label for="Implement">Strategy implemented</label>
				    </div>
				    <div class="col-75">			    
				    	<input type="text" name="Implement" id = "strategyImplemented">
				    </div>					
				</div>
			</div>
		<input type="submit" name="Strategy" value="Implement Strategy">
		</form >
	</div>
</c:if>

<!-- Each user can only rate the strategy once -->
<!-- Below if is to test that -->
<c:set var="thisUser" value="${logAuth.username}."/>
<c:if test = "${!fn:contains(incidentSelected.postIncident.staffWhoRatedStrategy,thisUser)}">
	<div class="container">
		<!-- @Naneth would you be able to space out the horizontal radio buttons for this form more clearly please? So that they won't be confusing to user -->
		<h2>Please rate the strategy that was implemented for feedback:</h2>
		<form action="setRatings" method="post" onSubmit="return validateRatingForm()">
			
			<div class="container">
				<div class="row">
				    <div class="col-25">			    
				    	<label for="Overall">Overall:</label>
				    </div>
				    <div class="col-75">
						<c:set var="overallArray">Very Bad,Bad,Neutral,Good,Very Good</c:set>
						<c:forEach var="overall" items="${overallArray}" varStatus="loop">	
							<div class="row2">
								<div class="col-75">			    
									<label for="Overall">${overall}</label>
								</div>
								<div class="col-25">
									<input type="radio" name="Overall" value="${loop.index}">
								</div>
							</div>
						</c:forEach>
				    </div>			
				</div>
			</div>			
			
			<div class="container">
				<div class="row">
				    <div class="col-25">			    
				    	<label for="Effectiveness">Strategy Effectiveness:</label>
				    </div>
				    <div class="col-75">
						<c:set var="effectivenessArray">Very Ineffective,Ineffective,Neutral,Effective,Very Effective</c:set>
						<c:forEach var="effectiveness" items="${effectivenessArray}" varStatus="loop">
							<div class="row2">
								<div class="col-75">			    
									<label for="Effectiveness">${effectiveness}</label>
								</div>
								<div class="col-25">
									<input type="radio" name="Effectiveness" value="${loop.index}">
								</div>
							</div>
						</c:forEach>
				    </div>			
				</div>
			</div>		

			<div class="container">
				<div class="row">
				    <div class="col-25">			    
				    	<label for="Improvement">Improvement On Situation Before:</label>
				    </div>
				    <div class="col-75">
						<c:set var="improvementArray">Alot Worse,Worse,Neutral,Better,Alot Better</c:set>
						<c:forEach var="improvement" items="${improvementArray}" varStatus="loop">
							<div class="row2">
								<div class="col-75">			    
									<label for="Improvement">${improvement}</label>
								</div>
								<div class="col-25">
									<input type="radio" name="Improvement" value="${loop.index}">
								</div>
							</div>
						</c:forEach>
				    </div>			
				</div>
			</div>		

			<div class="container">
				<div class="row">
				    <div class="col-25">			    
				    	<label for="Practical">Practical:</label>
				    </div>
				    <div class="col-75">
						<c:set var="practicalArray">Very Impractical,Impractical,Neutral,Quite Practical,Very Practical</c:set>
						<c:forEach var="practical" items="${practicalArray}" varStatus="loop">
							<div class="row2">
								<div class="col-75">			    
									<label for="Practical">${practical}</label>
								</div>
								<div class="col-25">
									<input type="radio" name="Practical" value="${loop.index}">
								</div>
							</div>
						</c:forEach>
				    </div>			
				</div>
			</div>		

			<div class="container">
				<div class="row">
				    <div class="col-25">			    
				    	<label for="Relevance">Relevance to this incident:</label>
				    </div>
				    <div class="col-75">
						<c:set var="relevanceArray">Very irrelevant,Irrelevant,Neutral,Relevant,Very Relevant</c:set>
						<c:forEach var="relevance" items="${relevanceArray}" varStatus="loop">
							<div class="row2">
								<div class="col-75">			    
									<label for="Relevance">${relevance}</label>
								</div>
								<div class="col-25">
									<input type="radio" name="Relevance" value="${loop.index}" >
								</div>
							</div>
						</c:forEach>
				    </div>			
				</div>
			</div>		
			
			<div class="container">
				<div class="row">
				    <div class="col-25">			    
				    	<label for="Satisfaction">Satisfaction of strategy implemented:</label>
				    </div>
				    <div class="col-75">
						<c:set var="satisfactionArray">Very Unsatisfied,Unsatisfied,Neutral,Satisfied,VerySatisfied</c:set>
						<c:forEach var="satisfaction" items="${satisfactionArray}" varStatus="loop">
							<div class="row2">
								<div class="col-75">			    
									<label for="Satisfaction">${satisfaction}</label>
								</div>
								<div class="col-25">
									<input type="radio" name="Satisfaction" value="${loop.index}">
								</div>
							</div>
						</c:forEach>
				    </div>			
				</div>
			</div>		
			
			<input type="submit" name="Ratings" value="Rate Strategy">
		</form>
	</div>
</c:if>

<div class="container">
	<div class="row">
		<div class="col-25">
			<form action="DisplayIncidentReport.jsp" method="post">
				<input type="submit" value="View Report">
			</form>
		</div>
	</div>
</div>
	
<form id="logOut" action="userLogout" method="post"></form>
<form id="account" action="personalDetails" method="post"></form>
<form id="statistics" action="showStatistics" method="post"></form>
	
<script>
function validateStrategyForm() {
	
	//check if both Likelihood and Consequences are filled out
	if((!$("input[name='Likelihood']:checked").val()==true)||
		(!$("input[name='Consequences']:checked").val()==true)) {
		alert("Please fill out both of the Risk Evaluation fields.");
		return false;
	}
	
	//check if Risk Foreseen is filled out
  	var theRisk = document.getElementById("riskForeseen").value;
	if(theRisk.length===0||theRisk.trim().length===0) {
		alert("Please fill out the Risk Foreseen field."); 
		return false;
	}
	
	//check if Strategy Implemented is filled out
	var theStrategy = document.getElementById("strategyImplemented").value;
	if(theStrategy.length===0||theStrategy.trim().length===0) {
		alert("Please fill out the Strategy Implemented field."); 
		return false;
	}
    
    return true;
    
   
}
  	
function validateRatingForm() {
	
	//if one of the ratings is not checked
	if((!$("input[name='Overall']:checked").val()==true)||
	(!$("input[name='Effectiveness']:checked").val()==true)||
	(!$("input[name='Improvement']:checked").val()==true)||
	(!$("input[name='Practical']:checked").val()==true)||
	(!$("input[name='Relevance']:checked").val()==true)||
	(!$("input[name='Satisfaction']:checked").val()==true)){
		
		
	//send an alert and stop form from sending
	alert("Please fill out all the ratings.");	
	
	return false;
	
	}

	//otherwise send form
	return true;
	
	}

</script>
</body>
</html>
