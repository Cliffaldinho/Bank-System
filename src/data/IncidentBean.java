package data;
import java.util.*;
import java.io.Serializable;
import java.text.*;
//import java.time.format.DateTimeFormatter;
import java.sql.Timestamp;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;

//is now a JavaBean
public class IncidentBean implements Serializable {

	/**
	 database normalization reference:
	 reduce data redundancy and improve data integrity
	 
	 database dependency:
	 when information in the same table uniquely determines other information stored in same table.
	 is a functional dependency between those attributes.
	 i.e. name is dependent upon Social Security Number
	 a relationship where knowing the value of one attribute is enough to tell you the value of another attribute in the same table
	 
	 */
	
	
	/**
	 Removed Analysis object.
	 
	 Analysis class contained 
	 analysis ID, which is same as incidentID,
	 rootCause in IncidentBean, which is same as rootCause in Analysis,
	 lessonsLearnt in IncidentBean, whch is same as lessonsLeart in Analysis
	 
	 Hence choose to store a particular colomn of data in one location, 
	 so that fewer places to update and less risk of having different data in different places
	 */
	
	
	/**
	 Removed RiskManagement object
	 Risk class contained attributes
	 
	 String riskStrategyWriter. which is equal to branch manager.
	 String delegatedStaff. which is equal to everybody (for ratings), Simulation class (for possible solutions/test strategy)
	 String riskTitle: which is equal to IncidentBean title
	 Category riskCategory: which is equal to IncidentBean category
	 (of which all overlap with IncidentBean or are already defined)
	 
	 And:
	 String risk: merged into IncidentBean as String riskForeseen
	 String managementStrategy: merged into IncidentBean as String strategyImplemented
	 
	 Chose to join it all into this class, as they are linked to attributes in this class.
	 i.e. 
	 riskCategory linked to IncidentBean category
	 riskTitle linked to IncidentBean title 
	 String riskForeseen is deduced off IncidentBean possibleCausesOfIncident
	 String strategyImplemented is deduced off IncidentBean possibleSolutionsOfIncident, and Simulations done
	 
	 thus linked it here to prevent adding/updating in multiple database tables for each entry
	 as they all go in a linear timeline of New->...->Analysis->Strategy->Archive
	 
	 */
	
	//------------------------------------
	//New phase
	private int incidentID;
	private UserBean userReportedIncident;//can be converted to String
	private String incidentTitle;
	private Category incidentCategory;
	private int incidentDateOfMonth;
	private String incidentMonth;
	private int incidentYear;
	private String descriptionOfIncident;
	private Priority priorityRating;
	private String[] incidentKeywords;//can be converted to String?
	private Timestamp ts;
	
	//----------------------------------------
	//Analysis phase
	private String possibleCausesOfIncident;
	private String possibleSolutionsOfIncident;
	
	private String[] simulations;
	
	//-----------------------------------------
	//Strategy phase
	private String riskForeseen;//Reocurrence as default	//linked to possibleCausesOfIncident
	private double riskEvaluation;//consequence x probability	
	
	private String strategyImplemented;	//linked to possibleSolutionsOfIncident
	
	//rating of strategy implemented
	//ratings are 1-5
	private int ratingOverall;
	private int amountOfRatingsReceived;

	private int ratingEffectiveness;
	private int ratingImprovementFromSituationBefore;
	private int ratingPractical;
	private int ratingRelevanceToIncident;
	private int ratingSatisfactionOfStrategy;
	//this can then be used in Archive for predictive analytics in future
	//hence used numbers instead of enum, as will need those numbers in large amounts of data for analytics
	
	private String idOfStaffAssigned;
	
	
	
	//-------------------------------------------
	//enums
	public enum Category {
		Regulatory_Law {
			public String toString() {
				return "Regulatory Law";
			}
		},
		Cyber_Security {
			public String toString() {
				return "Cyber Security";
			}
		},
		Human_Issues {
			public String toString() {
				return "Human Issues";
			}
		},
		Bank_Equipment {
			public String toString() {
				return "Bank Equipment";
			}
		},
		Bank_Algorithms {
			public String toString() {
				return "Bank Algorithms";
			}
		},
		Other {
			public String toString() {
				return "Other";
			}
		};
	}
	
	public enum Priority {
		Low, Medium, High;
	}
	


	public IncidentBean() {
		
		
		//New/Assigned/Fixed/Verified phase
		setTimeStamp();
		setIncidentDateOfMonth();
		setIncidentMonth();
		setIncidentYear();
		
		idOfStaffAssigned="None";
		
		//Analysis phase 
		possibleCausesOfIncident="";
		possibleSolutionsOfIncident="";
		
		simulations = new String[0];
		
		//Strategy phase
		riskForeseen="";
		riskEvaluation=0;
		strategyImplemented="";
		
		amountOfRatingsReceived=0;
		ratingOverall=0;
		ratingEffectiveness=0;
		ratingImprovementFromSituationBefore=0;
		ratingPractical=0;
		ratingRelevanceToIncident=0;
		ratingSatisfactionOfStrategy=0;
		
		//Archive phase
		//is once rating feedback of strategy have been received
		//finite amount of people?
		
	}
	
