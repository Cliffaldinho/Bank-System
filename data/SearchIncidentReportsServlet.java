package data;

import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns={"/searchIncidentReport"})
public class DisplayIncidentReportServlet extends HttpServlet{
	ArrayList<Incident> incidentReports = null;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		ArrayList<Incident> incidentReports = IncidentDatabase.getIncidentsList();
		ArrayList<Integer> searchReturn = new ArrayList<Integer>();
		String searchTopic = req.getParameter("searchTopic");
		String search = req.getParameter("search");
		int index = 0;

		switch(searchTopic)
		{
			case "incident":
											for(Incident i : incidentReports)
											{
												if(i.getIncidentTitle().equals(search))
												{
													searchReturn.add(index);
												}
												index++;
											}
											break;
			case "rootCause":
												for(Incident i : incidentReports)
												{
													if(i.getPossibleCausesOfIncident().contains(search))
													{
														searchReturn.add(index);
													}
													index++;
												}
												break;
			case "keywords":
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
											break;
			case "category":
											for(Incident i : incidentReports)
											{
												Category categories = i.getIncidentCategory();
												for(Category c : categories)
												{
													if(c.toString().equals(search))
													{
														searchReturn.add(index);
														break;
													}
												}
												index++;
											}
											break;
			default:
							break;
		}

		req.setAttribute("listOfSearchIndexes", searchReturn);
		req.getRequestDispatcher("ViewListOfIncidents.jsp").forward(req, res);

	}
}
