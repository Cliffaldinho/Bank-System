package data;

import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns={"/detectDuplicate"})
public class DetectDuplicateServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException {

		PrintWriter out = res.getWriter();
		
		String decision=req.getParameter("duplicateDecision");
		HttpSession aSession = req.getSession();
		
		if(decision!=null) {
			
			// if it is a duplicate
			if(decision.equalsIgnoreCase("Yes")) {
				
			int originalIndex = (int) aSession.getAttribute("originalIndex");
				
			//merge duplicated incident's description into original incident
			IncidentBean originalIncident = (IncidentBean) aSession.getAttribute("originalIncident");
			IncidentBean currentIncident = (IncidentBean) aSession.getAttribute("currentIncident");
		
			String currentDescription = currentIncident.getDescriptionOfIncident();
			String originalDescription = originalIncident.getDescriptionOfIncident();
			String newDescription = originalDescription+"<br>"+currentDescription;
			
			IncidentDAO.getIncidentsList().get(originalIndex).setDescriptionOfIncident(newDescription);

			
			//merge duplicated incident's possible causes into original incident
			String currentPossibleCauses = currentIncident.getPossibleCausesOfIncident();
			String originalPossibleCauses = originalIncident.getPossibleCausesOfIncident();
			String newPossibleCauses;
			
			if(!originalPossibleCauses.isBlank())
			{
				newPossibleCauses = originalPossibleCauses + "<br>" + currentPossibleCauses;
			} else {
				newPossibleCauses = originalPossibleCauses + currentPossibleCauses;
			}
			
			IncidentDAO.getIncidentsList().get(originalIndex).setPossibleCausesOfIncident(newPossibleCauses);
			
			
			//merge duplicated incident's possible solutions into original incident
			String currentPossibleSolutions = currentIncident.getPossibleSolutionsOfIncident();
			String originalPossibleSolutions = originalIncident.getPossibleSolutionsOfIncident();
			String newPossibleSolutions;
			
			if(!originalPossibleSolutions.isBlank())
			{
				newPossibleSolutions = originalPossibleSolutions + "<br>" + currentPossibleSolutions;
			} else {
				newPossibleSolutions = originalPossibleSolutions + currentPossibleSolutions;
			}
			
			IncidentDAO.getIncidentsList().get(originalIndex).setPossibleSolutionsOfIncident(newPossibleSolutions);
			
			//remove session attributes
			aSession.removeAttribute("originalIndex");
			aSession.removeAttribute("originalIncident");
			aSession.removeAttribute("currentIncident");
			
			req.getRequestDispatcher("ListOfIncidents.jsp").forward(req,res);
			}
			
			//if it is not a duplicate
			if(decision.equalsIgnoreCase("No")) {
			
			//add submitted incident as a new incident
			IncidentBean currentIncident = (IncidentBean) aSession.getAttribute("currentIncident");
			IncidentDAO.getIncidentsList().add(currentIncident);
			
			//remove session attributes
			aSession.removeAttribute("originalIndex");
			aSession.removeAttribute("originalIncident");
			aSession.removeAttribute("currentIncident");
			
			req.getRequestDispatcher("ListOfIncidents.jsp").forward(req,res);
			}
		}

		
		
		

	}
}
