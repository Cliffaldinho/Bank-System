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

	//receive from ListOfIncidents.jsp. Processes which report was clicked, and which option chosen (view,handle,close)
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException {
		
		PrintWriter out = res.getWriter();
	
	String incidentID = req.getParameter("clicked");
	
	String optionChosen = req.getParameter("chosen");
	

	int id=Integer.parseInt(incidentID);
	IncidentBean incident=IncidentDAO.getIncidentByIncidentID(id);
	
	
	//see which option was chosen for that incident
	boolean viewIncident,closeIncident;
	viewIncident=false;
	closeIncident=false;
	
	switch(optionChosen) {
		case "View":
			viewIncident=true;
			break;
		case "Close":
			closeIncident=true;
			break;
		default:
			System.out.println("Error: No user choice received for DisplayIncidentReportServlet.");
			break;
	}
	
	HttpSession aSession = req.getSession();
	
	
	if(viewIncident==true) {
		
		//set the session attribute incidentID and IncidentBean, for that incident.
		//For use in Display Report, Analysis, and Strategy jsps and servlets
		aSession.setAttribute("incidentID", id);
		aSession.setAttribute("incidentSelected", incident);
		
		req.getRequestDispatcher("DisplayIncidentReport.jsp").forward(req, res);
		
	}  else if (closeIncident==true) {
		
		IncidentDAO.deleteIncidentByIncidentID(id);
		
		req.getRequestDispatcher("prepareList").forward(req, res);
	}
	
	

	}
	
}
