package web.cms;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.bson.Document;
import com.mongodb.DBObject;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoDatabase;

import connection.DatabaseConnection;

/**
 * Servlet implementation class LoginCheck
 */

@WebServlet ("/LoginCheck")

public class LoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public LoginCheck() {
        super();
      
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//MongoClientURI connectionString = new MongoClientURI("mongodb+srv://admin:admin@cluster0.bwy7e.mongodb.net/CMS?retryWrites=true&w=majority");
		//MongoClient mongoClient = new MongoClient(connectionString);
		//MongoDatabase database = mongoClient.getDatabase("CMS");
		
		//MongoCollection<Document> collection = database.getCollection("users");

	
		//String user = request.getParameter("uname");
		//String pass = request.getParameter("password");
		
		
		
		String password = request.getParameter("password");
        if (password.equals("")) {
        	response.sendRedirect("error.jsp");
            return;
        }
        
        String username = request.getParameter("uname");
        if (username.equals("")) {
        	response.sendRedirect("error.jsp");
            return;
        }
        
        
        
		//mongoClient.close();
		
	}

	

	

}
