package data;

import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;


//receives from CreateIncidentReport.jsp

@WebServlet(urlPatterns={"/userLogin"})
public class UserLoginServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		String userid = req.getParameter("userid");
		String password = req.getParameter("password");
		User loginUser = new User();
		
		for (int i = 0; i < UserDatabase.getUserList().size(); i++){
			id = UserDatabase.getUserList().get(i).getStaffID();
			pw = UserDatabase.getUserList().get(i).getPassword();
			if (userid.equals(id)){
				if (password.equals(pw)){
					req.getRequestDispatcher("ListOfIncidents.jsp").forward(req,res);
				} else {
					alert("Incorrect username or password. Please try again.");
					req.getRequestDispatcher("UserLogin.jsp").forward(req,res);
				}
			} else {
				alert("Incorrect username or password. Please try again.");
				req.getRequestDispatcher("UserLogin.jsp").forward(req,res);
			}
		}		
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
		
		
		
	}
	
	
	
}