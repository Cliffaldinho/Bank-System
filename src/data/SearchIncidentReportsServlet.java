package data;

import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns={"/searchIncidentReports"})
public class SearchIncidentReportsServlet extends HttpServlet{

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		//new code
		HttpSession aSession = req.getSession();
		
		PrintWriter out = res.getWriter();

		//set the attributes for that session
		aSession.setAttribute("isSearch", true);

		//finish new code
		
	
		//gets existing list of Incidents (thus allowing users to search then sort or sort then search)
		ArrayList<IncidentBean> incidentReports = (ArrayList<IncidentBean>) aSession.getAttribute("listOfIncidents");
		
		ArrayList<Integer> searchReturn = new ArrayList<Integer>();
		

		String searchTopic = req.getParameter("searchTopic");
		String search = req.getParameter("search");

		
		int index = 0;

		switch(searchTopic)
		{
			case "Incident":
											for(IncidentBean i : incidentReports)
											{
												if(i.getIncidentTitle().equals(search))
												{
													searchReturn.add(i.getIncidentID());
												}
												index++;
											}
											break;
			case "RootCause":
												for(IncidentBean i : incidentReports)
												{
													//if(i.getPossibleCausesOfIncident().contains(search))
													if(i.getPostIncident().getPossibleCausesOfIncident().contains(search))
													{
														searchReturn.add(i.getIncidentID());
													}
													index++;
												}
												break;
			case "Keywords":
											for(IncidentBean i : incidentReports)
											{
												String[] keywords = i.getIncidentKeywords();
												for(String k : keywords)
												{
													if(k.equals(search))
													{
														searchReturn.add(i.getIncidentID());
														break;
													}
												}
												index++;
											}
											break;
			case "Category":
											for(IncidentBean i : incidentReports)
											{
												IncidentBean.Category category = i.getIncidentCategory();
												if(category.toString().equals(search))
												{
													searchReturn.add(i.getIncidentID());
												}
												index++;
											}
											break;
			default:
							for(IncidentBean i : incidentReports)
							{
								searchReturn.add(i.getIncidentID());
								index++;
							}
							break;
		}

		//reset list of search indexes
		aSession.setAttribute("listOfSearchIndexes",searchReturn);
		
		req.getRequestDispatcher("prepareList").forward(req, res);
		


	}
}
