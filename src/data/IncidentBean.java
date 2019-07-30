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
	private String incidentLog;
	private String[] staffAssigned;
	
	
	//----------------------------------------
	//Fixed phase
	
	//---------------------------------------
	//Verified phase

	
	
	
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
		
		setTimeStamp();
		setIncidentDateOfMonth();
		setIncidentMonth();
		setIncidentYear();
		staffAssigned= new String[0];
		
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
	
	//sets the incident ID
	public void setIncidentID() {
		//at the start of history, incidentCounter is 0
		//incidentCounter is static variable
		
		//For first incident, incident id is 1. For second incident, incident id is 2. etc etc.
		incidentID=IncidentDAO.getIncidentCounter()+1;
		
		//Increment the counter by 1
		IncidentDAO.setIncidentCounter(IncidentDAO.getIncidentCounter()+1);
		
		//creates a PostIncidentBean object, sets it's incidentID to be same as this IncidentBean's incidentID
		//and stores that PostIncidentBean in PostIncidentDAO
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
	
	//get the date and time as a string from the timestamp
	public String getDateTimeFromTimeStamp() {
		
		//initializes a LocalDateTime object
		LocalDateTime dateTime=	ts.toLocalDateTime();
		
		//set the format want the LocalDateTime object to be
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		
		//gets the date and time in that format
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
	
	//gets the incident keywords in a string
	public String getIncidentKeywordsInString() {
		String keywords="";
	
			int i=0;
			
			//as long as the keyword is not the last keyword in the incidentKeywords array
			while(i!=(incidentKeywords.length-1)) {
				
				//append incidentKeywords to the String keywords and add comma
				keywords=keywords+incidentKeywords[i]+", ";
				i++;
			}
			//if it is the last keyword
			//append without adding commas
			keywords=keywords+incidentKeywords[i];
			
			//return String of keywords
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
	
		//get the LocalDateTime through Timestamp
		LocalDateTime datetime =ts.toLocalDateTime();
		
		//get the LocalDate through LocalDateTime
		LocalDate date = datetime.toLocalDate();
		
		//set the incident Month
		incidentMonth=date.getMonth().toString();
		
	}
	
	
	public int getIncidentYear() {
		return incidentYear;
	}
	
	//as above in setIncidentMonth
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
	
	//returns a String of the assigned staff IDs
	public String getAssignedStaffIDInString() {
		String total="";
		String append="";
		for(int i=0;i<staffAssigned.length;i++) {
			append=staffAssigned[i];
			total=total+append+". ";
		}
		
		return total;
	}
	public String[] getAssignedStaffID() {
		
		return staffAssigned;
	}
	
	public void setAssignedStaffID(String[] staff) {
		staffAssigned=staff;
	}
	
	//gets the assigned staff name and position
	//like this
	//Bob Smith (Financial Analyst) 
	//Alice Diaz (Internal Auditor)
	public String getAssignedStaffNameAndPosition() {
		
		//to be printed out
		String total="";
		
		//to store name and position
		String staff;
		
		//to receive name and position from database
		String name;
		String position;
		
		//if assigned staff id array has nothing, then no staff assigned
		if(staffAssigned.length==0) {
			total="No staff assigned";
		
			//else
		} else {
			
			//traverse through staffAssigned array
			for(int i=0;i<staffAssigned.length;i++) {
				
				//get the assigned staff id in the array element
				String userId = staffAssigned[i];
				
				//get the UserBean from that staff id
				UserBean user = UserDAO.getUserByStaffID(userId);
				
				//get the User's name and position from that UserBean
				name=user.getName();
				position = user.getPosition().toString();
				
				//if it is the first cycle
				if(i==0) {
				//no need to go next line then append
				staff = name+" ("+position+")";
				//else if it's not first cycle
				} else {
					//go next line then append
					staff = "<br>"+ name+" ("+position+")";
				}
				
				//keep combining the assigned staff  
				total = total+staff;
				
			}
		}
		
		//print out assigned staff
		return total;
		
	}
	
	
	
	//returns a PostIncidentBean through using this IncidentBean's id
	//because an IncidentBean's incidentID is same as it's corresponding PostIncidentBean's incidentID
	public PostIncidentBean getPostIncident() {
		PostIncidentBean postIncident = new PostIncidentBean();
		postIncident=PostIncidentDAO.getPostIncidentByIncidentID(incidentID);
		return postIncident;
	}
	
	//adds the PostIncidentBean for this incident. Method called upon creation of this incident.
	public void setPostIncident() {
		
		//create new PostIncidentBean
		PostIncidentBean postIncident = new PostIncidentBean();
		
		//set the new PostIncidentBean's incidentID to be this IncidentBean's incidentID
		postIncident.setIncidentID(incidentID);
		
		//add this new PostIncidentBean to the database
		PostIncidentDAO.addPostIncident(postIncident);
		
	}
	
	
	
	


	
	
	public Priority getPriorityRating() {
		return priorityRating;
	}
	public void setPriorityRating(Priority p) {
		this.priorityRating = p;
	}
	

}
