package data;

import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;

//recieves from AssignStaffToIncident.jsp
@WebServlet(urlPatterns={"/finishAssignStaff"})
public class FinishAssignStaffServlet extends HttpServlet {

	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException {

		PrintWriter out = res.getWriter();
		
		//get the array of checkboxes checked for staff assigned
		String[] staffAssigned = req.getParameterValues("staff");
		//if no checkboxes checked, then array is null
		
		
		int incidentID;
		HttpSession aSession = req.getSession();
		incidentID = (int) aSession.getAttribute("incidentID");
		
		if(staffAssigned==null) {
			staffAssigned= new String[0];
		}
		//note form validation of assign staff (check if form is empty) takes place server side
		//so that branch manager can reallocate staff from an incident to a higher priority one

		
		//pass the array of assigned staff IDs for that incident, into it
		IncidentDAO.getIncidentByIncidentID(incidentID).setAssignedStaffID(staffAssigned);
		
		
		
		
		req.getRequestDispatcher("DisplayIncidentReport.jsp").forward(req, res);
		
	
	}
}
