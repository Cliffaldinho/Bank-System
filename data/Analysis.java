package data;
import java.util.*;

public class Analysis {

	private ArrayList<User> users;
	private Incident oneIncident;
	private String lessonsLearntFromIncident;
	private String rootCauseOfIncident;
	
	public Analysis() {
		users = new ArrayList<User>();
	}
	
	public Analysis(ArrayList<User> u,Incident one, String lessonsLearnt, String rootCause) {
		users = u;
		oneIncident=one;
		lessonsLearntFromIncident=lessonsLearnt;
		rootCauseOfIncident=rootCause;
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
