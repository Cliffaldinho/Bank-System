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

		int index=-1;
		
		//display Incident
		for(int i=0;i<IncidentDAO.getIncidentsList().size();i++) {

			
			tempIncidentChosen=req.getParameter("Show"+i);
			if(tempIncidentChosen!=null) {
				index=i;
				break;
			}
			
			
		}
		
		if(index!=-1) {
			//req.setAttribute("indexOfIncident", index);
			
			//new code
			HttpSession aSession = req.getSession();
			
			//set index for future retrieval in analysis etc
			aSession.setAttribute("indexOfIncident", index);
			
			IncidentBean displayIncident = IncidentDAO.getIncidentsList().get(index);
			
			//for use in DisplayIncidentReport.jsp
			req.setAttribute("incidentTitle", displayIncident.getIncidentTitle());
			req.setAttribute("incidentCategory", displayIncident.getIncidentCategory().toString());
			req.setAttribute("incidentDate", displayIncident.getDateTimeFromTimeStamp());
			req.setAttribute("incidentDescription", displayIncident.getDescriptionOfIncident());
			
			req.setAttribute("incidentKeywords", displayIncident.getIncidentKeywords());
			req.setAttribute("incidentPriority", displayIncident.getPriorityRating().toString());
			req.setAttribute("incidentPossibleCauses", displayIncident.getPossibleCausesOfIncident());
			req.setAttribute("incidentPossibleSolutions", displayIncident.getPossibleSolutionsOfIncident());
			
			User staffReported = displayIncident.getUserReportedIncident();
			req.setAttribute("staffName", staffReported.getName());
			req.setAttribute("staffPosition", staffReported.getPosition());
			req.setAttribute("staffID", staffReported.getStaffID());
			
			boolean checkDuplicate=false;
			req.setAttribute("checkDuplicate", checkDuplicate);
			//end use in displayIncidentReport.jsp
			
			//finish new code
			
			
			req.getRequestDispatcher("DisplayIncidentReport.jsp").forward(req, res);
			
			//Handle Incident
		} else {
			
			String theIncidentChosen;
			
			int theIndexOfIncident=-1;
			
			for(int i=0;i<IncidentDAO.getIncidentsList().size();i++) {
				String theIncident = "Handle";
				theIncident = theIncident.concat(Integer.toString(i));
				theIncidentChosen=req.getParameter(theIncident);
			
				if(theIncidentChosen!=null) {
					theIndexOfIncident=i;
					break;
				}
				
				
				//Delete Incident
				theIncident = "close".concat(theIncident);
				String incidentDeleted = req.getParameter(theIncident);
				
				if (incidentDeleted!=null) {
					index=i;
					IncidentDAO.getIncidentsList().remove(i);
					String path = getServletContext().getRealPath("./saves/incidents.dat");
					FileOutputStream fout = new FileOutputStream(getServletContext().getRealPath("./saves/incidents.dat"));
					ObjectOutputStream oout = new ObjectOutputStream(fout);
					oout.writeObject(IncidentDAO.getIncidentsList());
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
