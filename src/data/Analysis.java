package data;
import java.util.*;

public class Analysis {

	private ArrayList<User> users;
	private Incident oneIncident;
	private String lessonsLearntFromIncident;
	private String rootCauseOfIncident;
	private ArrayList<Simulation> listOfSimulations;
	


	public Analysis() {
		users = new ArrayList<User>();
		listOfSimulations= new ArrayList<Simulation>();
	}
	
	public Analysis(ArrayList<User> u,Incident one, String lessonsLearnt, String rootCause) {
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
	
	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> u) {
		this.users = u;
	}
	
	public Incident getOneIncident() {
		return oneIncident;
	}

	
	public void setOneIncident(Incident one) {
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
