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

//prepare to modify personal details of user
//receives from ListOfIncidents.jsp, FinishModifyPersonalDetailsServlet
@WebServlet(urlPatterns={"/personalDetails"})
public class ModifyPersonalDetailsServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException {
		
		PrintWriter out = res.getWriter();
		
		//get the StaffBean of the staff from this session
		HttpSession aSession = req.getSession();
		StaffBean staffLoggedIn = (StaffBean) aSession.getAttribute("logAuth");
		
		//get the corresponding UserBean
		UserBean user = staffLoggedIn.getUserByUsername();
		
		//set session attributes name, address, contact, to be used in PersonalDetails.jsp, and FinishModifyStaffServlet
		aSession.setAttribute("staffName", user.getName());
		aSession.setAttribute("staffAddress", user.getAddress());
		aSession.setAttribute("staffContact", user.getContactNumber());

		//forward to PersonalDetails.jsp
		req.getRequestDispatcher("PersonalDetails.jsp").forward(req, res);
		
	}

}
