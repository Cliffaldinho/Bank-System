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
	
	

	int id=Integer.parseInt(incidentID);
	IncidentBean incident=IncidentDAO.getIncidentByIncidentID(id);
	
	
	HttpSession aSession = req.getSession();
	

		
		//For use in Display Report, Analysis, and Strategy jsps and servlets
		aSession.setAttribute("incidentID", id);
		aSession.setAttribute("incidentSelected", incident);
		
		req.getRequestDispatcher("DisplayIncidentReport.jsp").forward(req, res);
		 
	
	

	}
	
}
