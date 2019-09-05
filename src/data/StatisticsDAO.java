package data;

import java.util.ArrayList;

public class StatisticsDAO {
	
	private static int userCounter;
	private static double averageFinishTime;//variable to hold "average time things get done" 
	private static double quickestFinishTime;//Shortest time things get done
	private static double slowestFinishTime;//Longest time things get done
	private static String peakTimeOfIssue;//Peak time of issues, i.e. midnight, early morning, noon, etc
	private static int regulatoryLawIncidentCount;//Each category's incident count
	private static int cyberSecurityIncidentCount;//Each category's incident count
	private static int humanIssuesIncidentCount;//Each category's incident count
	private static int bankEquipmentIncidentCount;//Each category's incident count
	private static int bankAlgorithmsIncidentCount;//Each category's incident count
	private static int otherIncidentCount;//Each category's incident count
	//Each staff's incident completion rate(%)
	//Each staff's average incident completion speed
	//Number of active/inactive customer accounts
	//Number of suspicious accounts
	//Historical timeline of incidents data
	//Number of robberies
	//Number of spam/fake/false incidents
	
	////////////////////////////////////////// ( constructor? ) /////////////////////////////////////////////////
	static {
		userCounter = 0;
		averageFinishTime = 0;
		quickestFinishTime = 0;
		slowestFinishTime = 0;
		peakTimeOfIssue = "";
		regulatoryLawIncidentCount = 0;
		cyberSecurityIncidentCount = 0;
		humanIssuesIncidentCount = 0;
		bankEquipmentIncidentCount = 0;
		bankAlgorithmsIncidentCount = 0;
		otherIncidentCount = 0;
	}

	////////////////////////////////////////////////( mutators ) //////////////////////////////////////////////////
	
	
	
	////////////////////////////////////////// ( getters and setters ) ////////////////////////////////////////////
	public static int getUserCounter() {
		return userCounter;
	}

	public static void setUserCounter(int userCounter) {
		StatisticsDAO.userCounter = userCounter;
	}

	public static double getAverageFinishTime() {
		return averageFinishTime;
	}

	public static void setAverageFinishTime(double averageFinishTime) {
		StatisticsDAO.averageFinishTime = averageFinishTime;
	}

	public static double getQuickestFinishTime() {
		return quickestFinishTime;
	}

	public static void setQuickestFinishTime(double quickestFinishTime) {
		StatisticsDAO.quickestFinishTime = quickestFinishTime;
	}

	public static double getSlowestFinishTime() {
		return slowestFinishTime;
	}

	public static void setSlowestFinishTime(double slowestFinishTime) {
		StatisticsDAO.slowestFinishTime = slowestFinishTime;
	}

	public static String getPeakTimeOfIssue() {
		return peakTimeOfIssue;
	}

	public static void setPeakTimeOfIssue(String peakTimeOfIssue) {
		StatisticsDAO.peakTimeOfIssue = peakTimeOfIssue;
	}

	public static int getRegulatoryLawIncidentCount() {
		return regulatoryLawIncidentCount;
	}

	public static void setRegulatoryLawIncidentCount(int regulatoryLawIncidentCount) {
		StatisticsDAO.regulatoryLawIncidentCount = regulatoryLawIncidentCount;
	}

	public static int getCyberSecurityIncidentCount() {
		return cyberSecurityIncidentCount;
	}

	public static void setCyberSecurityIncidentCount(int cyberSecurityIncidentCount) {
		StatisticsDAO.cyberSecurityIncidentCount = cyberSecurityIncidentCount;
	}

	public static int getHumanIssuesIncidentCount() {
		return humanIssuesIncidentCount;
	}

	public static void setHumanIssuesIncidentCount(int humanIssuesIncidentCount) {
		StatisticsDAO.humanIssuesIncidentCount = humanIssuesIncidentCount;
	}

	public static int getBankEquipmentIncidentCount() {
		return bankEquipmentIncidentCount;
	}

	public static void setBankEquipmentIncidentCount(int bankEquipmentIncidentCount) {
		StatisticsDAO.bankEquipmentIncidentCount = bankEquipmentIncidentCount;
	}

	public static int getBankAlgorithmsIncidentCount() {
		return bankAlgorithmsIncidentCount;
	}

	public static void setBankAlgorithmsIncidentCount(int bankAlgorithmsIncidentCount) {
		StatisticsDAO.bankAlgorithmsIncidentCount = bankAlgorithmsIncidentCount;
	}

	public static int getOtherIncidentCount() {
		return otherIncidentCount;
	}

	public static void setOtherIncidentCount(int otherIncidentCount) {
		StatisticsDAO.otherIncidentCount = otherIncidentCount;
	}
	
	
	
}
