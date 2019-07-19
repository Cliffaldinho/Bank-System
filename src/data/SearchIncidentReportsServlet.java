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
	ArrayList<IncidentBean> incidentReports = null;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		ArrayList<IncidentBean> incidentReports = IncidentDAO.getIncidentsList();
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
													searchReturn.add(index);
												}
												index++;
											}
											break;
			case "RootCause":
												for(IncidentBean i : incidentReports)
												{
													if(i.getPossibleCausesOfIncident().contains(search))
													{
														searchReturn.add(index);
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
														searchReturn.add(index);
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
													searchReturn.add(index);
												}
												index++;
											}
											break;
			default:
							for(IncidentBean i : incidentReports)
							{
								searchReturn.add(index);
								index++;
							}
							break;
		}

		req.setAttribute("listOfSearchIndexes", searchReturn);
		req.getRequestDispatcher("ListOfIncidents.jsp").forward(req, res);

	}
}
