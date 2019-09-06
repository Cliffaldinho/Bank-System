<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="style.css" />
	<title>Assign Staff to Incident</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
	<script type="text/javascript">
		function checkNotifications(){
			setInterval(checkForNotifications, 10000);
		}

		function checkForNotifications(){
			$.ajax({
				url: "notifications",
				type: "post",
				data: "<c:out value="${logAuth.username}"/>",
				success: function(results) {

					if(results!="") {
					alert(results); 
					}
				}
		});
		}
	</script>
</head>
<body onload="checkNotifications();">
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
	  <a href="prepareList">Incidents</a>
	  <a href="CreateIncidentReport.jsp">Report</a>
	  <a href="RolesForStaff.jsp">Roles</a>
	  <a href="index.jsp">Logout</a>
	</div>
	
</body>
</html>