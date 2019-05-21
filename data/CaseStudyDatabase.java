package data;
import java.util.*;

public class CaseStudyDatabase {

	private static ArrayList<CaseStudy> caseStudyList;
	
	static {

		caseStudyList = new ArrayList<>();
	}
	
	
	public CaseStudyDatabase() {
		
	}
	
	
	public static ArrayList<CaseStudy> getCaseStudyList() {
		return caseStudyList;
	}
	
	public static void addCaseStudy(CaseStudy aCase) {
		caseStudyList.add(aCase);
	}

	
}
