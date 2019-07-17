package data;

public class Simulation {

	private String date;
	private String rootCauseTargeted;
	private String actionsTaken;
	private String resultsFound;
	
	public Simulation() {
		
	}
	
	public Simulation(String thedate,String rootCause,String actions,String results) {
		
		this.date=thedate;
		this.rootCauseTargeted=rootCause;
		this.actionsTaken=actions;
		this.resultsFound=results;
		
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String theDate) {
		this.date = theDate;
	}

	public String getRootCauseTargeted() {
		return rootCauseTargeted;
	}

	public void setRootCauseTargeted(String rootCause) {
		this.rootCauseTargeted = rootCause;
	}

	public String getActionsTaken() {
		return actionsTaken;
	}

	public void setActionsTaken(String actions) {
		this.actionsTaken = actions;
	}

	public String getResultsFound() {
		return resultsFound;
	}

	public void setResultsFound(String results) {
		this.resultsFound = results;
	}
}
