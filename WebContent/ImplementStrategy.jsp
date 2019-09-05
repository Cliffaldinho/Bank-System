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
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script type="text/javascript">
	function checkNotifications(){
		setInterval(checkForNotifications, 30000);
	}
	
	function checkForNotifications(){
		$.ajax({
			url: "notifications",
			type: "post",
			data: "<c:out value="${logAuth.username}"/>",
			success: function(results) {
				alert(results); 
			}
	}
</script>
</head>
<body onload="checkNotifications();">
<b>Strategy</b>
<br>
Risk Foreseen:
${incidentSelected.postIncident.riskForeseen}
<br>
Risk Evaluated:
${incidentSelected.postIncident.riskEvaluationAsString}
<br>
Strategy Implemented:
${incidentSelected.postIncident.strategyImplemented}
<br>
<br>
<b>People ratings of Strategy</b>
<br>
Overall:
${incidentSelected.postIncident.ratingOverall}
<br>
Effectiveness:
${incidentSelected.postIncident.ratingEffectiveness}
<br>
Improvement:
${incidentSelected.postIncident.ratingImprovementFromSituationBefore}
<br>
Practical:
${incidentSelected.postIncident.ratingPractical}
<br>
Relevance:
${incidentSelected.postIncident.ratingRelevanceToIncident}
<br>
Satisfaction:
${incidentSelected.postIncident.ratingSatisfactionOfStrategy}
<br>
<br>







<!-- The Risk Foreseen, Risk Evaluation, and Strategy Implemented fields, can only be filled out once.-->
<!--  And only can be filled out by branch manager-->
<!-- if condition below is to test this -->
<c:if test="${logAuth.authenticationLevel==1&&incidentSelected.postIncident.strategyImplementedAlready==false}">

<form action="implementStrategy" method="post" onSubmit="return validateStrategyForm()">
Please fill out the strategy you want to implement:
<br>
Risk Foreseen:
<input type="text" name="Foreseen" id="riskForeseen" >
<br>
<!-- Used radio button because important that user sees all the choices. Select option is when user doesn't have to see all the options -->
Risk Evaluation:
<br>
<br>
Likelihood:
<br>
<c:set var="probabilityArray" scope="page">Rare,Unlikely,Possible,Likely,Almost Certain</c:set>
<c:forEach var="probability" items="${probabilityArray}" varStatus="loop">
${probability}:
<input type="radio" name="Likelihood" value="${loop.index}">
<br>
</c:forEach>
<!-- @Naneth could you align the radio buttons (like in CreateIncidentReport) for this form please? Coz I saw it was aligned in CreateIncidentReport.jsp, but not sure how so yeh lol.. -->
<br>
Consequence:
<br>
<c:set var="consequenceArray" scope="page">Insignificant,Minor,Moderate,Major,Critical</c:set>
<c:forEach var="consequence" items="${consequenceArray}" varStatus="loop">
${consequence}:
<input type="radio" name="Consequences" value="${loop.index}">
<br>
</c:forEach>
<br>
Strategy implemented
<input type="text" name="Implement" id = "strategyImplemented">
<br>

<input type="submit" name="Strategy" value="Implement Strategy">
</form >
</c:if>
<br>
<br>









<!-- Each user can only rate the strategy once -->
<!-- Below if is to test that -->
<c:set var="thisUser" value="${logAuth.username}."/>
<c:if test = "${!fn:contains(incidentSelected.postIncident.staffWhoRatedStrategy,thisUser)}">

<!-- @Naneth would you be able to space out the horizontal radio buttons for this form more clearly please? So that they won't be confusing to user -->
<form action="setRatings" method="post" onSubmit="return validateRatingForm()">
Please rate the strategy that was implemented for feedback:
<br>
Overall:
<c:set var="overallArray">Very Bad,Bad,Neutral,Good,Very Good</c:set>
<c:forEach var="overall" items="${overallArray}" varStatus="loop">
${overall}
<input type="radio" name="Overall" value="${loop.index}">
</c:forEach>
<br>
<br>
Strategy Effectiveness:
<c:set var="effectivenessArray">Very Ineffective,Ineffective,Neutral,Effective,Very Effective</c:set>
<c:forEach var="effectiveness" items="${effectivenessArray}" varStatus="loop">
${effectiveness}
<input type="radio" name="Effectiveness" value="${loop.index}">
</c:forEach>
<br>
<br>
Improvement On Situation Before:
<c:set var="improvementArray">Alot Worse,Worse,Neutral,Better,Alot Better</c:set>
<c:forEach var="improvement" items="${improvementArray}" varStatus="loop">
${improvement}
<input type="radio" name="Improvement" value="${loop.index}">
</c:forEach>
<br>
<br>
Practical:
<c:set var="practicalArray">Very Impractical,Impractical,Neutral,Quite Practical,Very Practical</c:set>
<c:forEach var="practical" items="${practicalArray}" varStatus="loop">
${practical}
<input type="radio" name="Practical" value="${loop.index}">
</c:forEach>
<br>
<br>
Relevance to this incident:
<c:set var="relevanceArray">Very irrelevant,Irrelevant,Neutral,Relevant,Very Relevant</c:set>
<c:forEach var="relevance" items="${relevanceArray}" varStatus="loop">
${relevance}
<input type="radio" name="Relevance" value="${loop.index}" >
</c:forEach>
<br>
<br>
Satisfaction of strategy implemented:
<c:set var="satisfactionArray">Very Unsatisfied,Unsatisfied,Neutral,Satisfied,VerySatisfied</c:set>
<c:forEach var="satisfaction" items="${satisfactionArray}" varStatus="loop">
${satisfaction}
<input type="radio" name="Satisfaction" value="${loop.index}">
</c:forEach>
<br>
<input type="submit" name="Ratings" value="Rate Strategy">
</form>

</c:if>
<br>
<br>
<form action="DisplayIncidentReport.jsp" method="post">
<input type="submit" value="View Report">
</form>
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