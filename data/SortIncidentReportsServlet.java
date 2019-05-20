package data;

import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns={"/sortIncidentReport"})
public class SortIncidentReportsServlet extends HttpServlet{
	ArrayList<Incident> incidentReports = null;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		ArrayList<Incident> incidentReports = IncidentDatabase.getIncidentsList();
    ArrayList<Integer> sortedList = new ArrayList<Integer>();
    String sortTopic = req.getParameter("sortBy");

    //create copy of incident reports
    ArrayList<Incident> sortedIncidentReports = new ArrayList<>();
    for(Incident i : incidentReports)
    {
      sortedIncidentReports.add(i);
    }
    int key = 0;
    int index = 0;

    switch(sortTopic)
    {
      case "IncidentTitle":
                          Map<Integer, String> indexesC = new HashMap<Integer, String>();
                          for(Incident i: incidentReports) //record indexes of incidents in copy
                          {
                            indexesC.put(index, i.getIncidentTitle()); //why not sort hashmap? can get messy
                            index++;
                          }
                          //sort copy
                          if (sortedIncidentReports.size() > 0) {
                            Collections.sort(sortedIncidentReports, new Comparator<Incident>() {
                              @Override
                              public int compare(final Incident object1, final Incident object2) {
                                return object1.getIncidentTitle().compareTo(object2.getIncidentTitle());
                              }
                            });
                          }
                          for(Incident i: sortedIncidentReports) //fill sortedlist with indexes corresponding sorted copy
                          {
                            for (Map.Entry<Integer, String> entry : indexesC.entrySet()) {
                                String value = entry.getValue();
                                key = entry.getKey();
                                if(value.equals(i.getIncidentTitle()))
                                {
                                  sortedList.add(key);
                                  break;
                                }
                            }
                            indexesC.remove(key); //so no duplicates added
                          }
                          req.setAttribute("sortReportsIndexes", sortedList);
                          req.getRequestDispatcher("ListOfIncidents.jsp").forward(req, res);
                          break;
      case "Category":
                      Map<Integer, String> indexes = new HashMap<Integer, String>();
                      for(Incident i: incidentReports) //record indexes of incidents in copy
                      {
                        indexes.put(index, i.getIncidentCategory());
                        index++;
                      }
                      if (sortedIncidentReports.size() > 0) { //sort copy
                        Collections.sort(sortedIncidentReports, new Comparator<Incident>() {
                          @Override
                          public int compare(final Incident object1, final Incident object2) {
                            return object1.getIncidentCategory().toString().compareTo(object2.getIncidentCategory().toString());
                          }
                        });
                      }
                      for(Incident i: sortedIncidentReports) //fill sortedlist with indexes corresponding sorted copy
                      {
                        for (Map.Entry<Integer, String> entry : indexes.entrySet()) {
                            String value = entry.getValue();
                            key = entry.getKey();
                            if(value.equals(i.getIncidentCategory().toString()))
                            {
                              sortedList.add(key);
                              break;
                            }
                        }
                        indexes.remove(key);
                      }
                      req.setAttribute("sortReportsIndexes", sortedList);
                      req.getRequestDispatcher("ListOfIncidents.jsp").forward(req, res);
                      break;
      case "year":
                  Map<Integer, String> indexesY = new HashMap<Integer, String>();
                  for(Incident i: incidentReports) //record indexes of incidents in copy
                  {
                    indexesY.put(index, i.getIncidentYear());
                    index++;
                  }
                  if (sortedIncidentReports.size() > 0) { //sort copy
                    Collections.sort(sortedIncidentReports, new Comparator<Incident>() {
                      @Override
                      public int compare(final Incident object1, final Incident object2) {
                        return object1.getIncidentYear().compareTo(object2.getIncidentYear());
                      }
                    });
                  }
                  for(Incident i: sortedIncidentReports) //fill sortedlist with indexes corresponding sorted copy
                  {
                    for (Map.Entry<Integer, String> entry : indexesY.entrySet()) {
                        String value = entry.getValue();
                        key = entry.getKey();
                        if(value==i.getIncidentYear())
                        {
                          sortedList.add(key);
                          break;
                        }
                    }
                    indexesY.remove(key);
                  }
                  req.setAttribute("sortReportsIndexes", sortedList);
                  req.getRequestDispatcher("ListOfIncidents.jsp").forward(req, res);
                  break;
      default:
              sortedList = incidentReports;
              req.setAttribute("sortReportsIndexes", sortedList); //send back original list
              req.getRequestDispatcher("ListOfIncidents.jsp").forward(req, res);
              break;
    }
    //end switch

	}
}
