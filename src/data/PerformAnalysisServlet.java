package data;

import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns={"/performAnalysis"})
public class PerformAnalysisServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException {
		
		PrintWriter out = res.getWriter();
		
		int storeIndex;
		String indexParameter;
		
		indexParameter=(String) req.getParameter("indexForAnalysis");
		storeIndex=Integer.parseInt(indexParameter);

	
		//An Analysis object created
		Analysis anAnalysis = new Analysis();
		
		//Added an Incident to the Analysis Object
		anAnalysis.setOneIncident(IncidentDAO.getIncidentsList().get(storeIndex));
		
		//Stored the Analysis in the Analysis database
		AnalysisDatabase.addAnalysis(anAnalysis);
		
		IncidentDAO.getIncidentsList().get(storeIndex).setHasAnalysis(true);
		
		int sizeOfList=AnalysisDatabase.getAnalysisList().size();
		int mostRecentIndex=sizeOfList-1;
		IncidentDAO.getIncidentsList().get(storeIndex).setIndexInAnalysisDatabase(mostRecentIndex);
		
		req.setAttribute("incidentDatabaseIndex",storeIndex);
		req.setAttribute("analysisDatabaseIndex", mostRecentIndex);
		
		req.getRequestDispatcher("PerformAnalysis.jsp").forward(req,res);

	}
	

}
