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
		

		String positionValue =req.getParameter("position");
		
		UserBean.Position staffPosition=UserBean.Position.valueOf(positionValue);
		
		UserDAO.getUserByStaffID(userID).setPosition(staffPosition);
		
		
		req.getRequestDispatcher("RolesForStaff.jsp").forward(req, res);
		
		
	}
}
