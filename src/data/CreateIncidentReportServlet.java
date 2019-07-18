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

	


	/**
	@Override
	public void init() throws ServletException {
		
		//anIncident = new Incident();

	}*/
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		Incident anIncident;
		anIncident= new Incident();
		
		String title;
		String anIncidentDescription;
		String[] someIncidentKeywords;
		String possibleCauses;
		String possibleSolutions;
		
		
		PrintWriter out = res.getWriter();
		
		//Set Incident Title
		title=req.getParameter("incidentTitle");
		anIncident.setIncidentTitle(title);
		


		//--------------------------------------------------------------------------------------------
		
		//Set Incident Category
		String tempCategory;
		tempCategory=req.getParameter("incidentCategory");
		if(tempCategory!=null) {
	
			
			switch(tempCategory) {
			
			case "regulatoryLaw":
				anIncident.setIncidentCategory(Incident.Category.Regulatory_Law);
				break;
			case "cyberSecurity":
				anIncident.setIncidentCategory(Incident.Category.Cyber_Security);
				break;
			case "humanIssues":
				anIncident.setIncidentCategory(Incident.Category.Human_Issues);
				break;
			case "bankEquipment":
				anIncident.setIncidentCategory(Incident.Category.Bank_Equipment);
				break;
			case "bankAlgorithms":
				anIncident.setIncidentCategory(Incident.Category.Bank_Algorithms);
				break;
			case "other":
				anIncident.setIncidentCategory(Incident.Category.Other);
				break;
			default:
				break;
			
			}
		}
		
		
		//Set Incident Description
		anIncidentDescription=req.getParameter("incidentDescription");
		

		
		anIncident.setDescriptionOfIncident(anIncidentDescription);
		
		//------------------------------------------------------------------------------------------------------
		

		//Set User which reported the Incident
		
		HttpSession aSession=req.getSession();
		StaffBean staff =(StaffBean) aSession.getAttribute("logAuth");

		//String theUserName=staff.getUsername();
		//out.println("The user id is"+theUserName);
		
		User user;
		user=staff.getUserByUsername();
		anIncident.setUserReportedIncident(user);
		
		//-----------------------------------------------------------------------------------------------------------
		
		//Set Incident Keywords

		String receiveKeywords,removeCommas,removeDots,removeMultipleSpaces,allLowerCases;
		receiveKeywords = req.getParameter("incidentKeywords");
		removeCommas=receiveKeywords.replaceAll("\\,", " ");
		removeDots=removeCommas.replaceAll("\\.", " ");
		removeMultipleSpaces=removeDots.replaceAll("\\s+"," ");
		allLowerCases=removeMultipleSpaces.toLowerCase();
		someIncidentKeywords=allLowerCases.split(" ");
		anIncident.setIncidentKeywords(someIncidentKeywords);
		
		
		
		//--------------------------------------------------------------------------------------------------------------
		
		//Set Possible Causes for Incident
		possibleCauses= req.getParameter("possibleCausesOfIncident");
		if(possibleCauses!=null) {
			anIncident.setPossibleCausesOfIncident(possibleCauses);
		}
		
		//-----------------------------------------------------------------------------------------------------------------
		
		//Set Possible Solutions for Incident
		possibleSolutions=req.getParameter("possibleSolutionsOfIncident");
		if(possibleSolutions!=null) {
			anIncident.setPossibleSolutionsOfIncident(possibleSolutions);
		}
				
		//-------------------------------------------------------------------------------------------------------------------
		
		//Set Priority for Incident
		String tempPriority;
		tempPriority=req.getParameter("thePriority");
		
		if(tempPriority!=null) {
			
			if(tempPriority.equalsIgnoreCase("Low")) {
				anIncident.setPriorityRating(Incident.Priority.Low);
			}
			else if (tempPriority.equalsIgnoreCase("Medium")) {
				anIncident.setPriorityRating(Incident.Priority.Medium);
			}
			else if (tempPriority.equalsIgnoreCase("High")) {
				anIncident.setPriorityRating(Incident.Priority.High);
			}
			
		}
		//Note:This implementation differs slightly from the use case description.
		//In Use Case Description, only branch manager can set priority
		//In here, any staff who hands in the incident report can set priority, and branch manager can modify/add priority if they think it's incorrect
	
		//---------------------------------------------------------------------------------------------------------------------

		
		//Detect Duplicates

		boolean possibleDuplicate=false;
		int duplicateIndex=anIncident.detectDuplicate();
		if(duplicateIndex!=-1) {
			possibleDuplicate=true;
		}
		
		
		//-----------------------------------------------------------------------------------------------------------------------
		
		if(possibleDuplicate==true) {
			req.setAttribute("theOriginalIndex", duplicateIndex);
			//out.println("duplicated");
			IncidentDatabase.getDuplicatesList().add(anIncident);
			req.getRequestDispatcher("DetectDuplicates.jsp").forward(req,res);
			
		} else {
			IncidentDatabase.getIncidentsList().add(anIncident);
			

			req.getRequestDispatcher("ListOfIncidents.jsp").forward(req,res);
			//out.println("original");
		}
	
		

		
	}
	
	
}
