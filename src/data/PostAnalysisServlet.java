package data;

import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns={"/postAnalysis"})
public class PostAnalysisServlet extends HttpServlet {

	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException {
		
		PrintWriter out = res.getWriter();
		//out.println("one");
		String tempAnalysis,tempIncident;
		tempAnalysis=req.getParameter("theAnalysisDatabaseIndex");
		tempIncident=req.getParameter("theIncidentDatabaseIndex");
		
		int analysisDatabaseIndex,incidentDatabaseIndex;
		analysisDatabaseIndex=Integer.parseInt(tempAnalysis);
		incidentDatabaseIndex=Integer.parseInt(tempIncident);
		
		
		//get from form
		String tempStaffName,tempStaffID,tempRootCause;
		int userIndex=-1;
		
		tempStaffName=req.getParameter("staffNameWriting");
		tempStaffID =req.getParameter("staffIDWriting");
		tempRootCause=req.getParameter("causes");
		
		String databaseID;
		for(int i=0;i<UserDatabase.getUsersList().size();i++) {
			
			databaseID=UserDatabase.getUsersList().get(i).getStaffID();
			
			if(databaseID.equalsIgnoreCase(tempStaffID)) {
				userIndex=i;
				break;
			}
		}
		
		//out.println("half finished");
		
		if(userIndex==-1) {
			out.println("User not found");
		} 
		
		else if (tempRootCause==null) {
			out.println("No root cause entered");
		}
		
		else {
		out.println("nearly finished");
		
		//Add user that reported it
		AnalysisDatabase.getAnalysisList().get(analysisDatabaseIndex).getUsers().add(UserDatabase.getUserByIndex(userIndex));
		//out.println(AnalysisDatabase.getAnalysisList().get(analysisDatabaseIndex).getUsers().get(0).getName());
		
		
		
		String cause= IncidentDatabase.getIncidentsList().get(incidentDatabaseIndex).getPossibleCausesOfIncident();
		boolean checkIfHasAlphabets;
		checkIfHasAlphabets=cause.matches(".*[a-zA-Z]+.*");
		String updateRootCause;
		if(checkIfHasAlphabets==true) {
			updateRootCause = cause+" "+tempRootCause;
		} else {
			updateRootCause=tempRootCause;
		}
	
		AnalysisDatabase.getAnalysisList().get(analysisDatabaseIndex).setRootCauseOfIncident(updateRootCause);
		//out.println(AnalysisDatabase.getAnalysisList().get(0).getRootCauseOfIncident());
		
		IncidentDatabase.getIncidentsList().get(incidentDatabaseIndex).setPossibleCausesOfIncident(updateRootCause);
		
		
		req.getRequestDispatcher("ListOfIncidents.jsp").forward(req, res);
		}
		
		//out.println("Incident Database Index is "+incidentDatabaseIndex);
		//out.println("Analysis Database Index is "+analysisDatabaseIndex);
		
	}
}
