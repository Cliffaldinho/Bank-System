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
				String theIncident = "Handle";
				theIncident = theIncident.concat(Integer.toString(i));
				theIncidentChosen=req.getParameter(theIncident);
			
				if(theIncidentChosen!=null) {
					theIndexOfIncident=i;
					break;
				}
				
				theIncident = "close".concat(theIncident);
				String incidentDeleted = req.getParameter(theIncident);
				
				if (incidentDeleted!=null) {
					index=i;
					IncidentDatabase.getIncidentsList().remove(i);
					String path = getServletContext().getRealPath("./saves/incidents.dat");
					FileOutputStream fout = new FileOutputStream(getServletContext().getRealPath("./saves/incidents.dat"));
					ObjectOutputStream oout = new ObjectOutputStream(fout);
					oout.writeObject(IncidentDatabase.getIncidentsList());
					oout.close();
					fout.close();
					req.getRequestDispatcher("ListOfIncidents.jsp").forward(req, res);
					return;
				}
			}

			out.println(theIndexOfIncident);
			
			req.setAttribute("indexForAssignStaff", theIndexOfIncident);
			
			req.getRequestDispatcher("AssignStaffToIncident.jsp").forward(req, res);
			
		}
		

	}
}
