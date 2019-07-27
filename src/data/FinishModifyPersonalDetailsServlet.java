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

@WebServlet(urlPatterns={"/finishPersonalDetails"})
public class FinishModifyPersonalDetailsServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException {
		
		PrintWriter out = res.getWriter();
		
		HttpSession aSession = req.getSession();
		StaffBean staffLoggedIn = (StaffBean) aSession.getAttribute("logAuth");
		String staffID=staffLoggedIn.getUsername();
		
		String staffNewName=req.getParameter("modifyName");
		
		if(!staffNewName.isBlank()) {
		
		UserDAO.getUserByStaffID(staffID).setName(staffNewName);
		}
		
		String staffNewAddress=req.getParameter("modifyAddress");
		
		if(!staffNewAddress.isBlank()) {
		UserDAO.getUserByStaffID(staffID).setAddress(staffNewAddress);
		}
		
		String staffNewContact=req.getParameter("modifyContact");
		
		if(!staffNewContact.isBlank()) {
			UserDAO.getUserByStaffID(staffID).setContactNumber(staffNewContact);
		}
		
		String staffNewPassword=req.getParameter("modifyPassword");
		
		//if pass client side validation password: All three password fields entered, New password and reentered new password same
		if(!staffNewPassword.isBlank()) {
			
			boolean incorrectOldPassword=false;
			
			String oldPasswordEntered=req.getParameter("originalPassword");
			String databasePassword=UserDAO.getUserByStaffID(staffID).getPassword();
			
			
			//if old password user entered is not the same as the password in database
			if(!oldPasswordEntered.equals(databasePassword)) {
				
				//set request attribute incorrectOldPassword to true
				incorrectOldPassword=true;
				
				req.setAttribute("incorrectPassword",incorrectOldPassword);
				
				//head back to page
				req.getRequestDispatcher("PersonalDetails.jsp");
				
			} else {
			
			
			UserDAO.getUserByStaffID(staffID).setPassword(staffNewPassword);
			
		} 
			
			}
		
		

		req.getRequestDispatcher("personalDetails").forward(req, res);
		
		
	}

}
