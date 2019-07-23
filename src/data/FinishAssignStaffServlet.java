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
		
		PrintWriter out = res.getWriter();
		
		String tempStaffChosen;
		String staffID="";
		
		for(int i=0;i<UserDAO.getUsersList().size();i++) {
			
			String storeParameter = UserDAO.getUsersList().get(i).getStaffID();
			
			tempStaffChosen = req.getParameter(storeParameter);
			
			if(tempStaffChosen!=null) {
				
				staffID = storeParameter;
				//out.println("StaffID in for loop is" + staffID);
				break;
				
				
			}
		}

		int incidentID;
		HttpSession aSession = req.getSession();
		incidentID = (int) aSession.getAttribute("incidentID");
		
		IncidentDAO.getIncidentByIncidentID(incidentID).setAssignedStaffID(staffID);
		//out.println("Incident ID" + incidentID + " has "+IncidentDAO.getIncidentByIncidentID(incidentID).getAssignedStaffID()+ " assigned.");
		
		req.getRequestDispatcher("prepareList").forward(req, res);
		
	
	}
}
