package data;
import java.util.*;

public class IncidentDAO {

	private static int incidentCounter;
	


	private static ArrayList<IncidentBean> incidentsList;
	private static ArrayList<IncidentBean> duplicatesList;
	

	static {
		incidentsList = new ArrayList<>();
		incidentCounter=0;
		duplicatesList = new ArrayList<>();
	}
	
	public IncidentDAO() {
		
	}
	
	
	public static int getIncidentCounter() {
		return incidentCounter;
	}

	public static void setIncidentCounter(int counter) {
		IncidentDAO.incidentCounter = counter;
	}
	
	public static ArrayList<IncidentBean> getIncidentsList() {
		return incidentsList;
	}
	
	public static void addIncident(IncidentBean in) {
		incidentsList.add(in);
		//incidentCounter++;
		//in.setIncidentID(incidentCounter);
	}
	
	public static IncidentBean getIncidentByIncidentID(int id) {
		IncidentBean incident = new IncidentBean();
		
		for(int i=0;i<incidentsList.size();i++) {
			if(incidentsList.get(i).getIncidentID()==id) {
				incident=incidentsList.get(i);
				break;
			}
		}
		
		return incident;
		
	}
	
	public static void deleteIncidentByIncidentID(int id) {
		for(int i=0;i<incidentsList.size();i++) {
			if(incidentsList.get(i).getIncidentID()==id) {
				incidentsList.remove(i);
				break;
			}
		}
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
