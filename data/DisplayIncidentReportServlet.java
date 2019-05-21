package data;

import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns={"/displayIncidentReport"})
public class DisplayIncidentReportServlet extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException {
		
		
		PrintWriter out = res.getWriter();
		
		String tempIncidentChosen;
		//String tempIncidentChosen = req.getParameter("incidentMarker");
		//String searchIncident;
		//String incidentChosen=tempIncidentChosen.substring(4);
		//int indexIncidentChosen=Integer.parseInt(incidentChosen);
		int index=-1;
		
		for(int i=0;i<IncidentDatabase.getIncidentsList().size();i++) {
			//searchIncident="Show"+i;
			//out.println("searchIncident is "+searchIncident);
			
			tempIncidentChosen=req.getParameter("Show"+i);
			if(tempIncidentChosen!=null) {
				index=i;
				break;
			}
			
			
		}
		
		//out.println(index);
		
		if(index!=-1) {
			req.setAttribute("indexOfIncident", index);
			req.getRequestDispatcher("DisplayIncidentReport.jsp").forward(req, res);
		} else {
			
			//PrintWriter out =res.getWriter();
			String theIncidentChosen;
			
			int theIndexOfIncident=-1;
			
			for(int i=0;i<IncidentDatabase.getIncidentsList().size();i++) {
				
				theIncidentChosen=req.getParameter("Handle"+i);
			
				if(theIncidentChosen!=null) {
					theIndexOfIncident=i;
					break;
				}
						
			}

			out.println(theIndexOfIncident);
			
			req.setAttribute("indexForAssignStaff", theIndexOfIncident);
			
			req.getRequestDispatcher("AssignStaffToIncident.jsp").forward(req, res);
			
		}
		

	}
}
