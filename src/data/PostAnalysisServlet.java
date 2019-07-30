package data;

import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;

//this sets the analysis that was entered
//receives from PerformAnalysis.jsp
@WebServlet(urlPatterns={"/postAnalysis"})
public class PostAnalysisServlet extends HttpServlet {

	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException {
		
		PrintWriter out = res.getWriter();
	
		//gets the root cause entered parameter
		String tempRootCause;
		tempRootCause=req.getParameter("causes");
		
		HttpSession aSession = req.getSession();
		
		//gets the staffID of the staff logged in through StaffBean
		StaffBean staff = (StaffBean) aSession.getAttribute("logAuth");
		String staffID=staff.getUsername();
		
		//gets the UserBean from the UserDAO using that staffID
		UserBean user = UserDAO.getUserByStaffID(staffID);
		
		//gets the name of the logged in staff through the UserBean
		String name,position;
		name=user.getName();
		position=user.getPosition().toString();
		
		//appends the root cause that the logged in staff entered
		//with their name and position
		tempRootCause = tempRootCause + " ("+name+", "+position+")";
		
		//get the sessional incident ID and IncidentBean object
		int incidentID = (int) aSession.getAttribute("incidentID");
		IncidentBean incident = IncidentDAO.getIncidentByIncidentID(incidentID);
	
		//gets that incident root cause that is stored in the database
		String rootCause = incident.getPostIncident().getPossibleCausesOfIncident();
		String updateRootCause;
		
		//if there is stuff stored
		if(!rootCause.isBlank()) {
			//go down one line then append
			updateRootCause = rootCause + "<br>" + tempRootCause;
		//if there is no stuff stored
		} else {
			//append
			updateRootCause = rootCause+tempRootCause;
		}
		
		//add the updated root cause to the database
		IncidentDAO.getIncidentByIncidentID(incidentID).getPostIncident().setPossibleCausesOfIncident(updateRootCause);

		//go back to PerformAnalysis.jsp
		req.getRequestDispatcher("PerformAnalysis.jsp").forward(req, res);
		}
		
	}
