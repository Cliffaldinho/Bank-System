<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="data.UserDAO" %>
    <%@ page import="data.*" %>
    <%@page import="data.UserBean.Position"%>
    <%@taglib
    prefix="c"
    uri="http://java.sun.com/jsp/jstl/core" 
%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%    pageContext.setAttribute("userPosition", data.UserBean.Position.values()); %>
<%    pageContext.setAttribute("userRoles", data.IncidentBean.Category.values()); %>

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
<style>
#staffPosition {
text-align-last:center;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>

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
	
	<!-- if user is branch manager, show this  -->
	<div class="horizonta_nav">
	  <a href="prepareList">Incidents</a>
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
		<h2>Set Staff Role</h2>
	
		 <form action="finishDefineStaffRole" method="post">
		<!--   <input type="hidden" name="index" value="<%//out.println(staff);%>">-->
		<table>
				<tr>
					<td style="background: #dddddd">
						Staff ID:
					</td>
					<td>
						${userSelected.staffID }
						<!--  <input type="text" name="id" value="<%//out.println(staffID);%>">"-->
						<!-- input with value being what is wanted to be shown -->
					</td>
				</tr>
				<tr>
					<td style="background: #dddddd">
						Staff Name:
					</td>
					<td>
						${userSelected.name}
						<!--  <input type="text" name="name" value="<%//out.println(staffName);%>">-->
					</td>
				</tr>
				
				
				<tr>
					<td style="background: #dddddd">
						Staff Position:
					</td>
					<td>
					<!-- id used to align dropdown menu contents to center in css in head -->
						<select  class="selectInsideTable" name="position" id="staffPosition" >
						<!-- userPosition is a list declared at the top -->
						<c:forEach  var="element" items="${userPosition}">
						<option value="${element}"  ${element==userSelected.position? 'selected="selected"' : "" }>
						${element.toString()}
						</option>
						</c:forEach>
							
						</select>
					</td>
				</tr>
				
				<tr>
					<td style="background: #dddddd">
						Contact Number:
					</td>
					<td>
					${userSelected.contactNumber}
					</td>
				</tr>
				
				<tr>
					<td style="background: #dddddd">
						Address:
					</td>
					<td>
						${userSelected.address}
					</td>
				</tr>
				
				<tr>
					<td style="background: #dddddd">
						Current Role:
					</td>
					<td>
					${userSelected.rolesToDo}
					</td>
				</tr>				
		</table> 
		<br>
			<table>
				
					<tr>
						<th colspan="2">
							Set New Role:
						</th>
					</tr>
					
					<c:forEach var="options" items="${userRoles}">
					<tr>
					<td>
					${options.toString()}
					</td>
					<td>
					<input type="checkbox" name="roles" value="${options.toString()}" <c:if test="${fn:containsIgnoreCase(userSelected.rolesToDo,options.toString())}">checked</c:if> >
					</td>
					</tr>
					</c:forEach>
					
			</table>	
			<input type="submit" name="setTheRole" value="Modify User/Role"><br>
				
		</form>	
		
		<br>
		<c:if test="${userSelected.locked}">
		<form action="unlockAccount" method="post">
		Account is locked
		<br>
		<input type="submit" value="Unlock Account">
		</form>
		</c:if>
	
	</div>
	
	<form id="logOut" action="userLogout" method="post"></form>
	<form id="account" action="personalDetails" method="post"></form>
	<form id="statistics" action="showStatistics" method="post"></form>

	<script>
	</script>
</body>
</html>