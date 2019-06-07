<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="data.UserDatabase" %>

<%HttpSession aSession = request.getSession();%>
<jsp:useBean id="logAuth" class="data.StaffBean" scope="session" />
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css" />
<title>Insert title here</title>
<script>
function validateName(){
	window.alert("Please enter a name."); 
}
function validateID(){
	window.alert("Please enter a valid ID."); 
}
function validateAddress(){
	window.alert("Please enter an address."); 
}
function validateContact(){
	window.alert("Please enter a contact number."); 
}
function validatePassword(){
	window.alert("Please enter a password."); 
}
</script>
</head>
<%
System.out.println(request.getAttribute("errorType"));
if (request.getAttribute("errorType") != null){
	String errorType = (String)request.getAttribute("errorType");
	System.out.println(errorType);
	if (errorType.equals("name")){
		out.print("<body onload=\"validateName()\">");
	} else if (errorType.equals("password")){
		out.print("<body onload=\"validatePassword()\">");
	} else if (errorType.equals("id")){
		out.print("<body onload=\"validateID()\">");
	} else if (errorType.equals("address")){
		out.print("<body onload=\"validateAddress()\">");
	} else if (errorType.equals("contact")){
		out.print("<body onload=\"validateContact()\">");
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
	  <a href="CreateIncidentReport.jsp">Report</a>
	  <a href="RolesForStaff.jsp">Roles</a>
	  <a href="index.jsp">Logout</a>
	</div>
	<br>
	<br>

	<div class="container">	
		<h2>Set Staff Role</h2>
		<!-- 
		Staff Name
		Staff Position
		Current Role
		Set New Role
		 -->
		 <form name="UserCreate" action="createUser" method="post">
		 <input type="hidden" name="index" value="">
		<table>
				<tr>
					<td style="background: #dddddd">
						Staff Name:
					</td>
					<td>
						<input type="text" name="name" value="<%if(request.getAttribute("errorType") != null){out.print(request.getParameter("name"));}%>">
					</td>
				</tr>
				
				<tr>
					<td style="background: #dddddd">
						Staff ID:
					</td>
					<td>
						<input type="text" name="id" value="<%if(request.getAttribute("errorType") != null){out.print(request.getParameter("id"));}%>">
					</td>
				</tr>
				
				<tr>
					<td style="background: #dddddd">
						Password:
					</td>
					<td>
						<input type="text" name="password" value="<%if(request.getAttribute("errorType") != null){out.print(request.getParameter("password"));}%>">
					</td>
				</tr>
				
				<tr>
					<td style="background: #dddddd">
						Staff Position:
					</td>
					<td>
						<select name="position">
							<option value="branch">Branch Manager</option>
							<option value="data">Data Processing Officer</option>
							<option value="it">IT</option>
							<option value="finance">Financial Analyst</option>
							<option value="auditor">Internal Auditor</option>
						</select>
					</td>
				</tr>
				
				<tr>
					<td style="background: #dddddd">
						Address:
					</td>
					<td>
						<input type="text" name="address" value="<%if(request.getAttribute("errorType") != null){out.print(request.getParameter("address"));}%>">
					</td>
				</tr>
				
				<tr>
					<td style="background: #dddddd">
						Contact Number:
					</td>
					<td>
						<input type="text" name="contact" value="<%if(request.getAttribute("errorType") != null){out.print(request.getParameter("contact"));}%>">
					</td>
				</tr>
				
				<tr>
					<td style="background: #dddddd">
						Current Role:
					</td>
					<td>
						None
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
			</table>		
			<input type="submit" name="createUser" value="create New User"><br>
				
		</form>	
		
	
	</div>
</body>
</html>
