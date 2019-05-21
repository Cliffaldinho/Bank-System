package data;

import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;


//receives from CreateIncidentReport.jsp

@WebServlet(urlPatterns={"/createIncidentReport"})
public class CreateIncidentReportServlet extends HttpServlet {

	


	/**
	@Override
	public void init() throws ServletException {
		
		//anIncident = new Incident();

	}*/
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		Incident anIncident;
		anIncident= new Incident();
		
		String title;
		int anIncidentDate;
		String anIncidentMonth;
		int anIncidentYear;
		String anIncidentDescription;
		String nameOfStaff;
		String positionOfStaff;
		String aStaffID;
		String[] someIncidentKeywords;
		Incident.Priority priorityLevel;
		String possibleCauses;
		String possibleSolutions;
		boolean duplicate;
		
		
		PrintWriter out = res.getWriter();
		
		//Set Incident Title
		title=req.getParameter("incidentTitle");
		anIncident.setIncidentTitle(title);

		//--------------------------------------------------------------------------------------------
		
		//Set Incident Category
		String tempCategory;
		tempCategory=req.getParameter("incidentCategory");
		if(tempCategory!=null) {
			if (tempCategory.equalsIgnoreCase("regulatoryLaw")) {
				anIncident.setIncidentCategory(Incident.Category.Regulatory_Law);
			} 
			else if (tempCategory.equalsIgnoreCase("cyberSecurity")) {
				anIncident.setIncidentCategory(Incident.Category.Cyber_Security);
			}
			else if (tempCategory.equalsIgnoreCase("humanIssues")) {
				anIncident.setIncidentCategory(Incident.Category.Human_Issues);
			}
			else if (tempCategory.equalsIgnoreCase("bankEquipment")) {
				anIncident.setIncidentCategory(Incident.Category.Bank_Equipment);
			}
			else if (tempCategory.equalsIgnoreCase("bankAlgorithms")) {
				anIncident.setIncidentCategory(Incident.Category.Bank_Algorithms);
			} 
			else if (tempCategory.equalsIgnoreCase("other")) {
				anIncident.setIncidentCategory(Incident.Category.Other);
			}
		}
		
		//----------------------------------------------------------------------------------------------
		
		//Set Incident Date
		String tempDay;
		String deleteNonNum;
		tempDay = req.getParameter("incidentDay");
		deleteNonNum=tempDay.replaceAll("[^0-9.]", "");
		anIncidentDate = Integer.parseInt(deleteNonNum);
		
		String tempMonth,tempMonthTwo;
		tempMonth = req.getParameter("incidentMonth");
		tempMonthTwo=tempMonth.substring(0,1).toUpperCase()+tempMonth.substring(1);
		anIncidentMonth = tempMonthTwo.replaceAll("\\s+", "");
		
		
		String tempYear,tempYearTwo;
		tempYear=req.getParameter("incidentYear");
		tempYearTwo = tempYear.replaceAll("\\s+", "");
		anIncidentYear=Integer.parseInt(tempYearTwo);
		
		anIncident.setIncidentDateOfMonth(anIncidentDate);
		anIncident.setIncidentMonth(anIncidentMonth);
		anIncident.setIncidentYear(anIncidentYear);
		
		//----------------------------------------------------------------------------------------------------
		
		//Set Incident Description
		anIncidentDescription=req.getParameter("incidentDescription");
		anIncident.setDescriptionOfIncident(anIncidentDescription);
		
		//------------------------------------------------------------------------------------------------------
		

		//Set User which reported the Incident
		
		//temporarily creating a user here for practice
		//in real tea user should be gotten straight from database
		if(UserDatabase.getUsersList().isEmpty()) {
		User tempUser = new User("Bob Smith","Elm Street","0403526395",User.Position.Financial_Analyst,"f111");
		User tempUserTwo = new User("Alice Diaz","Avoca Lane","0423436405",User.Position.Internal_Auditor,"a111");
		User tempUserThree = new User("Henry Stewart","Mahogany Lane","0435243964",User.Position.Data_Processing_Officer,"d111");
		User tempUserFour = new User("Enzo Rogers","Wilsons Creek","0432364354",User.Position.Branch_Manager,"b111");
		User tempUserFive = new User ("Chloe Morgan","Chicago Lane","0473423537",User.Position.IT,"i111");
		UserDatabase.addUsers(tempUser);
		UserDatabase.addUsers(tempUserTwo);
		UserDatabase.addUsers(tempUserThree);
		UserDatabase.addUsers(tempUserFour);
		UserDatabase.addUsers(tempUserFive);}
		//above few lines of code don't count in this class
		
