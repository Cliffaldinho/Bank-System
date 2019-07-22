package data;

import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import java.io.*;
import java.util.*;

@WebServlet(urlPatterns={"/prepareList"})
public class PrepareListServlet extends HttpServlet {

	
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
		PrintWriter out = res.getWriter();
		
		HttpSession aSession = req.getSession();
		
		//makes new list to be printed
		ArrayList<IncidentBean> printList = new ArrayList<IncidentBean>();
		
		boolean isSearch,isSort;
		isSearch=(boolean) aSession.getAttribute("isSearch");
		isSort = (boolean) aSession.getAttribute("isSort");
		
		
		if(isSearch==true) {
			
		//get indexes of searched list
		ArrayList<Integer> indexList = (ArrayList<Integer>) aSession.getAttribute("listOfSearchIndexes");
		
		//add it to list to be printed
		for(int i=0;i<indexList.size();i++) {
			IncidentBean storeIncident = IncidentDAO.getIncidentByIncidentID(indexList.get(i));
			printList.add(storeIncident);	
		}
		
		aSession.setAttribute("isSearch", false);
		aSession.removeAttribute("listOfSearchIndexes");
		
		} else if (isSort==true) {
		
		//get indexes of sorted list
		ArrayList<Integer> indexList = (ArrayList<Integer>) aSession.getAttribute("sortReportsIndexes");
		
		//add it to list to be printed
		for(int i=0;i<indexList.size();i++) {
			IncidentBean storeIncident = IncidentDAO.getIncidentByIncidentID(indexList.get(i));
			printList.add(storeIncident);	
		}
		
		aSession.setAttribute("isSort", false);
		aSession.removeAttribute("sortReportsIndexes");
		
		//out.println(printList.get(0).getIncidentKeywordsInString());
		} else {
			
			//print original list
			printList=IncidentDAO.getIncidentsList();
			
			aSession.setAttribute("listOfIncidents", printList);
			req.getRequestDispatcher("ListOfIncidents.jsp").forward(req, res);
		}
		
		aSession.setAttribute("listOfIncidents", printList);
		req.getRequestDispatcher("ListOfIncidents.jsp").forward(req, res);
		

		
	}
}
