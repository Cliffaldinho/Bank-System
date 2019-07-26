package data;

import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns={"/finishAssignStaff"})
public class FinishAssignStaffServlet extends HttpServlet {

	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException {
		
		PrintWriter out = res.getWriter();
		
		String staffID=req.getParameter("clicked");
		out.println(staffID);
		//Integer userID=Integer.parseInt(staffID);
		
		int incidentID;
		HttpSession aSession = req.getSession();
		incidentID = (int) aSession.getAttribute("incidentID");
		
		IncidentDAO.getIncidentByIncidentID(incidentID).setAssignedStaffID(staffID);
		
		req.getRequestDispatcher("prepareList").forward(req, res);
	
	}
}
