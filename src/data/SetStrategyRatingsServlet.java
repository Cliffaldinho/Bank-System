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
		
		
		IncidentBean incident = (IncidentBean) aSession.getAttribute("incidentSelected");
		int id = (int) aSession.getAttribute("incidentID");
		
		//increases the amount of ratings received by 1
		IncidentDAO.getIncidentByIncidentID(id).getPostIncident().setAmountOfRatingsReceived();
		//so that when individual ratings are set, they can be averaged accordingly
		
		String overallValue = req.getParameter("Overall");
		double overall=Double.parseDouble(overallValue) + 1;
		IncidentDAO.getIncidentByIncidentID(id).getPostIncident().setRatingOverall(overall);
		
		String effectivenessValue = req.getParameter("Effectiveness");
		double effectiveness = Double.parseDouble(effectivenessValue)+1;
		IncidentDAO.getIncidentByIncidentID(id).getPostIncident().setRatingEffectiveness(effectiveness);
		
		String improvementValue=req.getParameter("Improvement");
		double improvement = Double.parseDouble(improvementValue)+1;
		IncidentDAO.getIncidentByIncidentID(id).getPostIncident().setRatingImprovementFromSituationBefore(improvement);
		
		String practicalValue= req.getParameter("Practical");
		double practical = Double.parseDouble(practicalValue) +1;
		IncidentDAO.getIncidentByIncidentID(id).getPostIncident().setRatingPractical(practical);
		
		String relevanceValue = req.getParameter("Relevance");
		double relevance = Double.parseDouble(relevanceValue)+1;
		IncidentDAO.getIncidentByIncidentID(id).getPostIncident().setRatingRelevanceToIncident(relevance);
		
		String satisfactionValue = req.getParameter("Satisfaction");
		double satisfaction = Double.parseDouble(satisfactionValue)+1;
		IncidentDAO.getIncidentByIncidentID(id).getPostIncident().setRatingSatisfactionOfStrategy(satisfaction);
		
		
		StaffBean user = (StaffBean) aSession.getAttribute("logAuth");
		String userID = user.getUsername();
		
		IncidentDAO.getIncidentByIncidentID(id).getPostIncident().setStaffWhoRatedStrategy(userID);

		req.getRequestDispatcher("DisplayIncidentReport.jsp").forward(req,res);

		
		
	}
}
