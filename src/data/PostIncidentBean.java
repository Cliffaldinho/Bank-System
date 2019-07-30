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
	private String riskForeseen;//linked to possibleCausesOfIncident
	private int riskEvaluation;//consequence x probability	
	
	private String strategyImplemented;	//linked to possibleSolutionsOfIncident
	
	private boolean isStrategyImplementedAlready;//used to check if branch manager has already entered a strategy
	



	
	//this is to count the number of ratings received, so that for each of the ratings, can get the average each time someone adds a rating to it
	private int amountOfRatingsReceived;

	//rating of strategy implemented	
	//this allows all users to rate the strategy that branch manager implemented from that incident once
	//ratings are  for overall, effectiveness, improvement from situation before, practical, relevance to incident, and satisfaction	
	//ratings are 1-5
	private double ratingOverall;
	private double ratingEffectiveness;
	private double ratingImprovementFromSituationBefore;
	private double ratingPractical;
	private double ratingRelevanceToIncident;
	private double ratingSatisfactionOfStrategy;
	//this can then be used in Archive for predictive analytics in future
	//hence used numbers instead of enum, as will need those numbers in large amounts of data for analytics
	
	
	//these are simulations done in the get lessons learnt phase
	//Each simulation String contains 
	//user entered root cause targeted, actions done, results found, 
	//and computer generated user name and position
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
		
		
		isStrategyImplementedAlready=false;
		
		//Archive phase
		//is once rating feedback of strategy have been received
		//finite amount of people?
	}
	
	
	 //Methods for Analysis phase
	//----------------------------------------------------------------------------------------------------------------------------------
	
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
	
	//----------------------------------------------------------------------------------------------------------------------------------------
	//End Methods for Analysis phase
	
	
	 //Methods for Strategy phase
	//----------------------------------------------------------------------------------------------------------------------------------
	
	//Risk entered, Risk evaluation, and Strategy implementation, can only be entered once, by the branch manager. Then it's final
	//This method is to check that.
	public boolean isStrategyImplementedAlready() {
		return isStrategyImplementedAlready;
	}

	public void setStrategyImplementedAlready() {
		this.isStrategyImplementedAlready = true;
	}
	
	
	public int getAmountOfRatingsReceived() {
		return amountOfRatingsReceived;
	}

	//counter to count the amount of ratings received. So as to get the average
	public void setAmountOfRatingsReceived() {
		amountOfRatingsReceived=amountOfRatingsReceived+1;
	}
	
	public String getRiskForeseen() {
	
		return riskForeseen;
	}

	public void setRiskForeseen(String foreseen) {
		this.riskForeseen = foreseen;
	}

	public int getRiskEvaluation() {
		return riskEvaluation;
	}
	
	/*
	 if risk evaluated is
	 1-2: very low
	 3-4: low
	 5-9: moderate
	 10-15: high
	 16-25: very high
	 
	 According to the matrix drawn out, this would result in options
	 very low: 3 options
	 low: 5 options
	 moderate: 7 options
	 high: 6 options
	 very high: 4 options
	 
	 Which fits the normal distribution. Hence gone with it
	 */
	public String getRiskEvaluationAsString() {
		String result="";
		
		if(riskEvaluation>=1&&riskEvaluation<=2) {
			result="Very Low";
			
		} else if (riskEvaluation>=3&&riskEvaluation<=4) {
			result="Low";
			
		} else if (riskEvaluation>=5&&riskEvaluation<=9) {
			result="Moderate";
			
		} else if (riskEvaluation>=10&&riskEvaluation<=15) {
			result="High";
			
		} else if(riskEvaluation>=16&&riskEvaluation<=25){
			result="Very High";
		} 
		
		return result;
	}
	
	
	public void setRiskEvaluation(int probability, int consequence) {
		
		riskEvaluation=probability*consequence;

	}

	public String getStrategyImplemented() {

		return strategyImplemented;
	}

	public void setStrategyImplemented(String implemented) {
		this.strategyImplemented = implemented;
	}
	
	
	//amount of ratings received is increased at the start of the servlet
	//then all the ratings are setted
	//hence the equation for calculating an individual rating is 
	//((currentAverage*amountOfRatingsReceived-1)+userInputtedRating)/amountOfRatingsReceived
	public double getRatingOverall() {
		return ratingOverall;
	}

	public void setRatingOverall(double overall) {
		
		//get the average
		double overallAverage;
		overallAverage = ((ratingOverall*(amountOfRatingsReceived-1))+overall)/amountOfRatingsReceived;
		
		//round it to one decimal
		double overallOneDecimal;
		overallOneDecimal = (double) (Math.round(overallAverage*10))/10;
		
		//store it
		ratingOverall=overallOneDecimal;
	}

	public double getRatingEffectiveness() {
		return ratingEffectiveness;
	}

	public void setRatingEffectiveness(double effectiveness) {
		
		//get the average
		double effectivenessAverage;
		effectivenessAverage=((ratingEffectiveness*(amountOfRatingsReceived-1))+effectiveness)/amountOfRatingsReceived;
		
		//round it to one decimal
		double effectivenessOneDecimal;
		effectivenessOneDecimal = (double) (Math.round(effectivenessAverage*10))/10;
		
		//store it
		ratingEffectiveness=effectivenessOneDecimal;
	}

	public double getRatingImprovementFromSituationBefore() {
		return ratingImprovementFromSituationBefore;
	}

	public void setRatingImprovementFromSituationBefore(double improvement) {
		
		//get the average
		double improvementAverage;
		improvementAverage = ((ratingImprovementFromSituationBefore*(amountOfRatingsReceived-1))+improvement)/amountOfRatingsReceived;
		
		//round it to one decimal
		double improvementOneDecimal;
		improvementOneDecimal = (double) (Math.round(improvementAverage*10))/10;
		
		//store it
		ratingImprovementFromSituationBefore = improvementOneDecimal;
		
	}

	public double getRatingPractical() {
		return ratingPractical;
	}

	public void setRatingPractical(double practical) {
		
		//get the average
		double practicalAverage;
		practicalAverage = ((ratingPractical*(amountOfRatingsReceived-1))+practical)/amountOfRatingsReceived;
		
		//round it to one decimal
		double practicalOneDecimal;
		practicalOneDecimal = (double) (Math.round(practicalAverage*10))/10;
		
		//store it
		ratingPractical = practicalOneDecimal;
		
	}

	public double getRatingRelevanceToIncident() {
		return ratingRelevanceToIncident;
	}

	public void setRatingRelevanceToIncident(double relevance) {
		
		//get the average
		double relevanceAverage;
		relevanceAverage = ((ratingRelevanceToIncident*(amountOfRatingsReceived-1)) + relevance)/amountOfRatingsReceived;
		
		//round it to one decimal
		double relevanceOneDecimal;
		relevanceOneDecimal = (double) (Math.round(relevanceAverage*10))/10;
		
		//store it
		ratingRelevanceToIncident = relevanceOneDecimal;
	}

	public double getRatingSatisfactionOfStrategy() {
		return ratingSatisfactionOfStrategy;
	}

	public void setRatingSatisfactionOfStrategy(double satisfaction) {
		
		//get the average
		double satisfactionAverage;
		satisfactionAverage = ((ratingSatisfactionOfStrategy*(amountOfRatingsReceived-1)) + satisfaction)/amountOfRatingsReceived;
		
		//round it to one decimal
		double satisfactionOneDecimal;
		satisfactionOneDecimal = (double) (Math.round(satisfactionAverage*10))/10;

		//store it
		ratingSatisfactionOfStrategy = satisfactionOneDecimal;
	}
	
	//----------------------------------------------------------------------------------------------------------------------------------------
	//End Methods for Strategy phase

}
