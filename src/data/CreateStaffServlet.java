package data;

import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

@WebServlet(urlPatterns={"/createUser"})
public class CreateStaffServlet extends HttpServlet {

	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException {
		
		PrintWriter out = res.getWriter();
		String tempLaw,tempSecurity,tempHuman,tempEquipment,tempAlgorithms,tempOther,tempIndex;
		
		User tempUser = new User();
		boolean check = true;
		
		
		if (req.getParameter("name") == null || req.getParameter("name").equals("")) {
			req.setAttribute("errorType", "name");
			req.getRequestDispatcher("CreateUser.jsp").forward(req, res);
			return;
		} else {
			tempUser.setName(req.getParameter("name"));
		}
		
		if (req.getParameter("id") == null || req.getParameter("id").equals("") || UserDatabase.checkID(req.getParameter("id"))) {
			req.setAttribute("errorType", "id");
			req.getRequestDispatcher("CreateUser.jsp").forward(req, res);
			return;
		} else {
			tempUser.setStaffID(req.getParameter("id"));
		}
		
		if (req.getParameter("password") == null || req.getParameter("password").equals("")) {
			req.setAttribute("errorType", "password");
			req.getRequestDispatcher("CreateUser.jsp").forward(req, res);
			return;
		} else {
			tempUser.setPassword(req.getParameter("password"));
		}
		
		if (req.getParameter("address") == null || req.getParameter("address").equals("")) {
			req.setAttribute("errorType", "address");
			req.getRequestDispatcher("CreateUser.jsp").forward(req, res);
			return;
		} else {
			tempUser.setAddress(req.getParameter("address"));
		}
		
		if (req.getParameter("contact") == null || req.getParameter("contact").equals("")) {
			req.setAttribute("errorType", "contact");
			req.getRequestDispatcher("CreateUser.jsp").forward(req, res);
			return;
		} else {
			tempUser.setContactNumber(req.getParameter("contact"));
		}
		
		if (req.getParameter("position").equals("branch")) {
			tempUser.setPosition(User.Position.Branch_Manager);
		} else if (req.getParameter("position").equals("data")) {
			tempUser.setPosition(User.Position.Data_Processing_Officer);
		} else if (req.getParameter("position").equals("it")) {
			tempUser.setPosition(User.Position.IT);
		} else if (req.getParameter("position").equals("finance")) {
			tempUser.setPosition(User.Position.Financial_Analyst);
		} else if (req.getParameter("position").equals("auditor")) {
			tempUser.setPosition(User.Position.Internal_Auditor);
		}
		
		tempLaw=req.getParameter("Law");
		tempSecurity=req.getParameter("Security");
		tempHuman=req.getParameter("Human");
		tempEquipment=req.getParameter("Equipment");
		tempAlgorithms=req.getParameter("Algorithms");
		tempOther=req.getParameter("Other");
		
		
		
		String total;
		
		String law,security,human,equipment,algorithms,other;
		
		if(tempLaw!=null) {
			law=IncidentBean.Category.Regulatory_Law.toString()+". ";
		} else {
			law="";
		}
		
		if(tempSecurity!=null) {
			security=IncidentBean.Category.Cyber_Security.toString()+". ";
		} else {
			security="";
		}
		
		if(tempHuman!=null) {
			human=IncidentBean.Category.Human_Issues.toString()+". ";
		} else {
			human="";
		}
		
		if(tempEquipment!=null) {
			equipment=IncidentBean.Category.Bank_Equipment.toString()+". ";
		} else {
			equipment="";
		}
		
		if(tempAlgorithms!=null) {
			algorithms=IncidentBean.Category.Bank_Algorithms.toString()+". ";
		} else {
			algorithms="";
		}
		
		if(tempOther!=null) {
			other=IncidentBean.Category.Other.toString()+". ";
		} else {
			other="";
		}
		
		
		
		total=law+security+human+equipment+algorithms+other;
		tempUser.setRolesToDo(total);
		UserDatabase.addUsers(tempUser);
		String path = getServletContext().getRealPath("./saves/users.dat");
		FileOutputStream fout = new FileOutputStream(getServletContext().getRealPath("./saves/users.dat"));
		ObjectOutputStream oout = new ObjectOutputStream(fout);
		oout.writeObject(UserDatabase.getUsersList());
		oout.close();
		fout.close();
		req.getRequestDispatcher("RolesForStaff.jsp").forward(req, res);
	}
}
