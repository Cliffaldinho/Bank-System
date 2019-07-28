package data;

import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns={"/finishAssignStaff"})
public class FinishAssignStaffServlet extends HttpServlet {

	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException {
		//in database
		//decide store one colomn per piece of information
		//and avoid parsing strings to get staffid
		//hence store in String[] instead of a long String
		PrintWriter out = res.getWriter();
		
		String[] staffAssigned = req.getParameterValues("staff");
		
		int incidentID;
		HttpSession aSession = req.getSession();
		incidentID = (int) aSession.getAttribute("incidentID");
		
		
		if(staffAssigned==null) {
			staffAssigned= new String[0];
		}
		//note form validation of assign staff (check if form is empty) takes place server side
		//this is because if we stop branch manager from submitting form in AssignStaff.jsp client side when condition is no checkbox ticked
		//that would mean that if branch manager has already assigned staff previously
		//and wants to reallocate the human resources to another high priority incident, due to this incident being low priority,
		//they wouldn't be able to due to client side form validation preventing no checkbox ticked
		//hence validate form server side
		
		IncidentDAO.getIncidentByIncidentID(incidentID).setAssignedStaffID(staffAssigned);
		
		
		
		req.getRequestDispatcher("DisplayIncidentReport.jsp").forward(req, res);
		
	
	}
}
