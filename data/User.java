package data;

public class User {



	private String name;
	private String address;
	private String contactNumber;
	private Position userPosition;
	
	//very simple
	//name etc
	
	public User() {
		
	}
	
	public User(String n,String add, String number, Position p) {
		this.name=n;
		this.address=add;
		this.contactNumber=number;
		this.userPosition=p;
	}
	
	enum Position {
	Branch_Manager,
	Data_Processing_Officer,
	IT,
	Financial_Analyst,
	Internal_Auditor;
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
	

	
}
