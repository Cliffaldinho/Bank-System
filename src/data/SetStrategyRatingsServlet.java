package data;


import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;

//this sets the rating of an incident's strategy
@WebServlet(urlPatterns={"/setRatings"})
public class SetStrategyRatingsServlet extends HttpServlet {

	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException {
		PrintWriter out = res.getWriter();
		
		HttpSession aSession = req.getSession();
		
		//gets the IncidentBean and incidentID of that incident
		IncidentBean incident = (IncidentBean) aSession.getAttribute("incidentSelected");
		int id = (int) aSession.getAttribute("incidentID");
		
		//increases the amount of ratings received by 1
		IncidentDAO.getIncidentByIncidentID(id).getPostIncident().setAmountOfRatingsReceived();
		//so that when individual ratings are set, they can be averaged accordingly
		
		//set overall rating
		String overallValue = req.getParameter("Overall");
		double overall=Double.parseDouble(overallValue) + 1;
		IncidentDAO.getIncidentByIncidentID(id).getPostIncident().setRatingOverall(overall);
		
		//set effectiveness rating
		String effectivenessValue = req.getParameter("Effectiveness");
		double effectiveness = Double.parseDouble(effectivenessValue)+1;
		IncidentDAO.getIncidentByIncidentID(id).getPostIncident().setRatingEffectiveness(effectiveness);
		
		//set improvement rating
		String improvementValue=req.getParameter("Improvement");
		double improvement = Double.parseDouble(improvementValue)+1;
		IncidentDAO.getIncidentByIncidentID(id).getPostIncident().setRatingImprovementFromSituationBefore(improvement);
		
		//set practical rating
		String practicalValue= req.getParameter("Practical");
		double practical = Double.parseDouble(practicalValue) +1;
		IncidentDAO.getIncidentByIncidentID(id).getPostIncident().setRatingPractical(practical);
		
		//set relevance rating
		String relevanceValue = req.getParameter("Relevance");
		double relevance = Double.parseDouble(relevanceValue)+1;
		IncidentDAO.getIncidentByIncidentID(id).getPostIncident().setRatingRelevanceToIncident(relevance);
		
		//set satisfaction rating
		String satisfactionValue = req.getParameter("Satisfaction");
		double satisfaction = Double.parseDouble(satisfactionValue)+1;
		IncidentDAO.getIncidentByIncidentID(id).getPostIncident().setRatingSatisfactionOfStrategy(satisfaction);
		
		//get the staff who is logged in to this session
		StaffBean user = (StaffBean) aSession.getAttribute("logAuth");
		String userID = user.getUsername();
		
		//set the attribute that they have rated
		//UserDAO.getUserByStaffID(userID).setRatedStrategy();
		
		req.getRequestDispatcher("DisplayIncidentReport.jsp").forward(req,res);

		
		
	}
}
