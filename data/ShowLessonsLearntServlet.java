package data;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns={"/lessonsLearnt"})
public class ShowLessonsLearntServlet extends HttpServlet{

	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException {
		
		Simulation aSimulation = new Simulation();
		
		String theDate=req.getParameter("date");
		String theCause=req.getParameter("targetCause");
		String theActions=req.getParameter("actions");
		String theResults=req.getParameter("results");
		
		aSimulation.setDate(theDate);
		aSimulation.setRootCauseTargeted(theCause);
		aSimulation.setActionsTaken(theActions);
		aSimulation.setResultsFound(theResults);
		
		int theIncidentListIndex,theAnalysisListIndex;
		String tempIncidentListIndex,tempAnalysisListIndex;
		
		tempIncidentListIndex=req.getParameter("anIncidentDatabaseIndex");
		tempAnalysisListIndex=req.getParameter("anAnalysisDatabaseIndex");
		
		theIncidentListIndex=Integer.parseInt(tempIncidentListIndex);
		theAnalysisListIndex=Integer.parseInt(tempAnalysisListIndex);
		
		
		
		String solution=IncidentDatabase.getIncidentsList().get(theIncidentListIndex).getPossibleSolutionsOfIncident();
		boolean checkIfHasAlphabets;
		checkIfHasAlphabets=solution.matches(".*[a-zA-Z]+.*");
		String updateSolution;
		
		if(checkIfHasAlphabets==true) {
			updateSolution=solution+" "+theActions;
		} else {
			updateSolution=theActions;
		}
		
		/**
		AnalysisDatabase.getAnalysisList().get(analysisDatabaseIndex).setRootCauseOfIncident(updateRootCause);
		//out.println(AnalysisDatabase.getAnalysisList().get(0).getRootCauseOfIncident());
		
		IncidentDatabase.getIncidentsList().get(incidentDatabaseIndex).setPossibleCausesOfIncident(updateRootCause);
		*/
		
		AnalysisDatabase.getAnalysisList().get(theAnalysisListIndex).getListOfSimulations().add(aSimulation);
		
		IncidentDatabase.getIncidentsList().get(theIncidentListIndex).setPossibleSolutionsOfIncident(updateSolution);
		
		req.setAttribute("incidentDatabaseIndex", theIncidentListIndex);
		req.setAttribute("analysisDatabaseIndex",theAnalysisListIndex);
		req.getRequestDispatcher("PerformAnalysis.jsp").forward(req, res);
		
	}
}
