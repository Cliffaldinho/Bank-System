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

@WebServlet(urlPatterns={"/unlockAccount"})
public class UnlockAccountServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException {
		
		HttpSession aSession = req.getSession();
		String userID = (String) aSession.getAttribute("userID");
		UserDAO.getUserByStaffID(userID).setLocked(false);
		
		req.getRequestDispatcher("SetStaffRole.jsp").forward(req,res);
	}
}
