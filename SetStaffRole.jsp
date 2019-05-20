<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="data.UserDatabase" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
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
Staff Name:
<%out.println(staffName);%>
<br>
Staff ID:
<%out.println(staffID); %>
<br>
Staff Position:
<%out.println(staffPosition); %>
<br>
Current Role:
<%out.println(currentRole); %>
<br>
<form action="finishDefineStaffRole" method="get">
Set New Role:
Regulatory Law
<input type="checkbox" name="Law">
Cyber Security
<input type="checkbox" name="Security">
Human Issues
<input type="checkbox" name="Human">
Bank Equipment
<input type="checkbox" name="Equipment">
Bank Algorithms
<input type="checkbox" name="Algorithms">
Other
<input type="checkbox" name="Other">
<%out.println("<input type= \"hidden\" name=\"StaffIndex\" value=\""+staff+"\">"); %>
<input type="submit" name="setTheRole" value="Set Role">
</form> 
</body>
</html>