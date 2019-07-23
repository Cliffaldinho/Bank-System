package data;
import java.util.*;
import java.text.*;
import java.io.*;

public class UserDAO{

	private static ArrayList<UserBean> usersList;
	
	static {
		usersList = new ArrayList<>();
	}
	
	public UserDAO() {
		
	}
	
	public static ArrayList<UserBean> getUsersList() {
		return usersList;
	}

	public static void setUsers(ArrayList<UserBean> in) {
		usersList = in;
	}
	
	public static void addUsers(UserBean u) {
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
	
	public static UserBean findUserObjectByStaffID(String id) {
		int index =-1;
		
		for(int i=0;i<usersList.size();i++) {
			if(usersList.get(i).getStaffID().equalsIgnoreCase(id)) {
				index =i;
				break;
			}
		}
		
		return usersList.get(index);
	}
	
	public static UserBean getUserByStaffID(String id) {
		UserBean user = new UserBean();
		for(int i=0;i<usersList.size();i++) {
			if(usersList.get(i).getStaffID().equalsIgnoreCase(id)) {
				user = usersList.get(i);
			}
		}
		
		return user;
	}
	
	public static int getUsersListSize() {
		return usersList.size();
	}

	public static boolean checkID(String inID) {
		for (UserBean user: usersList) {
			if (user.getStaffID().equals(inID)){
				return true;
			}
		}
		return false;
	}
}
