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
		String staffSelected;
		
		int staffIndex=-1;
		
		for(int i=0;i<UserDatabase.getUsersList().size();i++) {
			
			String Staff = "Staff";
			Staff = Staff.concat(Integer.toString(i));
			staffSelected=req.getParameter(Staff);
			
			if(staffSelected!=null) {
				staffIndex=i;
				break;
			}
			
			Staff="delete".concat(Staff);
			String staffDeleted = req.getParameter(Staff);
			
			if (staffDeleted!=null) {
				staffIndex = i;
				UserDatabase.getUsersList().remove(i);
				String path = getServletContext().getRealPath("./saves/users.dat");
				FileOutputStream fout = new FileOutputStream(getServletContext().getRealPath("./saves/users.dat"));
				ObjectOutputStream oout = new ObjectOutputStream(fout);
				oout.writeObject(UserDatabase.getUsersList());
				oout.close();
				fout.close();
				req.getRequestDispatcher("RolesForStaff.jsp").forward(req, res);
				return;
				
			}
		}
		
		req.setAttribute("indexOfStaff", staffIndex);
		req.getRequestDispatcher("SetStaffRole.jsp").forward(req, res);
		//req.setAttribute("indexOfIncident", index);
		//req.getRequestDispatcher("DisplayIncidentReport.jsp").forward(req, res);
		
	}
	
}
