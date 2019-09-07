package data;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import java.io.Serializable;

public class StatisticsBean implements Serializable{

	private int[] categoryAmount;
	private double[] categoryPercentage;
	
	
	
	public int[] getCategoryAmount() {
		
		setCategoryAmount();
		return categoryAmount;
	}

	public void setCategoryAmount() {
		categoryAmount=countCategory();
	}
	
	public String[] getCategoryPercentage() {
		
		String[] printCategoryPercentage = new String[6];
		
		setCategoryPercentage();
		for(int i=0;i<categoryPercentage.length;i++) {
			double wholeNumber = categoryPercentage[i]*100;
			String printWholeNumber = Double.toString(wholeNumber)+" %";
			printCategoryPercentage[i]= printWholeNumber;
		}
		return printCategoryPercentage;
	}

	public void setCategoryPercentage() {
		categoryPercentage=countCategoryPercentage();
	}

	
	public StatisticsBean() {
		
		categoryAmount = new int[6];
		categoryPercentage = new double[6];
		
		/**regulatoryLawCounter=0;
		cyberSecurityCounter=0;
		humanIssuesCounter=0;
		bankEquipmentCounter=0;
		bankAlgorithmsCounter=0;
		otherCounter=0;
		*/
		
	}
	
	public int[] countCategory() {
		
		int regulatoryLawCounter=0;
		int cyberSecurityCounter=0;
		int humanIssuesCounter=0;
		int bankEquipmentCounter=0;
		int bankAlgorithmsCounter=0;
		int otherCounter=0;
		
		int[] storeCount = new int[6];
		
		for(int i=0;i<IncidentDAO.getIncidentsList().size();i++) {
			if(IncidentDAO.getIncidentsList().get(i).getIncidentCategory().equals(IncidentBean.Category.Regulatory_Law)) {
				
				regulatoryLawCounter++;
				
			} else if (IncidentDAO.getIncidentsList().get(i).getIncidentCategory().equals(IncidentBean.Category.Cyber_Security) ) {
				
				cyberSecurityCounter++;
				
			} else if (IncidentDAO.getIncidentsList().get(i).getIncidentCategory().equals(IncidentBean.Category.Human_Issues) ) {
				
				humanIssuesCounter++;
				
			} else if (IncidentDAO.getIncidentsList().get(i).getIncidentCategory().equals(IncidentBean.Category.Bank_Equipment) ) {
				
				bankEquipmentCounter++;
				
			} else if (IncidentDAO.getIncidentsList().get(i).getIncidentCategory().equals(IncidentBean.Category.Bank_Algorithms) ) {
				
				bankAlgorithmsCounter++;
				
			} else if (IncidentDAO.getIncidentsList().get(i).getIncidentCategory().equals(IncidentBean.Category.Other) ) {
				
				otherCounter++;
			}
			
		}
		
		storeCount[0]=regulatoryLawCounter;
		storeCount[1]=cyberSecurityCounter;
		storeCount[2]=humanIssuesCounter;
		storeCount[3]=bankEquipmentCounter;
		storeCount[4]=bankAlgorithmsCounter;
		storeCount[5]=otherCounter;
		
		return storeCount;
		
	}
	
	public double[] countCategoryPercentage() {
		
		int[] amountInCategory = countCategory();
		
		double totalAmountOfIncidents=IncidentDAO.getIncidentsList().size();

		double regulatoryLawPercentage;
		double cyberSecurityPercentage;
		double humanIssuesPercentage;
		double bankEquipmentPercentage;
		double bankAlgorithmsPercentage;
		double otherPercentage;
		
		regulatoryLawPercentage = amountInCategory[0]/totalAmountOfIncidents;
		cyberSecurityPercentage = amountInCategory[1]/totalAmountOfIncidents;
		humanIssuesPercentage = amountInCategory[2]/totalAmountOfIncidents;
		bankEquipmentPercentage = amountInCategory[3]/totalAmountOfIncidents;
		bankAlgorithmsPercentage = amountInCategory[4]/totalAmountOfIncidents;
		otherPercentage=amountInCategory[5]/totalAmountOfIncidents;
		
		double printRegulatoryLaw= (double) Math.round(regulatoryLawPercentage*100)/100;
		double printCyberSecurity= (double) Math.round(cyberSecurityPercentage*100)/100;
		double printHumanIssues= (double) Math.round(humanIssuesPercentage*100)/100;
		double printBankEquipment= (double) Math.round(bankEquipmentPercentage*100)/100;
		double printBankAlgorithms= (double) Math.round(bankAlgorithmsPercentage*100)/100;
		double printOther= (double) Math.round(otherPercentage*100)/100;
		
		double[] storePercentage = new double[6];
		
		storePercentage[0]=printRegulatoryLaw;
		storePercentage[1]=printCyberSecurity;
		storePercentage[2]=printHumanIssues;
		storePercentage[3]=printBankEquipment;
		storePercentage[4]=printBankAlgorithms;
		storePercentage[5]=printOther;
		
		return storePercentage;
		
	}
	
	public ArrayList<IncidentBean> getArchivedList() {
		
		ArrayList<IncidentBean> archivedList = new ArrayList<>();
		ArrayList<IncidentBean> allIncidentsList = IncidentDAO.getIncidentsList();
		
		for(int i=0;i<allIncidentsList.size();i++) {
			
		if(allIncidentsList.get(i).getIncidentStatus().equals(IncidentBean.Status.Archived)) {
			IncidentBean incident = allIncidentsList.get(i);
			archivedList.add(incident);
		}
		
		}
		return archivedList;
	}
	
}
