package data;

import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns={"/finishAssignStaff"})
public class FinishAssignStaffServlet extends HttpServlet {

	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException {
		PrintWriter out =res.getWriter();
		String tempIncident;
		
		//tempStaff=req.getParameter("Assign"+i);
		tempIncident=req.getParameter("IncidentIndex");
	
		int staffIndex=-1;
		for(int i=0;i<UserDatabase.getUsersList().size();i++) {
		
			String getStaff=req.getParameter("Assign"+i);
			
			if(getStaff!=null) {
				staffIndex=i;
				break;
			}
		
		
		
		}
		
		//out.println("Staff "+staffIndex);
		//out.println("Incident "+tempIncident);
		int incidentIndex;
		incidentIndex=Integer.parseInt(tempIncident);
		
		IncidentDatabase.getIncidentsList().get(incidentIndex).setIdOfStaffAssigned(UserDatabase.getUsersList().get(staffIndex).getStaffID());
		
		
		
		req.getRequestDispatcher("ListOfIncidents.jsp").forward(req, res);
	
	
	
	}
}
