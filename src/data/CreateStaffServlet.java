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

		
		UserBean tempUser = new UserBean();
		
			tempUser.setName(req.getParameter("name"));
	
			tempUser.setContactNumber(req.getParameter("contact"));

			tempUser.setAddress(req.getParameter("address"));
			
			
			String positionValue =req.getParameter("position");
			
			UserBean.Position staffPosition=UserBean.Position.valueOf(positionValue);
			
			tempUser.setPosition(staffPosition);
			

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
			tempUser.setRolesToDo(total);
	
		
		UserDAO.addUsers(tempUser);

		req.getRequestDispatcher("RolesForStaff.jsp").forward(req, res);
	}
}
