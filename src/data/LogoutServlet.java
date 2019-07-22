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
		req.setAttribute("loginError",null);
		
		aSession.removeAttribute("isSearch");
		aSession.removeAttribute("isSort");
		aSession.removeAttribute("printList");
		
		req.getRequestDispatcher("index.jsp").forward(req,res);
	}
	

	
	
} 