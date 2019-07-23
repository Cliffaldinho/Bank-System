package data;

import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns={"/postAnalysis"})
public class PostAnalysisServlet extends HttpServlet {

	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException {
		
		PrintWriter out = res.getWriter();
	
		String tempRootCause;
		tempRootCause=req.getParameter("causes");
		
		HttpSession aSession = req.getSession();
		
		StaffBean staff = (StaffBean) aSession.getAttribute("logAuth");
		String staffID=staff.getUsername();
		UserBean user = UserDAO.getUserByStaffID(staffID);
		String name,position;
		name=user.getName();
		position=user.getPosition().toString();
		tempRootCause = tempRootCause + " ("+name+", "+position+")";
		
		int incidentID = (int) aSession.getAttribute("incidentID");
		IncidentBean incident = IncidentDAO.getIncidentByIncidentID(incidentID);
	
		
		String rootCause = incident.getPossibleCausesOfIncident();
		String updateRootCause;
		if(!rootCause.isBlank()) {
			updateRootCause = rootCause + "<br>" + tempRootCause;
		} else {
			updateRootCause = rootCause+tempRootCause;
		}
		
		IncidentDAO.getIncidentByIncidentID(incidentID).setPossibleCausesOfIncident(updateRootCause);

		req.getRequestDispatcher("PerformAnalysis.jsp").forward(req, res);
		}
		
	}
