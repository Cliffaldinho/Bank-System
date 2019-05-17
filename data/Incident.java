package data;
import java.util.*;
import java.io.Serializable;

//is now a JavaBean
public class Incident implements Serializable {



	private ArrayList<User> usersReportedIncident;
	private String incidentTitle;
	private Category incidentCategory;
	private int incidentDateOfMonth;
	private String incidentMonth;
	private int incidentYear;
	private String descriptionOfIncident;
	private Priority priorityRating;
	private String incidentKeyword;
	private String possibleCausesOfIncident;
	private String possibleSolutionsOfIncident;


	public Incident() {
		
	}
	
	enum Category {
	Regulatory_Law,
	Cyber_Security,
	Human_Issues,
	Bank_Equipment,
	Bank_Algorithms;
	}
	
	enum Priority {
		Low, Medium, High;
	}
	
	public void setIncidentTitle(String title) {
		this.incidentTitle=title;
	}
	
	public String getIncidentTitle() {
		return incidentTitle;
	}
	
	public ArrayList<User> getUsersReportedIncident() {
		return usersReportedIncident;
	}

	public void setUsersReportedIncident(ArrayList<User> users) {
		this.usersReportedIncident = users;
	}
	
	public Priority getPriorityRating() {
		return priorityRating;
	}
	public void setPriorityRating(Priority p) {
		this.priorityRating = p;
	}
	
	public String getIncidentKeyword() {
		return incidentKeyword;
	}
	public void setIncidentKeyword(String keyword) {
		this.incidentKeyword = keyword;
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
	public void setIncidentMonth(String month) {
		this.incidentMonth = month;
	}
	public int getIncidentYear() {
		return incidentYear;
	}
	public void setIncidentYear(int year) {
		this.incidentYear = year;
	}
	public int getIncidentDateOfMonth() {
		return incidentDateOfMonth;
	}
	public void setIncidentDateOfMonth(int dateOfMonth) {
		this.incidentDateOfMonth = dateOfMonth;
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
