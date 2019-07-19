package data;
import java.util.*;

public class IncidentDAO {

	private static ArrayList<IncidentBean> incidentsList;
	private static ArrayList<IncidentBean> duplicatesList;
	

	static {
		incidentsList = new ArrayList<>();
		duplicatesList = new ArrayList<>();
	}
	
	public IncidentDAO() {
		
	}
	
	public static ArrayList<IncidentBean> getIncidentsList() {
		return incidentsList;
	}
	
	public static void addIncident(IncidentBean in) {
		incidentsList.add(in);
	}
	
	public static ArrayList<IncidentBean> getDuplicatesList() {
		return duplicatesList;
	}
	
	public static void addDuplicate(IncidentBean duplicate) {
		duplicatesList.add(duplicate);
	}

	public static void setIncidents(ArrayList<IncidentBean> in) {
		incidentsList = in;
	}
	
	public static void setDuplicates(ArrayList<IncidentBean> in) {
		duplicatesList = in;
	}
	
}
