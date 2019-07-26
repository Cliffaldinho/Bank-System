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


@WebServlet(urlPatterns={"/defineRolesForStaff"})
public class DefineStaffRolesServlet extends HttpServlet {

	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException {
		
		PrintWriter out = res.getWriter();
		String userID = req.getParameter("userChosen");
		
		//Integer id=Integer.parseInt(userID);
		UserBean user = UserDAO.getUserByStaffID(userID);
		
		//out.println(user.getPosition().toString());
		
		String action = req.getParameter("actionChosen");
		
		//out.println(userID+" "+action);
		
		switch(action) {
		
		case "Modify":
			HttpSession aSession = req.getSession();
			aSession.setAttribute("userID", userID);
			aSession.setAttribute("userSelected", user);
			
			//out.println(aSession.getAttribute("userID"));
			//UserBean aUser= (UserBean) aSession.getAttribute("userSelected");
			//out.println(aUser.getName());
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
