package data;

import java.util.*;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import java.io.PrintWriter;
import java.io.IOException;

/**
Create Incident Report (Cliff)
Assign Priority Rating (Cliff)
Detect Duplicates (Cliff)

View List of Incident Reports (Cliff)
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
 */

/**
(Naneth front end)
JSP Page 1: User login, logout
JSP Page 2: Create Incident Report, Assign Priority Rating, Detect Duplicates
JSP Page 3: View List of Incident Reports, Display Incident Report, Select Incident, Query, Search, Sort, Count Incident Reports
JSP Page 4: Handle Incident, Update Incident Report, Close Incident Report
JSP Page 5: Analyze Incident Post Completion, Perform Root Cause Analysis, Show Lessons learnt
JSP Page 6: Manage User, Create User, Delete User, Modify User
 */

//Question: Should Controller be multiple servlets because of many use cases?

public class Controller extends HttpServlet {

	private static ArrayList<Incident> listOfIncidents;
	
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException  {
		
	}
	
	
}