		aStaffID=req.getParameter("theStaffID");
		
		nameOfStaff = req.getParameter("staffName");
		String splitName[]=nameOfStaff.split(" ");
		String firstName=splitName[0];
		String secondName="none";
		if(splitName.length>1) {
			secondName=splitName[1];
		}
		
		String tempPosition;
		String position="none";
		tempPosition=req.getParameter("staffPosition");
		if(tempPosition!=null) {
			if(tempPosition.equalsIgnoreCase("branchManager")) {
				position=User.Position.Branch_Manager.toString();
				
			} else if (tempPosition.equalsIgnoreCase("DPO")) {
				position=User.Position.Data_Processing_Officer.toString();
				
			} else if (tempPosition.equalsIgnoreCase("IT")) {
				position=User.Position.IT.toString();
				
			} else if (tempPosition.equalsIgnoreCase("finance")) {
				position=User.Position.Financial_Analyst.toString();
				
			} else if (tempPosition.equalsIgnoreCase("audit")) {
				position=User.Position.Internal_Auditor.toString();
			} 
			
		}
		
		

		if(aStaffID!=null&&nameOfStaff!=null&&!position.equalsIgnoreCase("none")) {
			
		int tempIndex=UserDatabase.findUserIndexByStaffID(aStaffID);
		boolean verify=false;
		
		if(	UserDatabase.getUserByIndex(tempIndex).getPosition().toString().equalsIgnoreCase(position)&&
				(	(UserDatabase.getUserByIndex(tempIndex).getName().toLowerCase().contains(firstName.toLowerCase()))||
				(UserDatabase.getUserByIndex(tempIndex).getName().toLowerCase().contains(secondName.toLowerCase()))	)	) {
			verify=true;
		}
		
		if(verify==true) {
		anIncident.setUserReportedIncident(UserDatabase.getUserByIndex(tempIndex));
		//out.println("yes");
		}
		
		}
		
		//-----------------------------------------------------------------------------------------------------------
		
		//Set Incident Keywords
		
		String tempKeywordsOne,tempKeywordsTwo,tempKeywordsThree,tempKeywordsFour,tempKeywordsFive;
		tempKeywordsOne=req.getParameter("incidentKeywords");
		tempKeywordsTwo=tempKeywordsOne.replaceAll("\\,", " ");
		//out.println("Two is"+tempKeywordsTwo);
		tempKeywordsThree=tempKeywordsTwo.replaceAll("\\.", " ");
		//out.println("Three is "+tempKeywordsThree);
		tempKeywordsFour=tempKeywordsThree.replaceAll("\\s+"," ");
		//out.println("Four is "+tempKeywordsFour);
		tempKeywordsFive=tempKeywordsFour.toLowerCase();
		//out.println("Five is "+tempKeywordsFive);
		someIncidentKeywords=tempKeywordsFive.split(" ");
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
		int duplicatedIndex=-1;
		String databaseKeyword;
		String incidentKeyword;
		
