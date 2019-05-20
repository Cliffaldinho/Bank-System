package data;

import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;


@WebServlet(urlPatterns={"/defineRolesForStaff"})
public class DefineStaffRolesServlet extends HttpServlet {

	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException {
		PrintWriter out = res.getWriter();
		String staffSelected;
		
		int staffIndex=-1;
		for(int i=0;i<UserDatabase.getUsersList().size();i++) {
			
			staffSelected=req.getParameter("Staff"+i);
			
			if(staffSelected!=null) {
				staffIndex=i;
				break;
			}
		}
		
		//out.println(staffIndex);
		
		req.setAttribute("indexOfStaff", staffIndex);
		req.getRequestDispatcher("SetStaffRole.jsp").forward(req, res);
		//req.setAttribute("indexOfIncident", index);
		//req.getRequestDispatcher("DisplayIncidentReport.jsp").forward(req, res);
		
	}
	
}
