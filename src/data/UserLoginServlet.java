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
		if (UserDAO.getUsersList().isEmpty()) {
			File file = new File(getServletContext().getRealPath("./saves/users.dat"));
			if (!file.exists()) {
				UserBean tempUser = new UserBean();
				tempUser.setName("Bob Smith");
				tempUser.setAddress("Elm Street");
				tempUser.setContactNumber("0403526395");
				tempUser.setPosition(UserBean.Position.Financial_Analyst);
				
				UserBean tempUserTwo = new UserBean();
				tempUserTwo.setName("Alice Diaz");
				tempUserTwo.setAddress("Avoca Lane");
				tempUserTwo.setContactNumber("0423436405");
				tempUserTwo.setPosition(UserBean.Position.Internal_Auditor);
				
				UserBean tempUserThree = new UserBean();
				tempUserThree.setName("Henry Stewart");
				tempUserThree.setAddress("Mahogany Lane");
				tempUserThree.setContactNumber("0435243964");
				tempUserThree.setPosition(UserBean.Position.Data_Processing_Officer);
				
				UserBean tempUserFour = new UserBean();
				tempUserFour.setName("Enzo Rogers");
				tempUserFour.setAddress("Wilsons Creek");
				tempUserFour.setContactNumber("0432364354");
				tempUserFour.setPosition(UserBean.Position.Branch_Manager);
				
				UserBean tempUserFive = new UserBean();
				tempUserFive.setName("Chloe Morgan");
				tempUserFive.setAddress("Chicago Lane");
				tempUserFive.setContactNumber("0473423537");
				tempUserFive.setPosition(UserBean.Position.IT);
				
				UserDAO.addUsers(tempUser);
				UserDAO.addUsers(tempUserTwo);
				UserDAO.addUsers(tempUserThree);
				UserDAO.addUsers(tempUserFour);
				UserDAO.addUsers(tempUserFive);
				try{
					FileOutputStream fout = new FileOutputStream(getServletContext().getRealPath("./saves/users.dat"));
					ObjectOutputStream oout = new ObjectOutputStream(fout);
					oout.writeObject(UserDAO.getUsersList());
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
					ArrayList<UserBean> users = new ArrayList<UserBean>();
					users = (ArrayList<UserBean>)ostream.readObject();
					UserDAO.setUsers(users);
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
			ArrayList<IncidentBean> incidents = new ArrayList<IncidentBean>();
			incidents = (ArrayList<IncidentBean>)ostream.readObject();
			IncidentDAO.setIncidents(incidents);
			ostream.close();
			fstream.close();
		}
		catch(Exception e){
			System.out.println("An exception "+e.toString()+" has occurred.");
		}
		
		PrintWriter out =res.getWriter();
		for (int i = 0; i < UserDAO.getUsersList().size(); i++){
			String id = UserDAO.getUsersList().get(i).getStaffID();
		
			
			String pw = UserDAO.getUsersList().get(i).getPassword();
		
			
			if (userid.equals(id)){
				if (password.equals(pw)){
					found = true;
					
					//username in staffBean = userID
					logAuth.setUsername(userid);
			
					//logAuth is a staffBean
					aSession.setAttribute("logAuth",logAuth);
					
					boolean isSearch=false,isSort=false;
					aSession.setAttribute("isSearch", isSearch);
					aSession.setAttribute("isSort", isSort);
					
					ArrayList<IncidentBean> incidentsList = IncidentDAO.getIncidentsList();
					aSession.setAttribute("listOfIncidents", incidentsList);
					
					ArrayList<UserBean> staffList = UserDAO.getUsersList();
					aSession.setAttribute("listOfStaff", staffList);
					
					
					req.getRequestDispatcher("prepareList").forward(req,res);
				}
			}
		}	
		if (!found){
			req.setAttribute("loginError","Incorrect user ID or password.");
			req.getRequestDispatcher("FailedUserLogin.jsp").forward(req,res);
		}
	}

	
	
	
} 
