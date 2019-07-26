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
		
		HttpSession aSession = req.getSession();
		
		UserBean user = (UserBean) aSession.getAttribute("userSelected");
		String userID = (String) aSession.getAttribute("userID");
		
		
		
		String[] receivedRoles =  req.getParameterValues("roles");
		//out.println(receivedRoles.length);
		String total="";
		String append="";
		
		//see if checkbox checked, so that won't get index out of bounds error
		if(receivedRoles!=null) {
			for(int i=0;i<receivedRoles.length;i++) {
			append=receivedRoles[i];
			total=total+append+". ";
			}
		}
		UserDAO.getUserByStaffID(userID).setRolesToDo(total);
		
		
		//out.println(total);
		
		//user.setRolesToDo(total);
		
		
		

		String positionValue =req.getParameter("position");
		
		UserBean.Position staffPosition=UserBean.Position.valueOf(positionValue);
		
		UserDAO.getUserByStaffID(userID).setPosition(staffPosition);
		
		//user.setPosition(staffPosition);
		
		//out.println(user.getPosition().toString());
		
		
		req.getRequestDispatcher("RolesForStaff.jsp").forward(req, res);
		
		
		
		/**
		String tempLaw,tempSecurity,tempHuman,tempEquipment,tempAlgorithms,tempOther,tempIndex;
		
		tempIndex=req.getParameter("StaffIndex");
		
		//to change this line coz identify by staff id not index now
		String theIndex=tempIndex;
		
		if (req.getParameter("name").equals(null) || req.getParameter("name").equals("")) {
			//Do nothing
		} else {
			UserDAO.getUserByStaffID(theIndex).setName(req.getParameter("name"));
		}
		
		if (req.getParameter("id").equals(null) || req.getParameter("id").equals("")) {
			//Do nothing
		} else {
			UserDAO.getUserByStaffID(theIndex).setStaffID(req.getParameter("id"));
		}
		
		if (req.getParameter("password").equals(null) || req.getParameter("password").equals("")) {
			//Do nothing
		} else {
			UserDAO.getUserByStaffID(theIndex).setPassword(req.getParameter("password"));
		}
		
		if (req.getParameter("address").equals(null) || req.getParameter("address").equals("")) {
			//Do nothing
		} else {
			UserDAO.getUserByStaffID(theIndex).setAddress(req.getParameter("address"));
		}
		
		if (req.getParameter("contact").equals(null) || req.getParameter("contact").equals("")) {
			//Do nothing
		} else {
			UserDAO.getUserByStaffID(theIndex).setContactNumber(req.getParameter("contact"));
		}
		
		if (req.getParameter("position").equals("branch")) {
			UserDAO.getUserByStaffID(theIndex).setPosition(UserBean.Position.Branch_Manager);
		} else if (req.getParameter("position").equals("data")) {
			UserDAO.getUserByStaffID(theIndex).setPosition(UserBean.Position.Data_Processing_Officer);
		} else if (req.getParameter("position").equals("it")) {
			UserDAO.getUserByStaffID(theIndex).setPosition(UserBean.Position.IT);
		} else if (req.getParameter("position").equals("finance")) {
			UserDAO.getUserByStaffID(theIndex).setPosition(UserBean.Position.Financial_Analyst);
		} else if (req.getParameter("position").equals("auditor")) {
			UserDAO.getUserByStaffID(theIndex).setPosition(UserBean.Position.Internal_Auditor);
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
		UserDAO.getUserByStaffID(tempIndex).setRolesToDo(total);
		req.getRequestDispatcher("RolesForStaff.jsp").forward(req, res);
		*/
	}
}
