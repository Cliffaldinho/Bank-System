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

@WebServlet(urlPatterns={"/displayIncidentReport"})
public class DisplayIncidentReportServlet extends HttpServlet{

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException {
		
		PrintWriter out = res.getWriter();
		
		String tempIncidentChosen;
	
		int incidentID=-1;
		boolean viewIncident,handleIncident,closeIncident;//,checkDuplicate;
		viewIncident=false;
		handleIncident=false;
		closeIncident=false;
		
		HttpSession aSession = req.getSession();
		//String incidentDisplayStatus=(String) aSession.getAttribute("incidentDisplayStatus");
		
		IncidentBean printIncident = new IncidentBean();

		
		//Loops through all incidents in database
		outerloop:
		for(int i=0;i<IncidentDAO.getIncidentsList().size();i++) {
			
			//uses the incident IDs in database as parameter
			String storeParameter = Integer.toString(IncidentDAO.getIncidentsList().get(i).getIncidentID());

			tempIncidentChosen = req.getParameter(storeParameter);

			//checks if that particular parameter was clicked
			if(tempIncidentChosen!=null) {
				
				//if it was clicked, check which option was chosen
				innerloop:
				switch(tempIncidentChosen) {
				
				case "View Incident":
					viewIncident=true;
					break innerloop;
				
				case "Handle Incident":
					handleIncident=true;
					break innerloop;
					
				case "Close Incident":
					closeIncident=true;
					break innerloop;
					
				default:
					System.out.println("Error: No user choice received for DisplayIncidentReportServlet.");
					break innerloop;
				}
				
				
				
				//get the id of the incident clicked
				incidentID=IncidentDAO.getIncidentsList().get(i).getIncidentID();
				
				//get the incident chosen
				printIncident=IncidentDAO.getIncidentByIncidentID(incidentID);
				break outerloop;
			}
		}
	
		
		if(viewIncident==true||handleIncident==true) {
		aSession.setAttribute("incidentID", incidentID);
		aSession.setAttribute("incidentSelected", printIncident);
		}
	
		
		if(viewIncident==true) {
			req.getRequestDispatcher("DisplayIncidentReport.jsp").forward(req, res);
		} else if (handleIncident==true) {

			req.getRequestDispatcher("AssignStaffToIncident.jsp").forward(req, res);
		} else if (closeIncident==true) {
			IncidentDAO.deleteIncidentByIncidentID(incidentID);
			req.getRequestDispatcher("prepareList").forward(req, res);
		}
		
		


	}
	
}
