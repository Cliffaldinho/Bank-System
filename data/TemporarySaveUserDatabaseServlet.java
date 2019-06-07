package data;

import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class TemporarySaveUserDatabaseServlet extends HttpServlet {

	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException {
		ArrayList<User> tempList;
		tempList = new ArrayList<>();
		for(int i=0;i<UserDatabase.getUsersListSize();i++) {
			
		tempList.add(UserDatabase.getUsersList().get(i));
		}
		
		FileOutputStream fout = new FileOutputStream("C:\\");
		ObjectOutputStream oos = new ObjectOutputStream(fout);
		oos.writeObject(tempList);
		
		req.getRequestDispatcher("ListOfIncidents.jsp").forward(req, res);
	}
	
	
}
