package data;
import java.util.*;

public class CaseStudy {

	private ArrayList<User> users;
	private ArrayList<IncidentBean> manyIncidents;
	private String lessonsLearntFromIncident;
	private String rootCauseOfIncident;
	
	public CaseStudy() {
		
	}

	public CaseStudy(ArrayList<User> u,ArrayList<IncidentBean> many,String lessonsLearnt, String rootCause) {
		this.users=u;
		this.manyIncidents=many;
		this.lessonsLearntFromIncident=lessonsLearnt;
		this.rootCauseOfIncident=rootCause;
	}
	
	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> u) {
		this.users = u;
	}

	
	public ArrayList<IncidentBean> getManyIncidents() {
		return manyIncidents;
	}

	public void setManyIncidents(ArrayList<IncidentBean> many) {
		this.manyIncidents = many;
	}
	
	public String getLessonsLearntFromIncident() {
		return lessonsLearntFromIncident;
	}

	public void setLessonsLearntFromIncident(String lessonsLearnt) {
		this.lessonsLearntFromIncident = lessonsLearnt;
	}

	public String getRootCauseOfIncident() {
		return rootCauseOfIncident;
	}

	public void setRootCauseOfIncident(String rootCause) {
		this.rootCauseOfIncident = rootCause;
	}
	
}
