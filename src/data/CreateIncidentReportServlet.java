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
		if(tempCategory!=null) {
	
			
			switch(tempCategory) {
			
			case "regulatoryLaw":
				anIncident.setIncidentCategory(IncidentBean.Category.Regulatory_Law);
				break;
			case "cyberSecurity":
				anIncident.setIncidentCategory(IncidentBean.Category.Cyber_Security);
				break;
			case "humanIssues":
				anIncident.setIncidentCategory(IncidentBean.Category.Human_Issues);
				break;
			case "bankEquipment":
				anIncident.setIncidentCategory(IncidentBean.Category.Bank_Equipment);
				break;
			case "bankAlgorithms":
				anIncident.setIncidentCategory(IncidentBean.Category.Bank_Algorithms);
				break;
			case "other":
				anIncident.setIncidentCategory(IncidentBean.Category.Other);
				break;
			default:
				break;
			
			}
		}
		
		
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
			anIncident.setPossibleCausesOfIncident(possibleCauses);
		}
		
		
		//Set Possible Solutions for Incident and Staff who wrote the Possible Solutions
		possibleSolutions = req.getParameter("possibleSolutionsOfIncident");
		
		if(!possibleSolutions.isBlank()) {
			possibleSolutions = possibleSolutions + " ("+name+", "+position+")"; 
			anIncident.setPossibleSolutionsOfIncident(possibleSolutions);
		}
				
		
		//Set Priority for Incident
		String tempPriority;
		tempPriority=req.getParameter("thePriority");
		
		if(tempPriority!=null) {
			
			switch(tempPriority) {
				case "Low":
					anIncident.setPriorityRating(IncidentBean.Priority.Low);
					break;
				
				case "Medium":
					anIncident.setPriorityRating(IncidentBean.Priority.Medium);	
					break;
				
				case "High":
					anIncident.setPriorityRating(IncidentBean.Priority.High);
					break;
			}
			
			
		}
		
		//Detect Duplicates
		boolean possibleDuplicate=false;
		int duplicateID=anIncident.detectDuplicate();
		if(duplicateID!=-1) {
			possibleDuplicate=true;
		}
	
		
		if(possibleDuplicate==true) {
	
			IncidentBean originalIncident= IncidentDAO.getIncidentByIncidentID(duplicateID);
			boolean checkDuplicate=true;
			
			aSession.setAttribute("incidentID",duplicateID);
			aSession.setAttribute("incidentSelected", originalIncident);
			aSession.setAttribute("currentIncident", anIncident);
			
			req.setAttribute("checkDuplicate", checkDuplicate);

			req.getRequestDispatcher("DisplayIncidentReport.jsp").forward(req,res);
			
		} else {

			IncidentDAO.getIncidentsList().add(anIncident);
			
			req.getRequestDispatcher("prepareList").forward(req,res);
			
		}
	
	}
	
}
