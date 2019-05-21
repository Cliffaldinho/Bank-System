package data;

import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;

import javax.swing.JOptionPane;
import javax.swing.JOptionPane.*;

//receives from UserLogin.jsp


@WebServlet(urlPatterns={"/userLogin"})
public class UserLoginServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		HttpSession aSession = req.getSession(true);
		StaffBean logAuth = (StaffBean) aSession.getAttribute("logAuth");
		String userid = req.getParameter("userid");
		String password = req.getParameter("password");
		boolean found = false;
		if(UserDatabase.getUsersList().isEmpty()) {
		User tempUser = new User("Bob Smith","Elm Street","0403526395",User.Position.Financial_Analyst,"f111", "password");
		User tempUserTwo = new User("Alice Diaz","Avoca Lane","0423436405",User.Position.Internal_Auditor,"a111", "password");
		User tempUserThree = new User("Henry Stewart","Mahogany Lane","0435243964",User.Position.Data_Processing_Officer,"d111", "password");
		User tempUserFour = new User("Enzo Rogers","Wilsons Creek","0432364354",User.Position.Branch_Manager,"b111", "password");
		User tempUserFive = new User ("Chloe Morgan","Chicago Lane","0473423537",User.Position.IT,"i111", "password");
		UserDatabase.addUsers(tempUser);
		UserDatabase.addUsers(tempUserTwo);
		UserDatabase.addUsers(tempUserThree);
		UserDatabase.addUsers(tempUserFour);
		UserDatabase.addUsers(tempUserFive);}
		
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
			req.getRequestDispatcher("UserLogin.jsp").forward(req,res);
		}
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
		
		
		
	}
	
	
	
} 
