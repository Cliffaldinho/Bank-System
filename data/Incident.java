package data;
import java.util.*;
import java.io.Serializable;

//is now a JavaBean
public class Incident implements Serializable {



	private ArrayList<User> usersReportedIncident;
	private String incidentTitle;
	private String incidentCategory;
	private int incidentDateOfMonth;
	private String incidentMonth;
	private int incidentYear;
	private String descriptionOfIncident;
	private int priorityRating;
	private String incidentKeyword;
	private String possibleCausesOfIncident;
	private String possibleSolutionsOfIncident;


	public Incident() {
		
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
	
	public int getPriorityRating() {
		return priorityRating;
	}
	public void setPriorityRating(int priority) {
		this.priorityRating = priority;
	}
	public String getIncidentKeyword() {
		return incidentKeyword;
	}
	public void setIncidentKeyword(String keyword) {
		this.incidentKeyword = keyword;
	}
	public String getIncidentCategory() {
		return incidentCategory;
	}
	public void setIncidentCategory(String category) {
		this.incidentCategory = category;
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
