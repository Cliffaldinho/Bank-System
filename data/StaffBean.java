package data;

import java.io.Serializable;

//authentication bean
public class StaffBean implements Serializable {

	//Question: Does these go in Controller class or StaffBean class?
	//Manage User (Zac)
	//Create User (Zac)
	//Delete User (Zac)
	//Modify User (Zac)
	//User login (Zac)
	//User logout (Zac)
	
	private String username;
	private int authenticationLevel;
	
	public StaffBean() {
		
	}
	
	public void setUsername(String user) {
		this.username = user;
	}
	
	public String getUsername() {
		return username;
	}


	public void setAuthenticationLevel(int level) {
		this.authenticationLevel = level;
	}
	
	public int getAuthenticationLevel() {
		return authenticationLevel;
	}




	

	
}
