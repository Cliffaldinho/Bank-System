<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="data.Incident" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Login</title>
</head>
<body>
<form action="userLogin" method="get">
<!-- Compulsory -->
Username
<input type="text" name="username">
<br>

Password
<input type="text" name="password">
<br>

<input type="submit">
</form>
</body>
</html>