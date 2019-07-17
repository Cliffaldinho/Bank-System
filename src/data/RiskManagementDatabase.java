package data;
import java.util.*;

public class RiskManagementDatabase {

	private static ArrayList<RiskManagementStrategy> riskManagementList;
	
	static {
		riskManagementList = new ArrayList<>();
	}
	
	public RiskManagementDatabase() {
		
	}
	
	public static ArrayList<RiskManagementStrategy> getRiskManagementList() {
		return riskManagementList;
	}

	public static void addRiskManagementStrategy(RiskManagementStrategy rms) {
		riskManagementList.add(rms);
	}
		
}
