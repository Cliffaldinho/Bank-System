package data;

import java.io.Serializable;

//authentication bean
public class StaffBean implements Serializable {

	private String username;

	public StaffBean() {
		
	}
	
	public void setUsername(String user) {
		this.username = user;
	}
	
	public String getUsername() {
		return username;
	}
	

	public boolean isLocked() {
		
	boolean locked=getUserByUsername().isLocked();
	
	return locked;
	}
	public int getAuthenticationLevel() {
		int authenticationLevel = -1;
		for (int i = 0; i < UserDAO.getUsersList().size(); i++){
			if (UserDAO.getUsersList().get(i).getStaffID().equals(username)){
				String clearance = UserDAO.getUsersList().get(i).getPosition().toString();
				if (clearance.equals("Branch Manager")){
					authenticationLevel = 1;
				} else {
					authenticationLevel = 0;
				}
			}
		}
		return authenticationLevel;
	}
	
	public UserBean getUserByUsername() {
		UserBean user=UserDAO.getUserByStaffID(username);
		return user;
	}
	
	public String getStaffName() {
		UserBean user = UserDAO.getUserByStaffID(username);
		String name=user.getName();
		return name;
	}
	
	public IncidentBean[] getAssignedIncidents() {
		UserBean user = UserDAO.getUserByStaffID(username);
		
		int[] assignedIncidentsID = user.getAssignedIncidentsID();
		
		IncidentBean[] assignedIncidents = new IncidentBean[assignedIncidentsID.length];
		
		for(int i=0;i<assignedIncidents.length;i++) {
			assignedIncidents[i]=IncidentDAO.getIncidentByIncidentID(assignedIncidentsID[i]);
		}
		
		return assignedIncidents;
	}
	
	public IncidentBean[] getAssignedUnreadIncidents() {
		UserBean user = UserDAO.getUserByStaffID(username);
		
		int[] assignedIncidentsID = user.getAssignedIncidentsID();
		
		int counter = 0;
		
		for (int i = 0; i < assignedIncidentsID.length; i++){
			if (IncidentDAO.getIncidentByIncidentID(assignedIncidentsID[i]).getRead() == false){
				counter++;
			}
		}
		
		IncidentBean[] assignedIncidents = new IncidentBean[counter];
		
		for(int i=0;i<assignedIncidents.length;i++) {
			if (IncidentDAO.getIncidentByIncidentID(assignedIncidentsID[i]).getRead() == false){
				assignedIncidents[i]=IncidentDAO.getIncidentByIncidentID(assignedIncidentsID[i]);
				IncidentDAO.getIncidentByIncidentID(assignedIncidentsID[i]).setRead();
			}
		}
		
		return assignedIncidents;
	}



	

	
}
