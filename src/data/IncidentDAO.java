package data;
import java.util.*;

public class IncidentDAO {
	
	private static int incidentCounter;
	


	private static ArrayList<IncidentBean> incidentsList;

	static {
		incidentsList = new ArrayList<>();
		incidentCounter=0;
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
		
		//add the incidentBean to Incident database
		incidentsList.add(in);
		
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
	public static void setIncidents(ArrayList<IncidentBean> in) {
		incidentsList = in;
	}
	
	public static ArrayList<IncidentBean> getArchivedList() {
		ArrayList<IncidentBean> archivedList= new ArrayList<>();
		for(int i=0;i<incidentsList.size();i++) {
			if(incidentsList.get(i).getIncidentStatus().equals(IncidentBean.Status.Archived)) {
				archivedList.add(incidentsList.get(i));
			}
		}
		
		return archivedList;
	}
	
	public static ArrayList<IncidentBean> getNonArchivedList() {
		ArrayList<IncidentBean> nonArchivedList = new ArrayList<>();
		
		for(int i=0;i<incidentsList.size();i++) {
			if(!incidentsList.get(i).getIncidentStatus().equals(IncidentBean.Status.Archived)) {
				nonArchivedList.add(incidentsList.get(i));
			}
		}
		
		return nonArchivedList;
	}

}
