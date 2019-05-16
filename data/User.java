package data;

public class User {



	private String name;
	private String address;
	private String contactNumber;
	private String position;
	
	//very simple
	//name etc
	
	public User() {
		
	}
	
	public User(String n,String add, String number, String p) {
		this.name=n;
		this.address=add;
		this.contactNumber=number;
		this.position=p;
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

	public String getPosition() {
		return position;
	}

	public void setPosition(String p) {
		this.position = p;
	}
	

	
}
