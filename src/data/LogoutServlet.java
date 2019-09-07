package data;

import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;

import javax.swing.JOptionPane;
import javax.swing.JOptionPane.*;

//receives from UserLogin.jsp


@WebServlet(urlPatterns={"/userLogout"})
public class LogoutServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		HttpSession aSession = req.getSession(true);
		StaffBean logAuth = (StaffBean) aSession.getAttribute("logAuth");
		logAuth.setUsername(null); 
		req.setAttribute("loginError",null); //is String
		
		//removes all the session attributes
		
		//this was to filter search or sort in the searchServlet,sortServlet, and prepareListServlet
		aSession.removeAttribute("isSearch");
		aSession.removeAttribute("isSort");
		//initialized at the start
		
		//this was to store a list of incidents (could be searched list, or sort list, or full list)
		//the view is changed by prepareListServlet. Then ListOfIncidents.jsp only prints out the list attribute
		aSession.removeAttribute("listOfIncidents");
		aSession.removeAttribute("archivedList");
		
		//this was to store the list of staff 
		//(was used in all the user servlets/jsps where needed to view full list of users)
		//i.e. RolesForStaff.jsp etc, and their servlets
		aSession.removeAttribute("listOfStaff");
		//initialized at the start
		
		//this was to store the incident ID, and the IncidentBean object.
		//so that can getIncidentTitle, (i.e. ${incidentSelected.incidentTitle}) etc
		//or can set stuff based on IncidentDAO.getIncidentByID(incidentID)
		aSession.removeAttribute("incidentID");
		aSession.removeAttribute("incidentSelected");
		//was initialized in DisplayIncidentReportServet
		//then used throughout the Display, Analysis, and Strategy, of that incident
		
		//this was to store the user ID and UserBean 
		//for use in ocassions when want to select a user to perform an action
		//i.e. modify user etc
		aSession.removeAttribute("userID");
		aSession.removeAttribute("userSelected");
		
		//this was to store the logged in user name, address, and contact
		//in the rounds of ModifyPersonalDetailsServlet, PersonalDetails.jsp, FinishModifyPersonalDetailsServlet
		aSession.removeAttribute("staffName");
		aSession.removeAttribute("staffAddress");
		aSession.removeAttribute("staffContact");
		
		//this was used to store statistics stuff
		aSession.removeAttribute("stats");
		
		req.getRequestDispatcher("index.jsp").forward(req,res);
	}
	

	
	
} 
