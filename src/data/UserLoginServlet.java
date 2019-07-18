package data;

import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;

import java.util.*;

import javax.swing.JOptionPane;
import javax.swing.JOptionPane.*;

import java.util.*;
import java.text.*;
import java.io.*;

//receives from UserLogin.jsp


@WebServlet(urlPatterns={"/userLogin"})
public class UserLoginServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		HttpSession aSession = req.getSession(true);
		StaffBean logAuth = (StaffBean) aSession.getAttribute("logAuth");
		String userid = req.getParameter("userid");
		String password = req.getParameter("password");
		boolean found = false;
		if (UserDatabase.getUsersList().isEmpty()) {
			File file = new File(getServletContext().getRealPath("./saves/users.dat"));
			if (!file.exists()) {
				User tempUser = new User("Bob Smith","Elm Street","0403526395",User.Position.Financial_Analyst,"f111", "password");
				User tempUserTwo = new User("Alice Diaz","Avoca Lane","0423436405",User.Position.Internal_Auditor,"a111", "password");
				User tempUserThree = new User("Henry Stewart","Mahogany Lane","0435243964",User.Position.Data_Processing_Officer,"d111", "password");
				User tempUserFour = new User("Enzo Rogers","Wilsons Creek","0432364354",User.Position.Branch_Manager,"b111", "password");
				User tempUserFive = new User ("Chloe Morgan","Chicago Lane","0473423537",User.Position.IT,"i111", "password");
				UserDatabase.addUsers(tempUser);
				UserDatabase.addUsers(tempUserTwo);
				UserDatabase.addUsers(tempUserThree);
				UserDatabase.addUsers(tempUserFour);
				UserDatabase.addUsers(tempUserFive);
				try{
					FileOutputStream fout = new FileOutputStream(getServletContext().getRealPath("./saves/users.dat"));
					ObjectOutputStream oout = new ObjectOutputStream(fout);
					oout.writeObject(UserDatabase.getUsersList());
					oout.close();
					fout.close();
				}
				catch(Exception e){
					System.out.println("An exception has occurred.");
				}
			}
			
				try{
					String path = getServletContext().getRealPath("./saves/users.dat");
					FileInputStream fstream = new FileInputStream(path);
					ObjectInputStream ostream = new ObjectInputStream(fstream);
					ArrayList<User> users = new ArrayList<User>();
					users = (ArrayList<User>)ostream.readObject();
					UserDatabase.setUsers(users);
					ostream.close();
					fstream.close();
				}
				catch(Exception e){
					System.out.println("An exception "+e.toString()+" has occurred.");
				}
			}
			
		try{
			String path = getServletContext().getRealPath("./saves/incidents.dat");
			FileInputStream fstream = new FileInputStream(path);
			ObjectInputStream ostream = new ObjectInputStream(fstream);
			ArrayList<Incident> incidents = new ArrayList<Incident>();
			incidents = (ArrayList<Incident>)ostream.readObject();
			IncidentDatabase.setIncidents(incidents);
			ostream.close();
			fstream.close();
		}
		catch(Exception e){
			System.out.println("An exception "+e.toString()+" has occurred.");
		}
		
		try{
			String path = getServletContext().getRealPath("./saves/duplicates.dat");
			FileInputStream fstream = new FileInputStream(path);
			ObjectInputStream ostream = new ObjectInputStream(fstream);
			ArrayList<Incident> duplicates = new ArrayList<Incident>();
			duplicates = (ArrayList<Incident>)ostream.readObject();
			IncidentDatabase.setDuplicates(duplicates);
			ostream.close();
			fstream.close();
		}
		catch(Exception e){
			System.out.println("An exception "+e.toString()+" has occurred.");
		}
		
		for (int i = 0; i < UserDatabase.getUsersList().size(); i++){
			String id = UserDatabase.getUsersList().get(i).getStaffID();
			String pw = UserDatabase.getUsersList().get(i).getPassword();
			if (userid.equals(id)){
				if (password.equals(pw)){
					found = true;
					logAuth.setUsername(userid);
			
					aSession.setAttribute("logAuth",logAuth);
					
					req.getRequestDispatcher("ListOfIncidents.jsp").forward(req,res);
				}
			}
		}	
		if (!found){
			req.setAttribute("loginError","Incorrect user ID or password.");
			req.getRequestDispatcher("FailedUserLogin.jsp").forward(req,res);
		}
	}

	
	
	
} 
