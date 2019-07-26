<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="data.UserDAO" %>
    <%@ page import="data.*" %>
        <%@taglib
    prefix="c"
    uri="http://java.sun.com/jsp/jstl/core" 
%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%    pageContext.setAttribute("userPosition", data.UserBean.Position.values()); %>
<%    pageContext.setAttribute("userRoles", data.IncidentBean.Category.values()); %>

<%HttpSession aSession = request.getSession();%>
<jsp:useBean id="logAuth" class="data.StaffBean" scope="session" />

    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css" />
<title>Insert title here</title>
<style>
select:required:invalid{
color:gray;
}
#invalidOption{
display:none
}
#validOption{
color:black
}

</style>
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
	  <a href="CreateIncidentReport.jsp">Report</a>
	  <a href="RolesForStaff.jsp">Roles</a>
	  <a href="index.jsp">Logout</a>
	</div>
	<br>
	<br>

	<div class="container">	
		<h2>Set Staff Role</h2>

		 <form name="UserCreate" action="createUser" method="post" onSubmit="return validateForm()">
		<table>
				<tr>
					<td style="background: #dddddd">
						Staff Name:
					</td>
					<td>
						<input type="text" name="name" id="staffName">
					</td>
				</tr>
				<tr>
					<td style="background: #dddddd">
						Contact Number:
					</td>
					<td>
						<input type="text" name="contact" id="staffContactNumber">
					</td>
				</tr>
				<tr>
					<td style="background: #dddddd">
						Address:
					</td>
					<td>
						<input type="text" name="address" id="staffAddress">
					</td>
				</tr>
				<tr>
					<td style="background: #dddddd">
						Staff Position:
					</td>
					<td>
					
					<select name="position" required>
					<option disabled selected value="" id="invalidOption">Select position..</option>
					<c:forEach var = "element" items="${userPosition}">
					<option value="${element}" id="validOption">
					${element.toString()}
					</option>
					</c:forEach>
					
					</select>
					
					<!--  	<select name="position">
					 		<option disabled selected value>Please choose a position</option>
							<option value="branch">Branch Manager</option>
							<option value="data">Data Processing Officer</option>
							<option value="it">IT</option>
							<option value="finance">Financial Analyst</option>
							<option value="auditor">Internal Auditor</option>
						</select>-->
					</td>
				</tr>
				
				

				
		</table> 
		<br>
		
			<table>
				
					<tr>
						<th colspan="2">
							Set Role:
						</th>
					</tr>
					
					<c:forEach var="options" items="${userRoles}">
					<tr>
					<td>
					${options.toString()}
					</td>
					<td>
					<input type="checkbox" name="roles" value="${options.toString()}" >
					</td>
					</tr>
					</c:forEach>
					
					 <!-- 
					<tr>
						<td>	
							Regulatory Law
						</td>
						<td>	
							<input type="checkbox" name="Law"><br>
						</td>
					</tr>
					
					<tr>
						<td>	
							Cyber Security
						</td>
						<td>	
							<input type="checkbox" name="Security"><br>
						</td>
					</tr>
					
					<tr>
						<td>	
							Human Issues
						</td>
						<td>	
							<input type="checkbox" name="Human"><br>
						</td>
					</tr>
					
					<tr>
						<td>	
							Bank Equipment
						</td>
						<td>	
							<input type="checkbox" name="Equipment"><br>
						</td>
					</tr>
					
					<tr>
						<td>	
							Bank Algorithms
						</td>
						<td>	
							<input type="checkbox" name="Algorithms"><br>
						</td>
					</tr>
					
					<tr>
						<td>	
							Other
						</td>
						<td>	
							<input type="checkbox" name="Other"><br>
						</td>
					</tr>
					 -->
			</table>		
			<input type="submit" name="createUser" value="create New User"><br>
				
		</form>	
		
	
	</div>
<script>

function validateForm() {
	
	if(document.getElementById("staffName").value=="") {
		window.alert("Please enter a name."); 
		return false;
	}
	
	if(document.getElementById("staffAddress").value=="") {
		window.alert("Please enter an address."); 
		return false;
	}
	
	if(document.getElementById("staffContactNumber").value=="") {
		window.alert("Please enter a contact number."); 
		return false;
	}
	
/*	if(document.getElementById("staffPosition").value=="") {
		window.alert("Please select a position."); 
		return false;
	}*/

}
</script>
</body>
</html>
