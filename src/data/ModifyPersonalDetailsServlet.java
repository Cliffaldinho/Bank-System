package data;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

@WebServlet(urlPatterns={"/personalDetails"})
public class ModifyPersonalDetailsServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException {
		
		HttpSession aSession = req.getSession();
		StaffBean staffLoggedIn = (StaffBean) aSession.getAttribute("logAuth");
		UserBean user = staffLoggedIn.getUserByUsername();
		req.setAttribute("name", user.getName());
		req.setAttribute("address", user.getAddress());
		req.setAttribute("contact", user.getContactNumber());
		
		req.getRequestDispatcher("PersonalDetails.jsp").forward(req, res);
		
	}

}
