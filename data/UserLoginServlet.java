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


@WebServlet(urlPatterns={"/userLogin"})
public class UserLoginServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		String userid = req.getParameter("userid");
		String password = req.getParameter("password");
		boolean found = false;
		User userTest = new User("Bob", "11 Test St", "00000000", User.Position.IT, "id1234", "password");
		UserDatabase.addUsers(userTest);
		
		for (int i = 0; i < UserDatabase.getUsersList().size(); i++){
			String id = UserDatabase.getUsersList().get(i).getStaffID();
			String pw = UserDatabase.getUsersList().get(i).getPassword();
			if (userid.equals(id)){
				if (password.equals(pw)){
					found = true;
					req.getRequestDispatcher("ListOfIncidents.jsp").forward(req,res);
				}
			}
		}	
		if (!found){
			req.setAttribute("loginError","Incorrect user ID or password.");
			req.getRequestDispatcher("UserLogin.jsp").forward(req,res);
		}
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
		
		
		
	}
	
	
	
} 