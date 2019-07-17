package data;

import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns={"/detectDuplicate"})
public class DetectDuplicateServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException {
		int theDuplicateListSize=IncidentDatabase.getDuplicatesList().size();
		int lastIndex=theDuplicateListSize-1;
		int original=IncidentDatabase.getDuplicatesList().get(lastIndex).getOriginalIndexForDuplicateChecking();
		req.setAttribute("indexOfIncident", original);
		IncidentDatabase.getDuplicatesList().get(lastIndex).setDuplicateCheckInProcess(true);
		req.getRequestDispatcher("DisplayIncidentReport.jsp").forward(req,res);

		/*int duplicateListSize;
		duplicateListSize=IncidentDatabase.getDuplicatesList().size();
		int mostRecentIndex;
		mostRecentIndex=duplicateListSize-1;
		
		//add to Original List
		IncidentDatabase.getIncidentsList().add(IncidentDatabase.getDuplicatesList().get(mostRecentIndex));
		
		//delete from Duplicate List
		IncidentDatabase.getDuplicatesList().remove(mostRecentIndex);
		
		req.getRequestDispatcher("ListOfIncidents.jsp").forward(req, res);*/
	}
}
