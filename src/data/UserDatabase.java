package data;
import java.util.*;
import java.text.*;
import java.io.*;

public class UserDatabase{

	private static ArrayList<User> usersList;
	
	static {
		usersList = new ArrayList<>();
	}
	
	public UserDatabase() {
		
	}
	
	public static ArrayList<User> getUsersList() {
		return usersList;
	}

	public static void setUsers(ArrayList<User> in) {
		usersList = in;
	}
	
	public static void addUsers(User u) {
		usersList.add(u);
	}

	public static int findUserIndexByStaffID(String id) {

		int index=-1;
		
		for (int i=0;i<usersList.size();i++) {
			if(usersList.get(i).getStaffID().equalsIgnoreCase(id)) {
				index = i;
				break;
			}
		}
		
		return index;
	}
	
	public static User findUserObjectByStaffID(String id) {
		int index =-1;
		
		for(int i=0;i<usersList.size();i++) {
			if(usersList.get(i).getStaffID().equalsIgnoreCase(id)) {
				index =i;
				break;
			}
		}
		
		return usersList.get(index);
	}
	
	public static User getUserByIndex(int index) {
		return usersList.get(index);
	}
	
	public static int getUsersListSize() {
		return usersList.size();
	}

	public static boolean checkID(String inID) {
		for (User user: usersList) {
			if (user.getStaffID().equals(inID)){
				return true;
			}
		}
		return false;
	}
}
