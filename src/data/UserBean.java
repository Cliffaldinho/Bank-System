package data;

public class UserBean implements java.io.Serializable{



	private String name;
	private String address;
	private String contactNumber;
	private String password;
	
	private String staffID;
	private String rolesToDo;

	public String getRolesToDo() {
		String roles;
		
		if(rolesToDo.isBlank()) {
			roles="No roles assigned.";
		} else {
			roles=rolesToDo;
		}
		
		return roles;
	}

	public void setRolesToDo(String roles) {

		this.rolesToDo = roles;
	}
	
	
	

	public Position getUserPosition() {
		return userPosition;
	}

	public void setUserPosition(Position userPosition) {
		this.userPosition = userPosition;
	}


	private Position userPosition;
	
	//very simple
	//name etc
	
	public UserBean() {
		rolesToDo="";
		//password="password";
		
	}

	
	public enum Position {
		Branch_Manager {
			public String toString() {
				return "Branch Manager";
			}
		},
		Data_Processing_Officer {
			public String toString() {
				return "Data Processing Officer";
			}
		},
		IT {
			public String toString() {
				return "IT";
			}
		},
		Financial_Analyst {
			public String toString() {
				return "Financial Analyst";
			}
		},
		Internal_Auditor {
			public String toString() {
				return "Internal Auditor";
			}
		};
	}
	
	public String getStaffID() {
		return staffID;
	}
	
	public void setStaffID(String id) {
		
		this.staffID=id;
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String nm) {
		this.name = nm;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String add) {
		this.address = add;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String number) {
		this.contactNumber = number;
	}

	public Position getPosition() {
		return userPosition;
	}
	

	public void setPosition(Position p) {
		this.userPosition = p;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String p) {
		password = p;
	}
	
}
