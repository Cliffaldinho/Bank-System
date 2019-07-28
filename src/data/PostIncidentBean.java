package data;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PostIncidentBean {
	
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
	
	private int incidentID;//same as incidentID
	
	public int getIncidentID() {
		return incidentID;
	}

	public void setIncidentID(int id) {
		this.incidentID = id;
	}

	//Analysis phase
	private String possibleCausesOfIncident;
	private String possibleSolutionsOfIncident;
	
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
	
	
	
	private String[] simulations;
	
	public PostIncidentBean() {
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
	
	//add a simulation event to the existing array of simulations
	public void addSimulation(Simulation simulation) {
		
		//combine all aspects into a string
		String dateTime="Date: "+simulation.getDateTimeFromTimeStamp();
		String actionsTaken="Actions taken: "+simulation.getActionsTaken();
		String resultsFound="Results found: "+simulation.getResultsFound();
		String rootCauseTargeted="Root cause targeted: "+simulation.getRootCauseTargeted();
		String staff="Staff who completed this simulation: "+simulation.getStaff();
		String totalSimulation=dateTime+"<br>"+staff+"<br>"+rootCauseTargeted+"<br>"+actionsTaken+"<br>"+resultsFound;
		
		//convert existing simulation array in database, to a list
		List<String> storeSimulations = new LinkedList<String>(Arrays.asList(simulations));
		
		//use list method to add the simulation 
		storeSimulations.add(totalSimulation);

		//convert the list back to an array
		simulations= storeSimulations.toArray(new String[storeSimulations.size()]);
		
	}
	
	public String[] getSimulations() {
		
		return simulations;

		
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

}
