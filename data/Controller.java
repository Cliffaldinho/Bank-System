package data;

import java.util.*;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import java.io.PrintWriter;
import java.io.IOException;

/**
Create Incident Report (Cliff) (done)
Assign Priority Rating (Cliff) (done)
Detect Duplicates (Cliff) (done)

View List of Incident Reports (Cliff) (done)
Display Incident Report (Cliff)
Select Incident (Cliff)

Query Incident Reports (Geethma)
Search Incident Reports (Geethma)
Count Incident Reports (Geethma)
Sort Incident Reports (Geethma)

Handle Incident (Geethma)
Update Incident Report (Geethma)
Close Incident Report (Geethma)

Analyze Incident Post Completion (Cliff)
Perform Root Cause Analysis (Cliff)
Show Lessons Learnt (Cliff)

Manage User (Zac)
Create User (Zac)
Delete User (Zac)
Modify User (Zac)
User login (Zac)
User logout (Zac)
User Authentication Bean thingy implementation that Tim mentioned (Zac)
 */

/**
(Naneth front end)

CreateIncidentReport.jsp
ListOfIncidents.jsp

Please add other jsp files here that's finished back-end, and ready for front end work
 */


public class Controller extends HttpServlet {

	private static ArrayList<Incident> listOfIncidents;
	
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException  {
		
	}
	
	
}
