package data;

import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import java.io.*;
import java.util.*;


//filters the view for ListOfIncidents.jsp
//receives from pretty much anywhere. Whenever want to go to ListOfIncidents.jsp, go through this servlet.
@WebServlet(urlPatterns={"/prepareList"})
public class PrepareListServlet extends HttpServlet {
	
	
	//have this method because the href in web pages can't go to doPost
	//DisplayIncidentReport.jsp, AssignStaffToIncident.jsp, PerformAnalysis.jsp, (UpdateIncident.jsp), all go through here
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException {
		
		
		HttpSession aSession = req.getSession();
		
		//resets list to be printed
		ArrayList<IncidentBean> printList = new ArrayList<IncidentBean>();
		
		//the list to be printed is gotten from database
		printList=IncidentDAO.getIncidentsList();
		
		//set attribute
		aSession.setAttribute("listOfIncidents", printList);
		
		//forward to list
		req.getRequestDispatcher("ListOfIncidents.jsp").forward(req, res);
		
	}

	
	//search and sort and refresh all go through here
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
		PrintWriter out = res.getWriter();
		
		HttpSession aSession = req.getSession();
		
		//makes new list to be printed
		ArrayList<IncidentBean> printList = new ArrayList<IncidentBean>();
		
		//gets the session attribute to see if search or sort is true
		boolean isSearch,isSort;
		isSearch=(boolean) aSession.getAttribute("isSearch");
		isSort = (boolean) aSession.getAttribute("isSort");
		
		
		//if search is true
		if(isSearch==true) {
			
		//get indexes of searched list
		ArrayList<Integer> indexList = (ArrayList<Integer>) aSession.getAttribute("listOfSearchIndexes");
		
		//traverse through index of searched list
		for(int i=0;i<indexList.size();i++) {
			
			//get the IncidentBean corresponding to that index
			IncidentBean storeIncident = IncidentDAO.getIncidentByIncidentID(indexList.get(i));
			
			//add it to the list to be printed
			printList.add(storeIncident);	
		}
		
		//reset search attribute as false
		aSession.setAttribute("isSearch", false);
		
		//remove listOfSearchIndexes attribute
		aSession.removeAttribute("listOfSearchIndexes");
		
		//if sort is true
		} else if (isSort==true) {
		
		//get indexes of sorted list
		ArrayList<Integer> indexList = (ArrayList<Integer>) aSession.getAttribute("sortReportsIndexes");
		
		//traverse through index of sorted list
		for(int i=0;i<indexList.size();i++) {
			
			//get the IncidentBean corresponding to that index
			IncidentBean storeIncident = IncidentDAO.getIncidentByIncidentID(indexList.get(i));
			
			//add it to the list to be printed
			printList.add(storeIncident);	
		}
		
		//reset sort attributes as false
		aSession.setAttribute("isSort", false);
		
		//remove sortReportsIndexes
		aSession.removeAttribute("sortReportsIndexes");
		
		//if search and sort are not true
		} else {
			
			//the printed list is the original list
			printList=IncidentDAO.getIncidentsList();
			
			
		}
		
		aSession.setAttribute("listOfIncidents", printList);
		req.getRequestDispatcher("ListOfIncidents.jsp").forward(req, res);
		

		
	}
}
