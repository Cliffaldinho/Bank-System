package data;


import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;

@WebServlet(urlPatterns={"/updateIncidentStatus"})
public class UpdateIncidentStatusServlet extends HttpServlet{

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException {
		
		PrintWriter out = res.getWriter();
		
		HttpSession aSession = req.getSession();
		
		IncidentBean incidentSelected = (IncidentBean) aSession.getAttribute("incidentSelected");
		int incidentID= (int) aSession.getAttribute("incidentID");
		
		String incidentStatus = req.getParameter("status");
		
		switch(incidentStatus) {
		
		case "Incident fixed":
			
			//get the solution
			String storeSolution = req.getParameter("solution");
			
			StaffBean staff = (StaffBean) aSession.getAttribute("logAuth");
			UserBean user = staff.getUserByUsername();
			
			//get staff name and position
			String staffName,staffPosition;
			staffName=user.getName();
			staffPosition = user.getPosition().toString();
			
			//append staff name and position to the solution
			String solutionImplemented = storeSolution +" ("+staffName+", "+staffPosition+")";
			
			//store solution
			IncidentDAO.getIncidentByIncidentID(incidentID).setSolutionImplemented(solutionImplemented);
			
			//update incident status
			IncidentDAO.getIncidentByIncidentID(incidentID).setIncidentStatus(IncidentBean.Status.Fixed);
			
			break;
			
		case "Solution verified":
			
			//updates the incident status to verified
			IncidentDAO.getIncidentByIncidentID(incidentID).setIncidentStatus(IncidentBean.Status.Verified);
			
			//if it already has an analysis, straight away update incident status to analysis, skipping verified
			if(!IncidentDAO.getIncidentByIncidentID(incidentID).getPostIncident().getPossibleCausesOfIncident().isBlank()) {
				IncidentDAO.getIncidentByIncidentID(incidentID).setIncidentStatus(IncidentBean.Status.Analysis);
			}
			
			//get the solution verified, and store it in the possible solutions storage
			String solutionImplementedVerified=IncidentDAO.getIncidentByIncidentID(incidentID).getSolutionImplemented();
			String printImplemented = solutionImplementedVerified + " (Implemented)";
			
			String solution = incidentSelected.getPostIncident().getPossibleSolutionsOfIncident();
			
			String updateSolution;
			
			if(!solution.isBlank()) {
				updateSolution=solution+"<br>"+printImplemented;
			} else {
				updateSolution=solution+printImplemented;
			}
			
			IncidentDAO.getIncidentByIncidentID(incidentID).getPostIncident().setPossibleSolutionsOfIncident(updateSolution);
			
			
			break;
		
		case "Solution not verified":
			
			IncidentDAO.getIncidentByIncidentID(incidentID).setIncidentStatus(IncidentBean.Status.Assigned);
			break;
			
		default:
			
			break;
		}
		
		//out.println(IncidentDAO.getIncidentByIncidentID(incidentID).getIncidentStatus().toString());
		
		req.getRequestDispatcher("DisplayIncidentReport.jsp").forward(req, res);
		
	}
	
}