	//Methods for Strategy phase
	//----------------------------------------------------------------------------------------------------------------------------------
	public int getAmountOfRatingsReceived() {
		return amountOfRatingsReceived;
	}

	public void setAmountOfRatingsReceived(int amount) {
		this.amountOfRatingsReceived = amount;
	}
	
	public String getRiskForeseen() {
		return riskForeseen;
	}

	public void setRiskForeseen(String foreseen) {
		this.riskForeseen = foreseen;
	}

	public double getRiskEvaluation() {
		return riskEvaluation;
	}

	public void setRiskEvaluation(double probability, double consequence) {
		double evaluation=probability*consequence;
		double evaluationRounded=(Math.round(evaluation*100))/100;
		riskEvaluation=evaluationRounded;

	}

	public String getStrategyImplemented() {
		return strategyImplemented;
	}

	public void setStrategyImplemented(String implemented) {
		this.strategyImplemented = implemented;
	}
	
	
	public int getRatingOverall() {
		return ratingOverall;
	}

	public void setRatingOverall(int overall) {
		this.ratingOverall = overall;
	}

	public int getRatingEffectiveness() {
		return ratingEffectiveness;
	}

	public void setRatingEffectiveness(int effectiveness) {
		this.ratingEffectiveness = effectiveness;
	}

	public int getRatingImprovementFromSituationBefore() {
		return ratingImprovementFromSituationBefore;
	}

	public void setRatingImprovementFromSituationBefore(int improvement) {
		this.ratingImprovementFromSituationBefore = improvement;
	}

	public int getRatingPractical() {
		return ratingPractical;
	}

	public void setRatingPractical(int practical) {
		this.ratingPractical = practical;
	}

	public int getRatingRelevanceToIncident() {
		return ratingRelevanceToIncident;
	}

	public void setRatingRelevanceToIncident(int relevance) {
		this.ratingRelevanceToIncident = relevance;
	}

	public int getRatingSatisfactionOfStrategy() {
		return ratingSatisfactionOfStrategy;
	}

	public void setRatingSatisfactionOfStrategy(int satisfaction) {
		this.ratingSatisfactionOfStrategy = satisfaction;
	}
	
	//----------------------------------------------------------------------------------------------------------------------------------------
	//End Methods for Strategy phase
	
	//Methods for Analysis phase
	//-----------------------------------------------------------------------------------------------------------------------------------------
	public void addSimulation(Simulation simulation) {
		String dateTime="Date: "+simulation.getDateTimeFromTimeStamp();
		String actionsTaken="Actions taken: "+simulation.getActionsTaken();
		String resultsFound="Results found: "+simulation.getResultsFound();
		String rootCauseTargeted="Root cause targeted: "+simulation.getRootCauseTargeted();
		String staff="Staff who completed this simulation: "+simulation.getStaff();
		String totalSimulation=dateTime+"<br>"+staff+"<br>"+rootCauseTargeted+"<br>"+actionsTaken+"<br>"+resultsFound;
		
		List<String> storeSimulations = new LinkedList<String>(Arrays.asList(simulations));

		
		storeSimulations.add(totalSimulation);
		simulations= storeSimulations.toArray(new String[storeSimulations.size()]);
		
	}
	
	public String[] getSimulations() {
		
		return simulations;

		
	}
	//----------------------------------------------------------------------------------------------------------------------------------------
	//End Methods for Analysis phase
	
	//Methods for New phase
	//-----------------------------------------------------------------------------------------------------------------------------------------
	public int detectDuplicate() {
		//boolean possibleDuplicate=false;
		int duplicateID=-1;
		
		//traverse through Incident Database
		for(int i=0;i<IncidentDAO.getIncidentsList().size();i++) {
			
			//get Incident Category of database incident
			Category databaseIncidentCategory=IncidentDAO.getIncidentsList().get(i).getIncidentCategory();
			
			//get incident date of database incident
			Timestamp databaseIncidentTimestamp=IncidentDAO.getIncidentsList().get(i).getTimeStamp();
			LocalDateTime databaseIncidentDateTime=databaseIncidentTimestamp.toLocalDateTime();
			LocalDate databaseIncidentDate=databaseIncidentDateTime.toLocalDate();
			
			//get incident date of current incident
			LocalDateTime incidentDateTime=ts.toLocalDateTime();
			LocalDate incidentDate=incidentDateTime.toLocalDate();
			
			//compare amount of days between current incident and database incident, to check if it's within a week.
			long days,absoluteDays;
			days=ChronoUnit.DAYS.between(incidentDate, databaseIncidentDate);
			absoluteDays=Math.abs(days);
			boolean isInAWeekRange;
			if(absoluteDays<7) {
				isInAWeekRange=true;
			} else {
				isInAWeekRange=false;
			}

			String[] databaseIncidentKeywords=IncidentDAO.getIncidentsList().get(i).getIncidentKeywords();
			
			//if incident category matches database incident, and days between both incidents are within a week
			if(incidentCategory==databaseIncidentCategory&&isInAWeekRange==true) {
				
				//check if there is at least one keyword matching
				outerloop:
				for(int a=0;a<incidentKeywords.length;a++) {
					
					for(int b=0;b<databaseIncidentKeywords.length;b++) {
						
						//if there is at least one keyword matching
						if(incidentKeywords[a].equalsIgnoreCase(databaseIncidentKeywords[b])) {
							
							//take note of index of orginal incident in database
							duplicateID=IncidentDAO.getIncidentsList().get(i).getIncidentID();
							
							//break whole loop
							break outerloop;
						}
						
					}
					
				}
				
			
			} 
			
		}
		
		//returns index of duplicate;
		//no duplicate if -1
		//has duplicate if anything else
		return duplicateID;
		
		
	}
	
