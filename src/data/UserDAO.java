package data;
import java.util.*;
import java.text.*;
import java.io.*;

public class UserDAO{

	//Counters for users added throughout history. Remain same even after a user is deleted.

	private static int userCounter;
	
	private static ArrayList<UserBean> usersList;
	
	static {
		usersList = new ArrayList<>();
		userCounter=0;
	}
	
	public UserDAO() {
		
	}
	
	public static ArrayList<UserBean> getUsersList() {
		return usersList;
	}

	public static void setUsers(ArrayList<UserBean> in) {
		usersList = in;
	}
	
	public static int getUserCounter() {
		return userCounter;
	}

	public static void setUserCounter(int counter) {
		userCounter=counter;
	}
	
	public static void addUsers(UserBean u) {
		
		//create staff id (i.e. b1,b2,b3,b4,b5)
		String append="b";
		
		int tempCounter=userCounter+1;
		userCounter++;
		
		String stringCounter=String.valueOf(tempCounter);
		String id=append+stringCounter;
		
		u.setStaffID(id);
		
		//default password given by system is password. user can then change it themselves.
		u.setPassword("password");
		
		
		usersList.add(u);
		
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
	
	public static void deleteUserByUserID(String id) {
		for(int i=0;i<usersList.size();i++) {
			if(usersList.get(i).getStaffID().equalsIgnoreCase(id)) {
				usersList.remove(i);
				break;
			}
		}
	}
	
	public static int getUsersListSize() {
		return usersList.size();
	}


}
