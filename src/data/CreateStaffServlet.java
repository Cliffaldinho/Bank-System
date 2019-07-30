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

//receives from CreateUser.jsp
@WebServlet(urlPatterns={"/createUser"})
public class CreateStaffServlet extends HttpServlet {

	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException {
		
		PrintWriter out = res.getWriter();

		//new UserBean object
		UserBean tempUser = new UserBean();
		
		//set new user's name
			tempUser.setName(req.getParameter("name"));
		 
			//set new user's contact number
			tempUser.setContactNumber(req.getParameter("contact"));

			//set new user's address
			tempUser.setAddress(req.getParameter("address"));
			
			//get the position from CreateUser.jsp
			String positionValue =req.getParameter("position");
			
			//convert it to a UserBean.Position type
			UserBean.Position staffPosition=UserBean.Position.valueOf(positionValue);
			
			//set new user's position
			tempUser.setPosition(staffPosition);
			
			//get the array of roles from CreateUser.jsp
			String[] receivedRoles =  req.getParameterValues("roles");
			//if no roles was checked, then array is null
			//if one role was checked, then array  length is 1
			//if two roles were checked, then array length is 2
			
			//intialize a string to store total string, and a string to append input
			String total="";
			String append="";
			
			//see if checkbox checked, so that won't get index out of bounds error
			if(receivedRoles!=null) {
				
				//a for loop that goes through the array of roles received. 
				for(int i=0;i<receivedRoles.length;i++) {
					
					//append each role received to the total string
					append=receivedRoles[i];
					total=total+append+". ";
				}
			}
			
			//set the roles for new user
			tempUser.setRolesToDo(total);
	
		//add new user
		UserDAO.addUsers(tempUser);

		//forward to RolesForStaff.jsp
		req.getRequestDispatcher("RolesForStaff.jsp").forward(req, res);
	}
}
