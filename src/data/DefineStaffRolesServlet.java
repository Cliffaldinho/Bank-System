package data;

import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;


//receives from RolesForStaff.jsp (which is branch manager page only)
@WebServlet(urlPatterns={"/defineRolesForStaff"})
public class DefineStaffRolesServlet extends HttpServlet {

	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException {
		
		PrintWriter out = res.getWriter();
		
		//obtain the user id of the staff that branch manager clicked
		String userID = req.getParameter("userChosen");
		
		//get a UserBean from that user id
		UserBean user = UserDAO.getUserByStaffID(userID);
		
		//obtain the selected action that branch manager want to do to that user (Modify,Delete)
		String action = req.getParameter("actionChosen");
		
		//cases for action
		switch(action) {
		
		//if branch manager selected modify
		case "Modify":
			
			HttpSession aSession = req.getSession();
			
			//set the selected staff's userID and UserBean object, as session attributes
			//for use in SetStaffRole.jsp, and FinishSetStaffRoleServlet
			aSession.setAttribute("userID", userID);
			aSession.setAttribute("userSelected", user);
			
			//forward to SetStaffRole.jsp
			req.getRequestDispatcher("SetStaffRole.jsp").forward(req, res);
			break;
			
		//if branch manager selected delete
		case "Delete":
			
			//delete the user 
			UserDAO.deleteUserByUserID(userID);
			
			//forward to RolesForStaff.jsp
			req.getRequestDispatcher("RolesForStaff.jsp").forward(req, res);
			break;
			
			
			
			
			//error message if none of choices were selected
		default:
			System.out.println("Error: No action received for DefineStaffRoleServlet, from RolesForStaff.jsp");
			break;
		}
		

		
	}
	
}
