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
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		HttpSession aSession = req.getSession(true);
		StaffBean logAuth = (StaffBean)session.getAttribute("logAuth");
		logAuth.setUsername(null);
		req.setAttribute("loginError",null);
		req.getRequestDispatcher("UserLogin.jsp").forward(req,res);
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
		
		
		
	}
	
	
	
} 
