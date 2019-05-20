package data;

import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns={"/countIncidentReport"})
public class CountIncidentReportsServlet extends HttpServlet{
	ArrayList<Incident> incidentReports = null;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		ArrayList<Incident> incidentReports = IncidentDatabase.getIncidentsList();
    int count = 0;

    for(Incident i : incidentReports)
    {
      count++;
    }

    req.setAttribute("countReports", count);
    req.getRequestDispatcher("ListOfIncidents.jsp").forward(req, res);

	}
}
