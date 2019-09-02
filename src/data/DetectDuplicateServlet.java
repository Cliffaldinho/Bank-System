package data;

import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;

//receives from DisplayIncidentReport.jsp
@WebServlet(urlPatterns={"/detectDuplicate"})
public class DetectDuplicateServlet extends HttpServlet {

	
	//In DisplayIncidentReport.jsp, user is shown the original copy of the (possibly duplicate incident) that they entered
	//And asked if is the incident they entered (the possibly duplicate one), is a duplicate of that or not
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException {

		PrintWriter out = res.getWriter();
		
		String decision=req.getParameter("duplicateDecision");
		HttpSession aSession = req.getSession();
		
		if(decision!=null) {
			
			// if it is a duplicate
			if(decision.equalsIgnoreCase("Yes")) {
			
			//get the incident id of the original copy
			int originalID = (int) aSession.getAttribute("incidentID");
				
			//merge duplicated incident's description into original incident
			IncidentBean originalIncident = (IncidentBean) aSession.getAttribute("incidentSelected");
			IncidentBean currentIncident = (IncidentBean) aSession.getAttribute("currentIncident");
			
			String currentDescription = currentIncident.getDescriptionOfIncident();
			String originalDescription = originalIncident.getDescriptionOfIncident();
			String newDescription = originalDescription+"<br>"+currentDescription;
			
			IncidentDAO.getIncidentByIncidentID(originalID).setDescriptionOfIncident(newDescription);
			
			//merge duplicated incident's possible causes into original incident
			String currentPossibleCauses = currentIncident.getPostIncident().getPossibleCausesOfIncident();
			
			String originalPossibleCauses = originalIncident.getPostIncident().getPossibleCausesOfIncident();
			
			String newPossibleCauses;
			
			
			if(!originalPossibleCauses.isBlank())
			{
				newPossibleCauses = originalPossibleCauses + "<br>" + currentPossibleCauses;
			} else {
				newPossibleCauses = originalPossibleCauses + currentPossibleCauses;
			}
			
			IncidentDAO.getIncidentByIncidentID(originalID).getPostIncident().setPossibleCausesOfIncident(newPossibleCauses);
			
			
			//merge duplicated incident's possible solutions into original incident
			String currentPossibleSolutions = currentIncident.getPostIncident().getPossibleSolutionsOfIncident();
			String originalPossibleSolutions = originalIncident.getPostIncident().getPossibleSolutionsOfIncident();
			
			String newPossibleSolutions;
			
			if(!originalPossibleSolutions.isBlank())
			{
				newPossibleSolutions = originalPossibleSolutions + "<br>" + currentPossibleSolutions;
			} else {
				newPossibleSolutions = originalPossibleSolutions + currentPossibleSolutions;
			}
			
			IncidentDAO.getIncidentByIncidentID(originalID).getPostIncident().setPossibleSolutionsOfIncident(newPossibleSolutions);
			
			aSession.removeAttribute("currentIncident");
			
			req.getRequestDispatcher("prepareList").forward(req,res);
			}
			
			//if it is not a duplicate
			if(decision.equalsIgnoreCase("No")) {
			
			//add submitted incident as a new incident
			IncidentBean currentIncident = (IncidentBean) aSession.getAttribute("currentIncident");
			IncidentDAO.getIncidentsList().add(currentIncident);
			
			aSession.setAttribute("incidentSelected",currentIncident);
			aSession.setAttribute("incidentID", currentIncident.getIncidentID());
			
			aSession.removeAttribute("currentIncident");
			
			req.getRequestDispatcher("autoAssign").forward(req, res);
			}
		}

		
		
		

	}
}
