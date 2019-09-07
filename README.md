# Bank-System

Data is the name of (source code) package.

Inside it stores the servlets, javabeans, and java classes.

Surface level is where html, javascript, jsps are stored.

To install: Place the war file inside your Tomcat directory, in its "webapps" folder. Start Tomcat. Go to the webpage http://localhost:8080/Bank-System/index.jsp.

To log in: all user ids are b1, b2, b3, b4 etc. All passwords are "password". Passwords can be changed. 

To navigate the main page: "Incidents" displays a list of incidents, "Report" allows you to make a new incident, "Statistics" show all analytics, "Account" allows modification of personal details while also showing assigned incidents, and "Roles" (accessible only by branch manager) shows all the current users.

From these pages, all of our use cases, and features, listed in our design document are doable. Additionally,
once new users have been created or existing ones have been modified, you will be able to log out of the system
and log in using these new accounts.

The features we have are:
1) Auto Assign: The ability for a incident report to be automatically assigned to a staff member
2) Notifications: A notification popped up for a staff member when they have a new incident assigned to them.
3) Statistics: Analytics shown include amount of incidents in a particular category, and it's percentage among all incidents. Also shown are Ratings of Risk Management Strategy.
4) Automated lock: An account is locked after 3 failed attempts to login. Once locked, only branch manager can unlock that account.

Note that some tasks are only available to users with the "branch manager" role, and will require an 
account with such permissions, such as the aforementioned "b4".

We coded sql tables, however we were unable to make the database function. Hence our data is stored in the DAOs.
