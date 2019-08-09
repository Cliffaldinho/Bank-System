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
		
		String userID = req.getParameter("userChosen");
		
		UserBean user = UserDAO.getUserByStaffID(userID);
		
		String action = req.getParameter("actionChosen");
		
		switch(action) {
		
		case "Modify":
			
			HttpSession aSession = req.getSession();
			
			//for use in SetStaffRole.jsp, and FinishSetStaffRoleServlet
			aSession.setAttribute("userID", userID);
			aSession.setAttribute("userSelected", user);
			
			req.getRequestDispatcher("SetStaffRole.jsp").forward(req, res);
			break;
			
		
		case "Delete":
			
		 
			UserDAO.deleteUserByUserID(userID);
			
			
			req.getRequestDispatcher("RolesForStaff.jsp").forward(req, res);
			break;
			
			
			
			
			
		default:
			System.out.println("Error: No action received for DefineStaffRoleServlet, from RolesForStaff.jsp");
			break;
		}
		

		
	}
	
}
