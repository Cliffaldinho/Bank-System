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

		
		String decision=req.getParameter("duplicateDecision");
		HttpSession aSession = req.getSession();
		
		if(decision!=null) {
			
			//it is a duplicate
			if(decision.equalsIgnoreCase("Yes")) {
				
			//merge duplicated incident's description into original incident
			IncidentBean originalIncident = (IncidentBean) aSession.getAttribute("originalIncident");
			IncidentBean currentIncident = (IncidentBean) aSession.getAttribute("currentIncident");
		
			String currentDescription = currentIncident.getDescriptionOfIncident();
			String originalDescription = originalIncident.getDescriptionOfIncident();
			String newDescription = originalDescription+"<br>"+currentDescription;
			
			int originalIndex = (int) aSession.getAttribute("originalIndex");
			IncidentDAO.getIncidentsList().get(originalIndex).setDescriptionOfIncident(newDescription);

			
			aSession.removeAttribute("originalIndex");
			aSession.removeAttribute("originalIncident");
			aSession.removeAttribute("currentIncident");
			
			req.getRequestDispatcher("ListOfIncidents.jsp").forward(req,res);
			}
			
			if(decision.equalsIgnoreCase("No")) {
				
			IncidentBean currentIncident = (IncidentBean) aSession.getAttribute("currentIncident");
			IncidentDAO.getIncidentsList().add(currentIncident);
			
			
			aSession.removeAttribute("originalIndex");
			aSession.removeAttribute("originalIncident");
			aSession.removeAttribute("currentIncident");
			
			req.getRequestDispatcher("ListOfIncidents.jsp").forward(req,res);
			}
		}

		
		
		

	}
}
