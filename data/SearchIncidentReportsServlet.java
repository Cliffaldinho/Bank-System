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
	ArrayList<Incident> incidentReports = null;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		ArrayList<Incident> incidentReports = IncidentDatabase.getIncidentsList();
		ArrayList<Integer> searchReturn = new ArrayList<Integer>();
		String searchTopic = req.getAttribute("searchTopic");
		String search = req.getAttribute("search");
		int index = 0;
		int count = 0;

		switch(searchTopic)
		{
			case "Incident":
											for(Incident i : incidentReports)
											{
												if(i.getIncidentTitle().equals(search))
												{
													searchReturn.add(index);
												}
												index++;
											}
											count = searchReturn.size();
											break;
			case "RootCause":
												for(Incident i : incidentReports)
												{
													if(i.getPossibleCausesOfIncident().contains(search))
													{
														searchReturn.add(index);
													}
													index++;
												}
												count = searchReturn.size();
												break;
			case "Keywords":
											for(Incident i : incidentReports)
											{
												String[] keywords = i.getIncidentKeywords();
												for(String k : keywords)
												{
													if(k.equals(search))
													{
														searchReturn.add(index);
														break;
													}
												}
												index++;
											}
											count = searchReturn.size();
											break;
			case "Category":
											for(Incident i : incidentReports)
											{
												Incident.Category category = i.getIncidentCategory();
												if(category.toString().equals(search))
												{
													searchReturn.add(index);
												}
												index++;
											}
											count = searchReturn.size();
											break;
			default:
							for(Incident i : incidentReports)
							{
								searchReturn.add(index);
								index++;
							}
							count = searchReturn.size();
							break;
		}

		req.setAttribute("SearchCount", count);
		req.setAttribute("listOfSearchIndexes", searchReturn);
		req.getRequestDispatcher("ListOfIncidents.jsp").forward(req, res);

	}
}
