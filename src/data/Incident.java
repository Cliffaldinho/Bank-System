package data;
import java.util.*;
import java.io.Serializable;
import java.text.*;
import java.time.format.DateTimeFormatter;
import java.sql.Timestamp;

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


	public void setTimeStamp() {
		Date date = new Date();
		ts = new Timestamp(date.getTime()); 
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
		
		incidentMonth=new SimpleDateFormat("MMM").format(ts.getTime());
		
	}
	
	
	public int getIncidentYear() {
		return incidentYear;
	}
	public void setIncidentYear() {
		String tempIncidentYear;
		tempIncidentYear = new SimpleDateFormat("yyyy").format(ts.getTime());
		incidentYear=Integer.parseInt(tempIncidentYear);
	}
	public int getIncidentDateOfMonth() {
		return incidentDateOfMonth;
	}
	public void setIncidentDateOfMonth() {
		String tempIncidentDateOfMonth;
		tempIncidentDateOfMonth = new SimpleDateFormat("dd").format(ts.getTime());
		incidentDateOfMonth=Integer.parseInt(tempIncidentDateOfMonth);
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
