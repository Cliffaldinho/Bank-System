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
		
		StaffBean staff = (StaffBean) aSession.getAttribute("logAuth");
		String staffID=staff.getUsername();
		UserBean user = UserDAO.getUserByStaffID(staffID);
		
		String name,position;
		name=user.getName();
		position=user.getPosition().toString();
		
		
		//create a new simulation object
		Simulation aSimulation = new Simulation();
		
		//get the target cause, actions, and results from the PerformAnalysis.jsp
		String theCause=req.getParameter("targetCause");
		String theActions=req.getParameter("actions");
		String theResults=req.getParameter("results");
		
		//set the target cause, actions, results, and staff who did it in simulation object
		aSimulation.setRootCauseTargeted(theCause);
		aSimulation.setActionsTaken(theActions);
		aSimulation.setResultsFound(theResults);
		aSimulation.setStaff(name, position);
		
		//to store possible solution
		String tempSolution;
		
		//the possible solution is the action that staff entered
		tempSolution = theActions + " ("+name+", "+position+")";
		
		int incidentID = (int) aSession.getAttribute("incidentID");
		IncidentBean incident = IncidentDAO.getIncidentByIncidentID(incidentID);
		
		//add simulation for that incident
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
		
		req.getRequestDispatcher("PerformAnalysis.jsp").forward(req, res);

	}
}
