package data;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns={"/showStatistics"})
public class ShowStatisticsServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException {

		
		StatisticsBean stats = new StatisticsBean();
		
		HttpSession aSession = req.getSession();
		
		aSession.setAttribute("stats", stats);
		
		
		req.getRequestDispatcher("Statistics.jsp").forward(req, res);
	}
}