	public void setIncidentID() {
		incidentID=IncidentDAO.getIncidentCounter()+1;
		IncidentDAO.setIncidentCounter(IncidentDAO.getIncidentCounter()+1);
	}
	
	public int getIncidentID() {
		return incidentID;
	}
	
	
	public void setTimeStamp() {
		
		ts = new Timestamp(System.currentTimeMillis());
		
	}
	
	public Timestamp getTimeStamp() {
		return ts;
	}
	
	public String getDateTimeFromTimeStamp() {
		LocalDateTime dateTime=	ts.toLocalDateTime();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		String printDateTime=dateTime.format(formatter);
		
		return printDateTime;
	}
	
	public void setIncidentTitle(String title) {
		this.incidentTitle=title;
	}
	
	public String getIncidentTitle() {
		return incidentTitle;
	}
	
	public UserBean getUserReportedIncident() {
		return userReportedIncident;
	}

	public void setUserReportedIncident(UserBean u) {
		this.userReportedIncident=u;
	}
	
	public String[] getIncidentKeywords() {
		return incidentKeywords;
	}
	
	public String getIncidentKeywordsInString() {
		String keywords="";
	
			int i=0;
			while(i!=(incidentKeywords.length-1)) {
				keywords=keywords+incidentKeywords[i]+", ";
				i++;
			}
			keywords=keywords+incidentKeywords[i];
			
		return keywords;
	}
	
	public void setIncidentKeywords(String[] keywords) {
		this.incidentKeywords = keywords;
	}
	
	public Category getIncidentCategory() {
		return incidentCategory;
	}
	public void setIncidentCategory(Category aCategory) {
		this.incidentCategory = aCategory;
	}
	
	public String getIncidentMonth() {
		return incidentMonth;
	}
	public void setIncidentMonth() {
		
		LocalDateTime datetime =ts.toLocalDateTime();
		LocalDate date = datetime.toLocalDate();
		incidentMonth=date.getMonth().toString();
		
	}
	
	
	public int getIncidentYear() {
		return incidentYear;
	}
	public void setIncidentYear() {
		
		LocalDateTime datetime =ts.toLocalDateTime();
		LocalDate date = datetime.toLocalDate();
		incidentYear=date.getYear();
		
	}
	public int getIncidentDateOfMonth() {
		return incidentDateOfMonth;
	}
	
	public void setIncidentDateOfMonth() {
		
		LocalDateTime datetime =ts.toLocalDateTime();
		LocalDate date = datetime.toLocalDate();
		incidentDateOfMonth=date.getDayOfMonth();
	}
	public String getDescriptionOfIncident() {
		return descriptionOfIncident;
	}
	public void setDescriptionOfIncident(String description) {
		this.descriptionOfIncident = description;
	}
	
	//----------------------------------------------------------------------------------------------------------------------------------------
	//End Methods for New phase
	
	
	//get id of staff assigned
	public String getAssignedStaffID() {
		return idOfStaffAssigned;
	}

	public void setAssignedStaffID(String id) {
		this.idOfStaffAssigned = id;
	}
	
	//get name of staff assigned
	public String getAssignedStaffName() {
		String staffName;
		
		if(idOfStaffAssigned.equalsIgnoreCase("None")) {
			staffName="No staff assigned.";
		} else {
			staffName=UserDAO.getUserByStaffID(idOfStaffAssigned).getName();
		}
		
		return staffName;
	}
	
	
	
	


	
	
	public Priority getPriorityRating() {
		return priorityRating;
	}
	public void setPriorityRating(Priority p) {
		this.priorityRating = p;
	}
	

	public String getPossibleCausesOfIncident() {
		return possibleCausesOfIncident;
	}

	
	public void setPossibleCausesOfIncident(String possibleCauses) {
		this.possibleCausesOfIncident = possibleCauses;
	}
	public String getPossibleSolutionsOfIncident() {
		return possibleSolutionsOfIncident;
	}
	public void setPossibleSolutionsOfIncident(String possibleSolutions) {
		this.possibleSolutionsOfIncident = possibleSolutions;
	}

}
