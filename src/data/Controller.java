package data;

import java.util.*;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import java.io.PrintWriter;
import java.io.IOException;

//Please update to (done) when finished


/**

Presentation
Key Features: Zac

System Analysis and Design: 
Use Case Diagram (Geethma)
Class Diagram (Cliff) 
Database Diagram, Component Diagram, Deployment Diagram (Zac)
UI Diagram (Naneth)

Technologies and Frameworks
Naneth
Geethma 

Prototype
Zac
Cliff
Geethma
*/

//Geethma Contribution
//https://help.github.com/en/articles/why-are-my-contributions-not-showing-up-on-my-profile


/**
To do for presentation 

Cliff
Define Roles for Staff functionality (done)
Assign Staff to Incident functionality 

Geethma
Search, Sort, Count functionality

Naneth
All jsp files front end
Development technologies and environment slides (done)
UI Design diagram

Zac
User Login, Logout, Authorization level functionality
Database diagram, Architecture diagram
*/



/**
Create Incident Report (Cliff) (done)
Assign Priority Rating (Cliff) (done)
Detect Duplicates (Cliff) (done)

View List of Incident Reports (Cliff) (done)
Display Incident Report (Cliff) (done)
Select Incident (Cliff) (done)

Query Incident Reports (Geethma) 
Search Incident Reports (Geethma) (presentation)
Count Incident Reports (Geethma) (presentation)
Sort Incident Reports (Geethma) (presentation)

Handle Incident (Geethma)
Assign Staff to Incident (Geethma) (presentation)
Update Incident Report (Geethma)
Close Incident Report (Geethma)

Analyze Incident Post Completion (Cliff) (done)
Perform Root Cause Analysis (Cliff) (done)
Show Lessons Learnt (Cliff) (done)

Manage User (Zac)
Create User (Zac)
Delete User (Zac)
Modify User (Zac)
User login (Zac) (presentation)
User logout (Zac) (presentation)
User Login Authentication Bean thingy implementation that Tim mentioned (Zac)

Implement Risk Management Strategy (Naneth) 
Define Roles for Staff (Naneth) (presentation) (done by Cliff)
Test Strategy (Naneth)

 */

/**
(Naneth front end: will wait for jsp back end code to be done before it gets edited for front end)

CreateIncidentReport.jsp
ListOfIncidents.jsp
DetectDuplicates.jsp
DisplayIncidentReport.jsp
PerformAnalysis.jsp
RolesForStaff.jsp (done)
SetStaffRoles.jsp (done)
UserLogin.jsp

Please add other jsp files here that's finished back-end, and ready for front end work
 */


public class Controller extends HttpServlet {

	private static ArrayList<Incident> listOfIncidents;
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException  {
		
	}
	
	
}
