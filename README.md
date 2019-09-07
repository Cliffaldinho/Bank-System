# Bank-System

Data is the name of (source code) package.

Inside it stores the servlets, javabeans, and java classes.

Surface level is where html, javascript, jsps are stored.

To install: Place the war file inside your Tomcat directory, in its "webapps" folder. Start Tomcat. Go to the webpage http://localhost:8080/Bank-System/index.jsp.

To log in: all user ids are b1, b2, b3, b4 etc. All passwords are "password". Passwords can be changed. 

To navigate the main page: "Incidents" displays a list of incidents, "Report" allows you to make a new incident, "Roles" shows all the current users, and "Statistics" show all analytics.

From these pages, all of our use cases, and features, listed in our design document are doable. Additionally,
once new users have been created or existing ones have been modified, you will be able to log out of the system
and log in using these new accounts.

Note that some tasks are only available to users with the "branch manager" role, and will require an 
account with such permissions, such as the aforementioned "b4".

We coded sql tables, however we were unable to make the database function. Hence our data is stored in the DAOs.
