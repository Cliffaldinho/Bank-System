package data;

import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import java.io.*;
import java.util.*;

@WebServlet(urlPatterns={"/notifications"})
public class AutoAssignServlet extends HttpServlet {

	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException {
		
		PrintWriter out = res.getWriter();
		
		HttpSession aSession = req.getSession();
		
		StaffBean logAuth = (StaffBean) aSession.getAttribute("logAuth");
		
		IncidentBean[] assignedArray = logauth.getAssignedUnreadIncidents();
		
		
		if (assignedArray.length == 0){
			
		} else {
			String output = "You have been recently assigned:\n"
			for (int i = 0; i < assignedArray.length; i++){
				output += "Incident ID: "
				output += assignedArray[i].getIncidentID();
				output += " - Priority Rating: ";
				output += assignedArray[i].getPriorityRating();
				output += "\n";
			}
			out.write(output);
		}
		
		
		//int incidentID = (int) aSession.getAttribute("incidentID");
		
		//IncidentDAO.getIncidentByIncidentID(incidentID).autoAssignStaff();
		
		//req.getRequestDispatcher("prepareList").forward(req,res);
		
		
		
	}
}
