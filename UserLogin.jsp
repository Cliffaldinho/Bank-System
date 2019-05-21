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
<input type="text" name="password">
<br>

<input type="submit">
</form>
<p>
<c:if test="${not empty loginError}">
    <script>
    window.addEventListener("load",function(){
         alert("${loginError}");
    }
    </script>
</c:if>
</p>
</body>
</html>