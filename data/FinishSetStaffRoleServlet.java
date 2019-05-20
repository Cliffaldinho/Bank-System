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

	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException {
		
		PrintWriter out = res.getWriter();
		String tempLaw,tempSecurity,tempHuman,tempEquipment,tempAlgorithms,tempOther,tempIndex;
		
		tempLaw=req.getParameter("Law");
		tempSecurity=req.getParameter("Security");
		tempHuman=req.getParameter("Human");
		tempEquipment=req.getParameter("Equipment");
		tempAlgorithms=req.getParameter("Algorithms");
		tempOther=req.getParameter("Other");
		
		tempIndex=req.getParameter("StaffIndex");
		
		String total;
		
		String law,security,human,equipment,algorithms,other;
		
		if(tempLaw!=null) {
			law=Incident.Category.Regulatory_Law.toString()+". ";
		} else {
			law="";
		}
		
		if(tempSecurity!=null) {
			security=Incident.Category.Cyber_Security.toString()+". ";
		} else {
			security="";
		}
		
		if(tempHuman!=null) {
			human=Incident.Category.Human_Issues.toString()+". ";
		} else {
			human="";
		}
		
		if(tempEquipment!=null) {
			equipment=Incident.Category.Bank_Equipment.toString()+". ";
		} else {
			equipment="";
		}
		
		if(tempAlgorithms!=null) {
			algorithms=Incident.Category.Bank_Algorithms.toString()+". ";
		} else {
			algorithms="";
		}
		
		if(tempOther!=null) {
			other=Incident.Category.Other.toString()+". ";
		} else {
			other="";
		}
		
		
		int theIndex=Integer.parseInt(tempIndex);

		total=law+security+human+equipment+algorithms+other;
		UserDatabase.getUsersList().get(theIndex).setRolesToDo(total);
		req.getRequestDispatcher("RolesForStaff.jsp").forward(req, res);
	}
}
