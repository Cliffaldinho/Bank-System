package data;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns={"/lessonsLearnt"})
public class ShowLessonsLearntServlet extends HttpServlet{

	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException {
		
		HttpSession aSession = req.getSession();
		StaffBean staff = (StaffBean) aSession.getAttribute("logAuth");
		String staffID=staff.getUsername();
		UserBean user = UserDAO.getUserByStaffID(staffID);
		String name,position;
		name=user.getName();
		position=user.getPosition().toString();
		
		
		Simulation aSimulation = new Simulation();
		
		String theCause=req.getParameter("targetCause");
		String theActions=req.getParameter("actions");
		String theResults=req.getParameter("results");
		
		aSimulation.setRootCauseTargeted(theCause);
		aSimulation.setActionsTaken(theActions);
		aSimulation.setResultsFound(theResults);
		aSimulation.setStaff(name, position);
		
		String tempSolution;
		tempSolution = theActions + " ("+name+", "+position+")";
		
		int incidentID = (int) aSession.getAttribute("incidentID");
		IncidentBean incident = IncidentDAO.getIncidentByIncidentID(incidentID);
		
		//incident.addSimulation(aSimulation);
		incident.getPostIncident().addSimulation(aSimulation);
		
		//String solution = incident.getPossibleSolutionsOfIncident();
		String solution = incident.getPostIncident().getPossibleSolutionsOfIncident();
		
		String updateSolution;
		if(!solution.isBlank()) {
			updateSolution=solution+"<br>"+tempSolution;
		} else {
			updateSolution=solution+tempSolution;
		}
		
		//IncidentDAO.getIncidentByIncidentID(incidentID).setPossibleSolutionsOfIncident(updateSolution);
		IncidentDAO.getIncidentByIncidentID(incidentID).getPostIncident().setPossibleSolutionsOfIncident(updateSolution);
		
		req.getRequestDispatcher("PerformAnalysis.jsp").forward(req, res);

	}
}
