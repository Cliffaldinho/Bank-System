package data;

import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import java.io.*;
import java.util.*;


//receives from CreateIncidentReport.jsp
@WebServlet(urlPatterns={"/createIncidentReport"})
public class CreateIncidentReportServlet extends HttpServlet {

	


	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		IncidentBean anIncident;
		anIncident= new IncidentBean();
		
		String title;
		String anIncidentDescription;
		String[] someIncidentKeywords;
		String possibleCauses;
		String possibleSolutions;
		
		
		PrintWriter out = res.getWriter();
		
		//set Incident ID
		anIncident.setIncidentID();
		
		//Set Incident Title
		title=req.getParameter("incidentTitle");
		anIncident.setIncidentTitle(title);
		
		
		//Set Incident Category
		String tempCategory;
		tempCategory=req.getParameter("incidentCategory");
		
		IncidentBean.Category category=IncidentBean.Category.valueOf(tempCategory);
		
		anIncident.setIncidentCategory(category);
		
		
		//Set Incident Description
		anIncidentDescription=req.getParameter("incidentDescription");
		
		anIncident.setDescriptionOfIncident(anIncidentDescription);
		

		//Set User which reported the Incident
		HttpSession aSession=req.getSession();
		StaffBean staff =(StaffBean) aSession.getAttribute("logAuth");
		
		UserBean user;
		user=staff.getUserByUsername();
		anIncident.setUserReportedIncident(user);
		
		
		//Set Incident Keywords
		String receiveKeywords,removeCommas,removeDots,removeMultipleSpaces,allLowerCases;
		receiveKeywords = req.getParameter("incidentKeywords");
		removeCommas=receiveKeywords.replaceAll("\\,", " ");
		removeDots=removeCommas.replaceAll("\\.", " ");
		removeMultipleSpaces=removeDots.replaceAll("\\s+"," ");
		allLowerCases=removeMultipleSpaces.toLowerCase();
		someIncidentKeywords=allLowerCases.split(" ");
		anIncident.setIncidentKeywords(someIncidentKeywords);
		
		
		
		//Set possible causes and solutions
		String name,position;
		name=user.getName();
		position=user.getPosition().toString();
		
		//Set Possible Causes for Incident and Staff who wrote the Possible Cause
		possibleCauses= req.getParameter("possibleCausesOfIncident");
		
		if(!possibleCauses.isBlank()) {
			possibleCauses = possibleCauses + " ("+name+", "+position+")";	
			
			//anIncident.setPossibleCausesOfIncident(possibleCauses);
			anIncident.getPostIncident().setPossibleCausesOfIncident(possibleCauses);
		}
		
		
		//Set Possible Solutions for Incident and Staff who wrote the Possible Solutions
		possibleSolutions = req.getParameter("possibleSolutionsOfIncident");
		
		if(!possibleSolutions.isBlank()) {
			possibleSolutions = possibleSolutions + " ("+name+", "+position+")"; 
			
			anIncident.getPostIncident().setPossibleSolutionsOfIncident(possibleSolutions);
			//anIncident.setPossibleSolutionsOfIncident(possibleSolutions);
		}
				
		
		//Set Priority for Incident
		String tempPriority;
		tempPriority=req.getParameter("thePriority");
		
		IncidentBean.Priority priority=IncidentBean.Priority.valueOf(tempPriority);
		anIncident.setPriorityRating(priority);
		
	
		
		//Detect Duplicates
		boolean possibleDuplicate=false;
		int duplicateID=anIncident.detectDuplicate();
		if(duplicateID!=-1) {
			possibleDuplicate=true;
		}
	
		
		//if it is a possible duplicate
		if(possibleDuplicate==true) {
			
			//get the incident id of the original incident
			IncidentBean originalIncident= IncidentDAO.getIncidentByIncidentID(duplicateID);
			
			//set a checkDuplicate checker for the DisplayIncidentReport page, to be true
			boolean checkDuplicate=true;
			
			//set session attributes for incident, so that DisplayIncidentReport and DetectDuplicateServlet can utilize it
			
			aSession.setAttribute("incidentID",duplicateID);	//this is id of original incident, to be utilized in DisplayIncidentReport
			
			aSession.setAttribute("incidentSelected", originalIncident);	//this is incident object of original incident, to be utilized in DisplayIncidentReport 
			
			aSession.setAttribute("currentIncident", anIncident);	//this is incident object of the newly entered incident, to be utilized in DetectDuplicateServlet
			
			//set the checkDuplicate as a request scope variable, coz only the DisplayIncidentReport page needs it
			req.setAttribute("checkDuplicate", checkDuplicate);	

			//forward to DisplayIncidentReport.jsp
			req.getRequestDispatcher("DisplayIncidentReport.jsp").forward(req,res);
			
			//if it's not a possible duplicate
		} else {

			//add it to incidents list
			IncidentDAO.getIncidentsList().add(anIncident);
			
			//forward to the prepareList controller 
			req.getRequestDispatcher("prepareList").forward(req,res);
			
		}
	
	}
	
}