		//if the incident being reported, compared to the ones in the database, has the
		outerloop:
		for(int i=0;i<IncidentDatabase.getIncidentsList().size();i++) {
			
			//same category
			if(anIncident.getIncidentCategory()==IncidentDatabase.getIncidentsList().get(i).getIncidentCategory()) {
				
				//same month and year
				if(	(anIncident.getIncidentMonth().equalsIgnoreCase(IncidentDatabase.getIncidentsList().get(i).getIncidentMonth()))&&
						(anIncident.getIncidentYear()==IncidentDatabase.getIncidentsList().get(i).getIncidentYear())	)
				{
				
				
				int date=anIncident.getIncidentDateOfMonth();
					int a=date+1;
					int b=date+2;
					int c=date+3;
					int d=date+4;
					int e=date+5;
					int f=date+6;
					int g=date+7;
					int h=date-1;
					int j=date-2;
					int k=date-3;
					int l=date-4;
					int m=date-5;
					int n=date-6;
					int o=date-7;
					
					//around the same date, give or take a week
					if(IncidentDatabase.getIncidentsList().get(i).getIncidentDateOfMonth()==date||
							IncidentDatabase.getIncidentsList().get(i).getIncidentDateOfMonth()==a||
							IncidentDatabase.getIncidentsList().get(i).getIncidentDateOfMonth()==b||
							IncidentDatabase.getIncidentsList().get(i).getIncidentDateOfMonth()==c||
							IncidentDatabase.getIncidentsList().get(i).getIncidentDateOfMonth()==d||
							IncidentDatabase.getIncidentsList().get(i).getIncidentDateOfMonth()==e||
							IncidentDatabase.getIncidentsList().get(i).getIncidentDateOfMonth()==f||
							IncidentDatabase.getIncidentsList().get(i).getIncidentDateOfMonth()==g||
							IncidentDatabase.getIncidentsList().get(i).getIncidentDateOfMonth()==g||
							IncidentDatabase.getIncidentsList().get(i).getIncidentDateOfMonth()==h||
							IncidentDatabase.getIncidentsList().get(i).getIncidentDateOfMonth()==j||
							IncidentDatabase.getIncidentsList().get(i).getIncidentDateOfMonth()==k||
							IncidentDatabase.getIncidentsList().get(i).getIncidentDateOfMonth()==l||
							IncidentDatabase.getIncidentsList().get(i).getIncidentDateOfMonth()==m||
							IncidentDatabase.getIncidentsList().get(i).getIncidentDateOfMonth()==n||
							IncidentDatabase.getIncidentsList().get(i).getIncidentDateOfMonth()==o) {
					
						//and has at least 1 keyword matching
						for(int x=0;x<IncidentDatabase.getIncidentsList().get(i).getIncidentKeywords().length;x++) {
							databaseKeyword=IncidentDatabase.getIncidentsList().get(i).getIncidentKeywords()[x];
							for(int y=0;y<anIncident.getIncidentKeywords().length;y++) {
								incidentKeyword=anIncident.getIncidentKeywords()[y];
								
								//mark it as a possible duplicate
								if(incidentKeyword.equalsIgnoreCase(databaseKeyword)) {
									possibleDuplicate=true;
									if(possibleDuplicate==true) {
										duplicatedIndex=i;
										
										break outerloop;
									}
								}
							}
						}
						
						
						/**
						//has 3 out of 5 keyword matches
						first=someIncidentKeywords[0];
						
						if(someIncidentKeywords.length>1) {
							second = someIncidentKeywords[1];
						}
						
						if(someIncidentKeywords.length>2) {
							
							third=someIncidentKeywords[2];
						}
						
						if(someIncidentKeywords.length>3) {
							fourth=someIncidentKeywords[3];
						}
						
						if(someIncidentKeywords.length>4) {
							fifth=someIncidentKeywords[4];
						}
						
						for(int j=0;j<IncidentDatabase.getIncidentsList().get(i).getIncidentKeywords().length;j++) {
							if(IncidentDatabase.getIncidentsList().get(i).getIncidentKeywords()[j].equalsIgnoreCase("first")) {
								matchOne=1;
							}
							
							if(IncidentDatabase.getIncidentsList().get(i).getIncidentKeywords()[j].equalsIgnoreCase("second")) {
								matchTwo=1;
							}
							
							if(IncidentDatabase.getIncidentsList().get(i).getIncidentKeywords()[j].equalsIgnoreCase("third")) {
								matchThree=1;
							}
							
							if(IncidentDatabase.getIncidentsList().get(i).getIncidentKeywords()[j].equalsIgnoreCase("fourth")) {
								matchFour=1;
							}
							
							if(IncidentDatabase.getIncidentsList().get(i).getIncidentKeywords()[j].equalsIgnoreCase("fifth")) {
								matchFive=1;
							}
						}
					
						totalMatch=matchOne+matchTwo+matchThree+matchFour+matchFive;
						
						//then it's a duplicate
						if(totalMatch>2) {
							possibleDuplicate=true;
							if(possibleDuplicate==true) {
								duplicatedIndex=i;
								break;
							}
						}
						
						
					//possibleDuplicate=true;*/
					}
				}
				
			//possibleDuplicate=true;
			}
		}
		
		//-----------------------------------------------------------------------------------------------------------------------
		
		if(possibleDuplicate==true) {
			req.setAttribute("theOriginalIndex", duplicatedIndex);
			//out.println("duplicated");
			IncidentDatabase.getDuplicatesList().add(anIncident);
			req.getRequestDispatcher("DetectDuplicates.jsp").forward(req,res);
			
		} else {
			IncidentDatabase.getIncidentsList().add(anIncident);
			req.getRequestDispatcher("ListOfIncidents.jsp").forward(req,res);
			//out.println("original");
		}
		
		
		
		
		out.println("finish");
		
		
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
		
		
		
	}
	
	
	
}
