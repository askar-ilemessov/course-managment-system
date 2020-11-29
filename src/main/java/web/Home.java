package web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

/**
 * Servlet implementation class Home
 */
@WebServlet(asyncSupported = false, name = "Home Page", urlPatterns = { "/home" })
public class Home extends HttpServlet {
	private static MongoClient mongoClient;
	private static MongoClientURI connectionString = new MongoClientURI(
			"mongodb+srv://admin:admin@cluster0.bwy7e.mongodb.net/CMS?retryWrites=true&w=majority");
	private static MongoDatabase database = null;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// send application here
//		Enumeration<String> params = request.getParameterNames();
//		while (params.hasMoreElements()) {
//			String paramName = params.nextElement();
//			System.out.println("Parameter Name - " + paramName + ", Value - " + request.getParameter(paramName));
//		}

		String uname = request.getParameter("uname");
		String psw = request.getParameter("psw");

		if (checkLoginCredentials(uname, psw)) {
			System.out.println("Successfully logged in as " + uname);
			
			try {
				MongoCollection<Document> accounts = database.getCollection("users");
				mongoClient.close();
				request.setAttribute("accounts", accounts);

			} catch (Exception e) {
				
			}
			// Create a session object
			HttpSession session = request.getSession(true);
			session.setAttribute("userid", request.getParameter("uname"));
					
			RequestDispatcher view = request.getRequestDispatcher("/ManageApplications.jsp");
			view.forward(request, response);
		} else {
			request.setAttribute("showError", "true");
			RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
			view.forward(request, response);
		}

		// System.out.println(" " + name + " lastname" + " " + email + " "+ accType);
		
		//doGet(request, response);
	}

	public boolean checkLoginCredentials(String uname, String psw) {
		try {
				try {
					// database connection
					mongoClient = new MongoClient(connectionString);
					database = mongoClient.getDatabase("CMS");
	
					System.out.println("Successfully Connected" + " to the database");
					mongoClient.close();
				} catch (Exception e) {
					System.out.println("Failed to Connected" + e);
	
				}

				MongoCollection<Document> accounts = database.getCollection("users");

				BasicDBObject query = new BasicDBObject();
			    List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
			    obj.add(new BasicDBObject("name", uname));
			    obj.add(new BasicDBObject("password", psw));
			    query.put("$and", obj);
			  
			    FindIterable<Document> docsIterable = accounts.find(query);
				try (MongoCursor<Document> iterator = docsIterable.iterator()) {
					int count = 0;
					while (iterator.hasNext() && count < 1) {
						iterator.next();
						count++;
					}
					
					if (count > 0) {
						return true;
					} else {
						return false;
					}
				}

		} catch (Exception e) {
			System.out.println("Unsuccessfully" + e);
		}
		return false;
	}

}
