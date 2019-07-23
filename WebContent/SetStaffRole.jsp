<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="data.UserDAO" %>

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
		<%
			int staff=(int) request.getAttribute("indexOfStaff");
				String staffName;
				String staffID;
				String staffPosition;
				String currentRole;
				String password;
				String address;
				String contact;
				staffName=UserDAO.getUsersList().get(staff).getName();
				staffID=UserDAO.getUsersList().get(staff).getStaffID();
				staffPosition=UserDAO.getUsersList().get(staff).getPosition().toString();
				if (UserDAO.getUsersList().get(staff).getRolesToDo()!=null){
			currentRole=UserDAO.getUsersList().get(staff).getRolesToDo().toString();
				} else {
			currentRole="None";
				}
				password = UserDAO.getUsersList().get(staff).getPassword();
				address = UserDAO.getUsersList().get(staff).getAddress();
				contact = UserDAO.getUsersList().get(staff).getContactNumber();
				String setNone="None";
		%>
		<!-- 
		Staff Name
		Staff Position
		Current Role
		Set New Role
		 -->
		 <form action="finishDefineStaffRole" method="post">
		 <input type="hidden" name="index" value="<%out.println(staff);%>">
		<table>
				<tr>
					<td style="background: #dddddd">
						Staff Name:
					</td>
					<td>
						<input type="text" name="name" value="<%out.println(staffName);%>">
					</td>
				</tr>
				
				<tr>
					<td style="background: #dddddd">
						Staff ID:
					</td>
					<td>
						<input type="text" name="id" value="<%out.println(staffID);%>">"
					</td>
				</tr>
				
				<tr>
					<td style="background: #dddddd">
						Password:
					</td>
					<td>
						<input type="text" name="password" value="<%out.println(password);%>">"
					</td>
				</tr>
				
				<tr>
					<td style="background: #dddddd">
						Staff Position:
					</td>
					<td>
						<select name="position">
							<option value="branch" <%if(staffPosition.equals("Branch Manager")){out.println("selected");}%>>Branch Manager</option>
							<option value="data" <%if(staffPosition.equals("Data Processing Officer")){out.println("selected");}%>>Data Processing Officer</option>
							<option value="it" <%if(staffPosition.equals("IT")){out.println("selected");}%>>IT</option>
							<option value="finance" <%if(staffPosition.equals("Financial Analyst")){out.println("selected");}%>>Financial Analyst</option>
							<option value="auditor" <%if(staffPosition.equals("Internal Auditor")){out.println("selected");}%>>Internal Auditor</option>
						</select>
					</td>
				</tr>
				
				<tr>
					<td style="background: #dddddd">
						Address:
					</td>
					<td>
						<input type="text" name="address" value="<%out.println(address);%>">"
					</td>
				</tr>
				
				<tr>
					<td style="background: #dddddd">
						Contact Number:
					</td>
					<td>
						<input type="text" name="contact" value="<%out.println(contact);%>">"
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
							<input type="checkbox" name="Law" <%if(currentRole.contains("Regulatory Law")){out.println("checked");}%>><br>
						</td>
					</tr>
					
					<tr>
						<td>	
							Cyber Security
						</td>
						<td>	
							<input type="checkbox" name="Security" <%if(currentRole.contains("Cyber Security")){out.println("checked");}%>><br>
						</td>
					</tr>
					
					<tr>
						<td>	
							Human Issues
						</td>
						<td>	
							<input type="checkbox" name="Human" <%if(currentRole.contains("Human Issues")){out.println("checked");}%>><br>
						</td>
					</tr>
					
					<tr>
						<td>	
							Bank Equipment
						</td>
						<td>	
							<input type="checkbox" name="Equipment" <%if(currentRole.contains("Bank Equipment")){out.println("checked");}%>><br>
						</td>
					</tr>
					
					<tr>
						<td>	
							Bank Algorithms
						</td>
						<td>	
							<input type="checkbox" name="Algorithms" <%if(currentRole.contains("Bank Algorithms")){out.println("checked");}%>><br>
						</td>
					</tr>
					
					<tr>
						<td>	
							Other
						</td>
						<td>	
							<input type="checkbox" name="Other" <%if(currentRole.contains("Other")){out.println("checked");}%>><br>
						</td>
					</tr>
			</table>		
			<%out.println("<input type= \"hidden\" name=\"StaffIndex\" value=\""+staff+"\">"); %>
			<input type="submit" name="setTheRole" value="Modify User/Role"><br>
				
		</form>	
		
	
	</div>
</body>
</html>
