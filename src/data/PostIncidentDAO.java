package data;

import java.util.ArrayList;

public class PostIncidentDAO {

	private static ArrayList<PostIncidentBean> postIncidentsList;
	
	static {
		postIncidentsList = new ArrayList<>();
		
	}
	
	public static ArrayList<PostIncidentBean> getPostIncidentList() {
		return postIncidentsList;
	}
	
	public static void addPostIncident(PostIncidentBean postIncident) {
		postIncidentsList.add(postIncident);
		
	}
	
	public static PostIncidentBean getPostIncidentByIncidentID(int id) {
		PostIncidentBean postIncident = new PostIncidentBean();
		
		for(int i=0;i<postIncidentsList.size();i++) {
			if(postIncidentsList.get(i).getIncidentID()==id) {
				postIncident=postIncidentsList.get(i);
				break;
			}
		}
		
		return postIncident;
	}
}
