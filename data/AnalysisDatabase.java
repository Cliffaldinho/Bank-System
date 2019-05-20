package data;
import java.util.*;

public class AnalysisDatabase {

	private static ArrayList<Analysis> analysisList;
	
	static {
		analysisList = new ArrayList<>();
	}
	
	
	public static ArrayList<Analysis> getAnalysisList() {
		return analysisList;
	}
	
	public static void addAnalysis(Analysis a) {
		analysisList.add(a);
	}
	
}
