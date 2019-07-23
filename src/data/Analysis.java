package data;
import java.util.*;

public class Analysis {

	private ArrayList<UserBean> users;
	private IncidentBean oneIncident;
	private String lessonsLearntFromIncident;
	private String rootCauseOfIncident;
	private ArrayList<Simulation> listOfSimulations;
	


	public Analysis() {
		users = new ArrayList<UserBean>();
		listOfSimulations= new ArrayList<Simulation>();
	}
	
	public Analysis(ArrayList<UserBean> u,IncidentBean one, String lessonsLearnt, String rootCause) {
		users = u;
		oneIncident=one;
		lessonsLearntFromIncident=lessonsLearnt;
		rootCauseOfIncident=rootCause;
	}
	
	public ArrayList<Simulation> getListOfSimulations() {
		return listOfSimulations;
	}

	public void setListOfSimulations(ArrayList<Simulation> simulations) {
		this.listOfSimulations = simulations;
	}
	
	public ArrayList<UserBean> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<UserBean> u) {
		this.users = u;
	}
	
	public IncidentBean getOneIncident() {
		return oneIncident;
	}

	
	public void setOneIncident(IncidentBean one) {
		this.oneIncident = one;
	}
	
	public String getRootCauseOfIncident() {
		return rootCauseOfIncident;
	}

	public void setRootCauseOfIncident(String rootCause) {
		this.rootCauseOfIncident = rootCause;
	}

	public String getLessonsLearntFromIncident() {
		return lessonsLearntFromIncident;
	}

	public void setLessonsLearntFromIncident(String lessonsLearnt) {
		this.lessonsLearntFromIncident = lessonsLearnt;
	}
	
}
