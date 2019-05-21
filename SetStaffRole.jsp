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
	  <a href="UserLogin.jsp">Logout</a>
	</div>
	<br>
	<br>

	<div class="container">	
		<h2>Set Staff Role</h2>
		<%
		int staff=(int) request.getAttribute("indexOfStaff");
		String staffName;
		String staffID;
		String staffPosition;
		String currentRole;
		staffName=UserDatabase.getUsersList().get(staff).getName();
		staffID=UserDatabase.getUsersList().get(staff).getStaffID();
		staffPosition=UserDatabase.getUsersList().get(staff).getPosition().toString();
		currentRole=UserDatabase.getUsersList().get(staff).getRolesToDo();
		String setNone="None";
		if(currentRole==null) {
			currentRole=setNone;
		}
		%>
		<!-- 
		Staff Name
		Staff Position
		Current Role
		Set New Role
		 -->
		 
		<table>
				<tr>
					<td style="background: #dddddd">
						Staff Name:
					</td>
					<td>
						<%out.println(staffName);%>
					</td>
				</tr>
				
				<tr>
					<td style="background: #dddddd">
						Staff ID:
					</td>
					<td>
						<%out.println(staffName);%>
					</td>
				</tr>
				
				<tr>
					<td style="background: #dddddd">
						Staff Position:
					</td>
					<td>
						<%out.println(staffPosition); %>
					</td>
				</tr>
				
				<tr>
					<td style="background: #dddddd">
						Current Role:
					</td>
					<td>
						<%out.println(currentRole); %>
					</td>
				</tr>				
		</table> 
		<br>

		<form action="finishDefineStaffRole" method="get">
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
			
			<input type="submit" name="setTheRole" value="Set Role"><br>
		</form>	
		
		<%out.println("<input type= \"hidden\" name=\"StaffIndex\" value=\""+staff+"\">"); %>
	</div>
</body>
</html>
