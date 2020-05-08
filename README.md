# Bank-System

To log in: all user ids are b1, b2, b3, b4 etc. All passwords are "password". Passwords can be changed. 

To navigate the main page: "Incidents" displays a list of incidents, "Report" allows you to make a new incident, "Statistics" show all analytics, "Account" allows modification of personal details while also showing assigned incidents, and "Roles" (accessible only by branch manager) shows all the current users.

From these pages, all of the use cases and additional features are functional.

Use cases include:
1) Create Staff
2) Modify Staff
3) Delete Staff
4) Update Incident Report
5) Assign Staff to Incident
6) Close Incident Report
7) Define Roles for Staff
8) Implement Risk Management Strategy
9) Test Risk Management Strategy
10) Staff login
11) Staff logout
12) Create Incident Report
13) Assign Priority Ratings
14) Detect Duplicate Reports
15) View List of Incident Reports
16) Search Incident Reports
17) Sort Incident Reports
18) Count Incident Reports
19) Display Incident Report
20) Perform Root Cause Analysis
21) Show Lessons Learnt

Additional features we have are:
1) Auto Assign: The ability for a incident report to be automatically assigned to a staff member
2) Notifications: A notification popped up for a staff member when they have a new incident assigned to them.
3) Statistics: Analytics shown include amount of incidents in a particular category, and it's percentage among all incidents. Also shown are Ratings of Risk Management Strategy.
4) Automated lock: An account is locked after 3 failed attempts to login. Once locked, only branch manager can unlock that account.

Once new users have been created or existing ones have been modified, you will be able to log out of the system
and log in using these new accounts.

Note that some tasks are only available to users with the "branch manager" role, and will require an 
account with such permissions, such as the aforementioned "b4".

Our data is stored in DAOs.

The WebApp is deployed on heroku. It's url is:
https://bank-system-2019.herokuapp.com/