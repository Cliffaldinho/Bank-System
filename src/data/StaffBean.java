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
	
	public int getAuthenticationLevel() {
		int authenticationLevel = -1;
		for (int i = 0; i < UserDatabase.getUsersList().size(); i++){
			if (UserDatabase.getUsersList().get(i).getStaffID().equals(username)){
				String clearance = UserDatabase.getUsersList().get(i).getPosition().toString();
				if (clearance.equals("Branch Manager")){
					authenticationLevel = 1;
				} else {
					authenticationLevel = 0;
				}
			}
		}
		return authenticationLevel;
	}




	

	
}
