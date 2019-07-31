package data;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

//this sets the startegy that branch manager implemented
//receives from ImplementStrategy.jsp
@WebServlet(urlPatterns={"/implementStrategy"})
public class ImplementStrategyServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException {
		
		PrintWriter out = res.getWriter();
		
		HttpSession aSession = req.getSession();
		
		//Get incident ID and IncidentBean session attribute for that particular incident
		//(Was set in DisplayIncidentReportServlet)
		IncidentBean incident = (IncidentBean) aSession.getAttribute("incidentSelected");
		int id = (int) aSession.getAttribute("incidentID");
		
		String riskForeseen=req.getParameter("Foreseen");
		IncidentDAO.getIncidentByIncidentID(id).getPostIncident().setRiskForeseen(riskForeseen);
		
		String strategyImplemented=req.getParameter("Implement");
		IncidentDAO.getIncidentByIncidentID(id).getPostIncident().setStrategyImplemented(strategyImplemented);
		
		String likelihoodValue=req.getParameter("Likelihood");
		int likelihood=Integer.parseInt(likelihoodValue)+1;	// is +1 here because in the forEach loop, index is 0,1,2,3,4. Whereas want 1,2,3,4,5.
		
		String consequenceValue=req.getParameter("Consequences");
		int consequence=Integer.parseInt(consequenceValue)+1;
		
		IncidentDAO.getIncidentByIncidentID(id).getPostIncident().setRiskEvaluation(likelihood, consequence);
		
		//Set the strategy as has been entered = true
		//So that the branch manager can only implement the strategy once
		IncidentDAO.getIncidentByIncidentID(id).getPostIncident().setStrategyImplementedAlready();
	
		req.getRequestDispatcher("DisplayIncidentReport.jsp").forward(req,res);
		
	}

}
