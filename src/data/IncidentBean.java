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
	
	
	//JOIN of IncidentBean and PostIncidentBean
	//incidentID same
	//act as primary key
	//split into two tables coz was tough to read in one big table
	
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
	private Status incidentStatus;
	
	

	//---------------------------------------
	//Assigned phase
	private String idOfStaffAssigned;
	private String incidentLog;
	
	
	
	//---------------------------------------
	//Verified phase
	private PostIncidentBean postIncident;
	
	

	
	
	
	//-------------------------------------------
	//enums
	
	public enum Status {
		New {
			public String toString() {
				return "New Incident";
			}
		},
		
		Assigned {
			public String toString() {
				return "Staff Assigned";
			}
		},
		
		Fixed {
			public String toString() {
				return "Incident fixed";
			}
		},
		
		Verified {
			public String toString() {
				return "Solution verified";
			}
		},
		
		Analysis {
			public String toString() {
				return "Undergoing analysis";
			}
		},
		
		Strategy {
			public String toString() {
				return "Strategy implementation";
			}
		},
		
		Archived {
			public String toString() {
				return "Archived";
			}
		}
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
	


	public IncidentBean() {
		
		
		//New/Assigned/Fixed/Verified phase
		setTimeStamp();
		setIncidentDateOfMonth();
		setIncidentMonth();
		setIncidentYear();
		
		idOfStaffAssigned="None";
		
		
		
	}
	
	
	
	
	//Methods for New phase
	//-----------------------------------------------------------------------------------------------------------------------------------------
	
	
	public Status getIncidentStatus() {
		return incidentStatus;
	}

	public void setIncidentStatus(Status status) {
		this.incidentStatus = status;
	}
	
	
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
		setPostIncident();
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
	
	public PostIncidentBean getPostIncident() {
		PostIncidentBean postIncident = new PostIncidentBean();
		postIncident=PostIncidentDAO.getPostIncidentByIncidentID(incidentID);
		return postIncident;
	}
	
	public void setPostIncident() {
		PostIncidentBean postIncident = new PostIncidentBean();
		postIncident.setIncidentID(incidentID);
		PostIncidentDAO.addPostIncident(postIncident);
		
	}
	
	
	
	


	
	
	public Priority getPriorityRating() {
		return priorityRating;
	}
	public void setPriorityRating(Priority p) {
		this.priorityRating = p;
	}
	

}
