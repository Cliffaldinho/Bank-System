package data;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;

//sets the Lessons Learnt, and Simulations
//receives from PerformAnalysis.jsp
@WebServlet(urlPatterns={"/lessonsLearnt"})
public class ShowLessonsLearntServlet extends HttpServlet{

	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException {
		
		HttpSession aSession = req.getSession();
		
		//get the staff who is logged in
		StaffBean staff = (StaffBean) aSession.getAttribute("logAuth");
		String staffID=staff.getUsername();
		UserBean user = UserDAO.getUserByStaffID(staffID);
		
		//get the staff logged in name and position
		String name,position;
		name=user.getName();
		position=user.getPosition().toString();
		
		
		//create a new simulation object
		Simulation aSimulation = new Simulation();
		
		//get the target cause, actions, and results from the PerformAnalysis.jsp
		String theCause=req.getParameter("targetCause");
		String theActions=req.getParameter("actions");
		String theResults=req.getParameter("results");
		
		//set the target cause, actions, results, and staff who did it
		aSimulation.setRootCauseTargeted(theCause);
		aSimulation.setActionsTaken(theActions);
		aSimulation.setResultsFound(theResults);
		aSimulation.setStaff(name, position);
		
		//stores the solution
		String tempSolution;
		
		//the possible solution is the action that staff entered
		tempSolution = theActions + " ("+name+", "+position+")";
		
		//get the incidentID and IncidentBean
		int incidentID = (int) aSession.getAttribute("incidentID");
		IncidentBean incident = IncidentDAO.getIncidentByIncidentID(incidentID);
		
		//add that simulation for that incident
		incident.getPostIncident().addSimulation(aSimulation);
		
		//update the solution for that incident
		String solution = incident.getPostIncident().getPossibleSolutionsOfIncident();
		
		String updateSolution;
		if(!solution.isBlank()) {
			updateSolution=solution+"<br>"+tempSolution;
		} else {
			updateSolution=solution+tempSolution;
		}
		
		IncidentDAO.getIncidentByIncidentID(incidentID).getPostIncident().setPossibleSolutionsOfIncident(updateSolution);
		
		//go back to PerformAnalysis.jsp
		req.getRequestDispatcher("PerformAnalysis.jsp").forward(req, res);

	}
}
