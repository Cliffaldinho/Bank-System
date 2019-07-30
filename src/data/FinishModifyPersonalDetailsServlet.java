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

//sets the personal details of logged in user
//receives from PersonalDetails.jsp
@WebServlet(urlPatterns={"/finishPersonalDetails"})
public class FinishModifyPersonalDetailsServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException {
		
		PrintWriter out = res.getWriter();
		
		HttpSession aSession = req.getSession();
		
		//get the StaffBean of user currently logged in
		StaffBean staffLoggedIn = (StaffBean) aSession.getAttribute("logAuth");
		String staffID=staffLoggedIn.getUsername();
		
		//get the name parameter
		String staffNewName=req.getParameter("modifyName");
		
		//set it if it's not null or not empty
		if(!staffNewName.isBlank()) {
		
		UserDAO.getUserByStaffID(staffID).setName(staffNewName);
		}
		
		//get the address parameter
		String staffNewAddress=req.getParameter("modifyAddress");
		
		//set it if it's not null or not empty
		if(!staffNewAddress.isBlank()) {
		UserDAO.getUserByStaffID(staffID).setAddress(staffNewAddress);
		}
		
		String staffNewContact=req.getParameter("modifyContact");
		
		//set it if it's not null or not empty
		if(!staffNewContact.isBlank()) {
			UserDAO.getUserByStaffID(staffID).setContactNumber(staffNewContact);
		}
		
		//get the password parameter
		String staffNewPassword=req.getParameter("modifyPassword");
		
		//if pass client side password validation: All three password fields entered, New password and reentered new password same
		if(!staffNewPassword.isBlank()) {
			//then do server side password validation: Check if old password is entered correctly
			
			
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
			
			//if old password user entered is same as password in database
			//store new password that user entered
			UserDAO.getUserByStaffID(staffID).setPassword(staffNewPassword);
			
		} 
			
			}
		
		
		//forward to ModifyPersonalDetailsServlet (which will then go to the PersonalDetails.jsp page, and print updated info)
		req.getRequestDispatcher("personalDetails").forward(req, res);
		
		
	}

}
