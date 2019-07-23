package data;
import java.util.*;
import java.io.Serializable;

//is now a JavaBean
public class RiskManagementStrategy implements Serializable {



	private UserBean riskStrategyWriter;
	private UserBean.Position delegatedStaff;
	private String riskTitle;
	private Category incidentCategory;
	private String risk;
	private String managementStrategy;
	



	public RiskManagementStrategy() 
	{	}
	
	public RiskManagementStrategy(UserBean writer, UserBean.Position newDelegatedStaff, String title, Category newCategory, String newRisk, String newStrategy) 
	{	
		riskStrategyWriter = writer;
		delegatedStaff = newDelegatedStaff;
		riskTitle = title;
		incidentCategory = newCategory;
		risk = newRisk;
		managementStrategy = newStrategy;
	}

	public enum Category 
	{
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
	

	public UserBean getRiskStrategyWriter() {
		return riskStrategyWriter;
	}

	public void setRiskStrategyWriter(UserBean riskStrategyWriter) {
		this.riskStrategyWriter = riskStrategyWriter;
	}

	public String getRiskTitle() {
		return riskTitle;
	}

	public void setRiskTitle(String riskTitle) {
		this.riskTitle = riskTitle;
	}

	public Category getIncidentCategory() {
		return incidentCategory;
	}

	public void setIncidentCategory(Category incidentCategory) {
		this.incidentCategory = incidentCategory;
	}

	public String getRisk() {
		return risk;
	}

	public void setRisk(String risk) {
		this.risk = risk;
	}

	public String getManagementStrategy() {
		return managementStrategy;
	}

	public void setManagementStrategy(String managementStrategy) {
		this.managementStrategy = managementStrategy;
	}

		
}
