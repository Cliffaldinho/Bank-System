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
<title>User Login</title>
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
	
	<br>
	<br>

<div class="container">		
	<h2>User Login</h2>
	<form action="userLogin" method="post">
		<!-- Compulsory -->
		<label for="userid">User ID</label> <!-- Naneth: was getting error when "Username" was not in a tag -->
		<input type="text" name="userid">
		<br>
		<br>
		
		<label for="Password">Password </label><!-- Naneth: was getting error when "Password" was not in a tag -->
		<input type="text" name="password">
		<br>
		<br>
		
		<input type="submit" name= "login" value="Login" />  
		
	</form>
</div>


</body>
</html>
