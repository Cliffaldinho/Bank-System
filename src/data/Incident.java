package data;
import java.util.*;
import java.io.Serializable;
import java.text.*;
//import java.time.format.DateTimeFormatter;
import java.sql.Timestamp;
import java.time.*;
import java.time.temporal.ChronoUnit;

//is now a JavaBean
public class Incident implements Serializable {



	private User userReportedIncident;
	private String incidentTitle;
	private Category incidentCategory;
	private int incidentDateOfMonth;
	private String incidentMonth;
	private int incidentYear;
	private String descriptionOfIncident;
	private Priority priorityRating;
	private String[] incidentKeywords;
	private String possibleCausesOfIncident;
	private String possibleSolutionsOfIncident;
	
	private int originalIndexForDuplicateChecking;
	private boolean duplicateCheckInProcess;
	
	private boolean hasAnalysis;
	private int indexInAnalysisDatabase;
	
	private String idOfStaffAssigned;
	
	private Timestamp ts;








	public Incident() {
		duplicateCheckInProcess=false;
		hasAnalysis=false;
		setTimeStamp();
		setIncidentDateOfMonth();
		setIncidentMonth();
		setIncidentYear();
	}


	public int detectDuplicate() {
		//boolean possibleDuplicate=false;
		int duplicateIndex=-1;
		
		//traverse through Incident Database
		for(int i=0;i<IncidentDatabase.getIncidentsList().size();i++) {
			
			//get Incident Category of database incident
			Category databaseIncidentCategory=IncidentDatabase.getIncidentsList().get(i).getIncidentCategory();
			
			//get incident date of database incident
			Timestamp databaseIncidentTimestamp=IncidentDatabase.getIncidentsList().get(i).getTimeStamp();
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

			String[] databaseIncidentKeywords=IncidentDatabase.getIncidentsList().get(i).getIncidentKeywords();
			
			//if incident category matches database incident, and days between both incidents are within a week
			if(incidentCategory==databaseIncidentCategory&&isInAWeekRange==true) {
				
				//check if there is at least one keyword matching
				outerloop:
				for(int a=0;a<incidentKeywords.length;a++) {
					
					for(int b=0;b<databaseIncidentKeywords.length;b++) {
						
						//if there is at least one keyword matching
						if(incidentKeywords[a].equalsIgnoreCase(databaseIncidentKeywords[b])) {
							
							//take note of index of orginal incident in database
							duplicateIndex=i;
							
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
		return duplicateIndex;
		
		
	}
	
	public void setTimeStamp() {
		
		ts = new Timestamp(System.currentTimeMillis());
		
	}
	
	public Timestamp getTimeStamp() {
		return ts;
	}
	public Date getDateFromTimeStamp() {
		Date date = new Date(ts.getTime());
		return date;
	}
	
	
	public String getIdOfStaffAssigned() {
		return idOfStaffAssigned;
	}

	public void setIdOfStaffAssigned(String id) {
		this.idOfStaffAssigned = id;
	}
	
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
	
	public boolean getHasAnalysis() {
		return hasAnalysis;
	}

	public void setHasAnalysis(boolean exist) {
		this.hasAnalysis = exist;
	}

	public int getIndexInAnalysisDatabase() {
		return indexInAnalysisDatabase;
	}

	public void setIndexInAnalysisDatabase(int index) {
		this.indexInAnalysisDatabase = index;
	}
	
	
	public boolean getDuplicateCheckInProcess() {
		return duplicateCheckInProcess;
	}

	public void setDuplicateCheckInProcess(boolean duplicateCheck) {
		this.duplicateCheckInProcess = duplicateCheck;
	}

	public int getOriginalIndexForDuplicateChecking() {
		return originalIndexForDuplicateChecking;
	}

	public void setOriginalIndexForDuplicateChecking(int original) {
		this.originalIndexForDuplicateChecking = original;
	}
	
	public void setIncidentTitle(String title) {
		this.incidentTitle=title;
	}
	
	public String getIncidentTitle() {
		return incidentTitle;
	}
	
	public User getUserReportedIncident() {
		return userReportedIncident;
	}

	public void setUserReportedIncident(User u) {
		this.userReportedIncident=u;
	}
	
	public Priority getPriorityRating() {
		return priorityRating;
	}
	public void setPriorityRating(Priority p) {
		this.priorityRating = p;
	}
	
	public String[] getIncidentKeywords() {
		return incidentKeywords;
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
