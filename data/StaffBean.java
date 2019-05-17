package data;

import java.io.Serializable;

//authentication bean
public class StaffBean implements Serializable {

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
