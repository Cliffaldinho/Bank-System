package data;

import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns={"/sortIncidentReports"})
public class SortIncidentReportsServlet extends HttpServlet{
	ArrayList<IncidentBean> incidentReports = null;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		ArrayList<IncidentBean> incidentReports = IncidentDAO.getIncidentsList();
    ArrayList<Integer> sortedList = new ArrayList<Integer>();
    String sortTopic = req.getParameter("sortBy");

    //create copy of incident reports
    ArrayList<IncidentBean> sortedIncidentReports = new ArrayList<>();
    for(IncidentBean i : incidentReports)
    {
      sortedIncidentReports.add(i);
    }
    int key = 0;
    int index = 0;

    switch(sortTopic)
    {
      case "Title":
                          Map<Integer, String> indexesT = new HashMap<Integer, String>();
                          for(IncidentBean i: incidentReports) //record indexes of incidents in copy
                          {
                            indexesT.put(index, i.getIncidentTitle()); //why not sort hashmap? can get messy
                            index++;
                          }
                          //sort copy
                          if (sortedIncidentReports.size() > 0) {
                            Collections.sort(sortedIncidentReports, new Comparator<IncidentBean>() {
                              @Override
                              public int compare(final IncidentBean object1, final IncidentBean object2) {
                                return object1.getIncidentTitle().compareTo(object2.getIncidentTitle());
                              }
                            });
                          }
                          for(IncidentBean i: sortedIncidentReports) //fill sortedlist with indexes corresponding sorted copy
                          {
                            for (Map.Entry<Integer, String> entry : indexesT.entrySet()) {
                                String value = entry.getValue();
                                key = entry.getKey();
                                if(value.equals(i.getIncidentTitle()))
                                {
                                  sortedList.add(key);
                                  break;
                                }
                            }
                            indexesT.remove(key); //so no duplicates added
                          }
                          req.setAttribute("sortReportsIndexes", sortedList);
                          req.getRequestDispatcher("ListOfIncidents.jsp").forward(req, res);
                          break;
      case "Category":
                      Map<Integer, String> indexes = new HashMap<Integer, String>();
                      for(IncidentBean i: incidentReports) //record indexes of incidents in copy
                      {
                        indexes.put(index, i.getIncidentCategory().toString());
                        index++;
                      }
                      if (sortedIncidentReports.size() > 0) { //sort copy
                        Collections.sort(sortedIncidentReports, new Comparator<IncidentBean>() {
                          @Override
                          public int compare(final IncidentBean object1, final IncidentBean object2) {
                            return object1.getIncidentCategory().toString().compareTo(object2.getIncidentCategory().toString());
                          }
                        });
                      }
                      for(IncidentBean i: sortedIncidentReports) //fill sortedlist with indexes corresponding sorted copy
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
      case "Year":
                  Map<Integer, Integer> indexesY = new HashMap<Integer, Integer>();
                  for(IncidentBean i: incidentReports) //record indexes of incidents in copy
                  {
                    indexesY.put(index, i.getIncidentYear());
                    index++;
                  }
                  if (sortedIncidentReports.size() > 0) { //sort copy
                    Collections.sort(sortedIncidentReports, new Comparator<IncidentBean>() {
                      @Override
                      public int compare(final IncidentBean object1, final IncidentBean object2) {
                        if(object1.getIncidentYear() > object2.getIncidentYear()) {
                          return 1;
                        } else if (object1.getIncidentYear() < object2.getIncidentYear()) {
                          return -1;
                        } else {
                          return 0;
                        }
                      }
                    });
                  }
                  for(IncidentBean i: sortedIncidentReports) //fill sortedlist with indexes corresponding sorted copy
                  {
                    for (Map.Entry<Integer, Integer> entry : indexesY.entrySet()) {
                        int value = entry.getValue();
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
              for(IncidentBean i: incidentReports)
              {
                sortedList.add(index);
                index++;
              }
              req.setAttribute("sortReportsIndexes", sortedList); //send back original list
              req.getRequestDispatcher("ListOfIncidents.jsp").forward(req, res);
              break;
    }
    //end switch

	}
}
