package data;

import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import java.io.*;
import java.util.*;

@WebServlet(urlPatterns={"/autoAssign"})
public class AutoAssignServlet extends HttpServlet {

	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException {
		
		PrintWriter out = res.getWriter();
		
		HttpSession aSession = req.getSession();
		
		int incidentID = (int) aSession.getAttribute("incidentID");
		
		IncidentDAO.getIncidentByIncidentID(incidentID).autoAssignStaff();
		
		req.getRequestDispatcher("prepareList").forward(req,res);
		
	
		
	}
}
