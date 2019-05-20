<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ page import="data.*" %><!-- Naneth: was getting error when importing data.Incident -->
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Login</title>
</head>
<body>
<form action="userLogin" method="get">
<!-- Compulsory -->
<label for="userName">Username</label> <!-- Naneth: was getting error when "Username" was not in a tag -->
<input type="text" name="username">
<br>

<label for="Password">username</label><!-- Naneth: was getting error when "Password" was not in a tag -->
<input type="text" name="password">
<br>

<input type="submit">
</form>
</body>
</html>
