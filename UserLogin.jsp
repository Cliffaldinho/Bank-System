<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="data.Incident" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css" />
<title>User Login</title>
</head>
<body>
<form action="userLogin" method="get">
<!-- Compulsory -->
User ID
<input type="text" name="userid">
<br>

<!-- Compulsory -->
Password
<input type="password" name="password">
<br>

<input type="submit">
</form>
</body>
</html>