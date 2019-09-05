package data;

import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;

//display saved statistics data
//used by any buttons that would open the statistics page
//goes to prepareListServlet
@WebServlet(urlPatterns={"/viewStatistics"})
public class ViewStatisticsServlet extends HttpServlet{

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		HttpSession aSession = req.getSession();
		PrintWriter out = res.getWriter();

		//set the attributes for that session
//		aSession.setAttribute("isSearch", true); // no need?
	
		//update all statistics data

		int userCounter;
		double averageFinishTime;//variable to hold "average time things get done" 
		double quickestFinishTime;//Shortest time things get done
		double slowestFinishTime;//Longest time things get done
		String peakTimeOfIssue;//Peak time of issues, i.e. midnight, early morning, noon, etc
		int regulatoryLawIncidentCount;//Each category's incident count
		int cyberSecurityIncidentCount;//Each category's incident count
		int humanIssuesIncidentCount;//Each category's incident count
		int bankEquipmentIncidentCount;//Each category's incident count
		int bankAlgorithmsIncidentCount;//Each category's incident count
		int otherIncidentCount;//Each category's incident count
		
		
		


	}
}
