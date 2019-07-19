package data;

import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns={"/finishDefineStaffRole"})
public class FinishSetStaffRoleServlet extends HttpServlet {

	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException {
		
		PrintWriter out = res.getWriter();
		String tempLaw,tempSecurity,tempHuman,tempEquipment,tempAlgorithms,tempOther,tempIndex;
		
		tempIndex=req.getParameter("StaffIndex");
		int theIndex=Integer.parseInt(tempIndex);
		
		if (req.getParameter("name").equals(null) || req.getParameter("name").equals("")) {
			//Do nothing
		} else {
			UserDatabase.getUserByIndex(theIndex).setName(req.getParameter("name"));
		}
		
		if (req.getParameter("id").equals(null) || req.getParameter("id").equals("")) {
			//Do nothing
		} else {
			UserDatabase.getUserByIndex(theIndex).setStaffID(req.getParameter("id"));
		}
		
		if (req.getParameter("password").equals(null) || req.getParameter("password").equals("")) {
			//Do nothing
		} else {
			UserDatabase.getUserByIndex(theIndex).setPassword(req.getParameter("password"));
		}
		
		if (req.getParameter("address").equals(null) || req.getParameter("address").equals("")) {
			//Do nothing
		} else {
			UserDatabase.getUserByIndex(theIndex).setAddress(req.getParameter("address"));
		}
		
		if (req.getParameter("contact").equals(null) || req.getParameter("contact").equals("")) {
			//Do nothing
		} else {
			UserDatabase.getUserByIndex(theIndex).setContactNumber(req.getParameter("contact"));
		}
		
		if (req.getParameter("position").equals("branch")) {
			UserDatabase.getUserByIndex(theIndex).setPosition(User.Position.Branch_Manager);
		} else if (req.getParameter("position").equals("data")) {
			UserDatabase.getUserByIndex(theIndex).setPosition(User.Position.Data_Processing_Officer);
		} else if (req.getParameter("position").equals("it")) {
			UserDatabase.getUserByIndex(theIndex).setPosition(User.Position.IT);
		} else if (req.getParameter("position").equals("finance")) {
			UserDatabase.getUserByIndex(theIndex).setPosition(User.Position.Financial_Analyst);
		} else if (req.getParameter("position").equals("auditor")) {
			UserDatabase.getUserByIndex(theIndex).setPosition(User.Position.Internal_Auditor);
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
		UserDatabase.getUsersList().get(theIndex).setRolesToDo(total);
		req.getRequestDispatcher("RolesForStaff.jsp").forward(req, res);
	}
}
