package data;
import java.util.*;

public class IncidentDatabase {

	private static ArrayList<Incident> incidentsList;
	private static ArrayList<Incident> duplicatesList;
	

	static {
		incidentsList = new ArrayList<>();
		duplicatesList = new ArrayList<>();
	}
	
	public IncidentDatabase() {
		
	}
	
	public static ArrayList<Incident> getIncidentsList() {
		return incidentsList;
	}
	
	public static void addIncident(Incident in) {
		incidentsList.add(in);
	}
	
	public static ArrayList<Incident> getDuplicatesList() {
		return duplicatesList;
	}
	
	public static void addDuplicate(Incident duplicate) {
		duplicatesList.add(duplicate);
	}

	
}
