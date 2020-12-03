package web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
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
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

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
		System.out.println("GET");
		refreshAttributes(request);
		RequestDispatcher view = request.getRequestDispatcher("/ManageApplications.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		switch (getReqType(request)) {
		//LOGIN AUTH
		case "login":
			String uname = request.getParameter("uname");
			String psw = request.getParameter("psw");

			if (checkLoginCredentials(uname, psw)) {
				System.out.println("Successfully logged in as " + uname);
				
				try {
					refreshAttributes(request);

				} catch (Exception e) {
					
				}
				// Create a session object
				HttpSession session = request.getSession(true);
				session.setAttribute("userid", request.getParameter("uname"));
				
				if(uname.equals("admin")) {
					RequestDispatcher view = request.getRequestDispatcher("/ManageApplications.jsp");
					view.forward(request, response);
				}else {
					RequestDispatcher view = request.getRequestDispatcher("/StudentView.jsp");
					MongoCollection<Document> courses = database.getCollection("courses");
					request.setAttribute("courses", parseCollection(courses));
					
					
					
	
					
					
					
					
					

					
					
					
					MongoCollection<Document> collection = database.getCollection("users");
					
					
					List<Document> users = (List<Document>) collection.find().into(new ArrayList<Document>());
					
					for (Document user : users) {
						if(user.getString("name").equals(uname)) {
							List<Document> regCourse = (List<Document>) user.get("reg_courses");
							
							request.setAttribute("courses2", regCourse);
							
							for (Document course : regCourse) {
								System.out.println("name = " + user.getString("name") + " course details below");
								System.out.println("course name = " + course.getString("name"));
								System.out.println("cours type = " + course.getString("term"));
							}
						}
						
						
					}			
					
					view.forward(request, response);
				}
				
			} else {
				request.setAttribute("showError", "true");
				RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
				view.forward(request, response);
			}
			break;
		//DELETE ACCOUNT
		case "deleteAccount":
			try {
				MongoCollection<Document> accounts = database.getCollection("users");
				
				accounts.deleteOne(new Document("_id", new ObjectId(request.getParameter("deleteAccount"))));
				
				refreshAttributes(request);

				RequestDispatcher view = request.getRequestDispatcher("/ManageApplications.jsp");
				view.forward(request, response);

			} catch (Exception e) {
				
			}
			break;
		case "deleteCourse":
			try {
				MongoCollection<Document> courses = database.getCollection("courses");
				
				courses.deleteOne(new Document("_id", new ObjectId(request.getParameter("deleteCourse"))));
				
				refreshAttributes(request);

				RequestDispatcher view = request.getRequestDispatcher("/ManageApplications.jsp");
				view.forward(request, response);

			} catch (Exception e) {
				
			}
			break;
		default:
			refreshAttributes(request);
			RequestDispatcher view = request.getRequestDispatcher("/ManageApplications.jsp");
			view.forward(request, response);
			break;
		}
	}

	

	private String getReqType(HttpServletRequest request) {
		try {
//			Enumeration<String> params = request.getParameterNames();
//			while (params.hasMoreElements()) {
//				String paramName = params.nextElement();
//				System.out.println("Parameter Name - " + paramName + ", Value - " + request.getParameter(paramName));
//			}
			if (request.getParameterMap().containsKey("login")) {
				return "login";
			} else if (request.getParameterMap().containsKey("deleteAccount")) {
				return "deleteAccount";
			} else if (request.getParameterMap().containsKey("deleteCourse")) {
				return "deleteCourse";
			} 
		} catch (Exception e) {
			
		}
		return "";
	}
	
	
	private <T> Object parseCollection(List<Document> list) {
		// Retrieving the documents
		ArrayList<List> documents = new ArrayList<>();
		
		FindIterable<List> iterDoc = ((MongoCollection<List>) list).find();
		MongoCursor<List> it = iterDoc.iterator();
		while (it.hasNext()) {
			documents.add((List) it.next());
		}
		return documents;
	}

	private <T> Object parseCollection(MongoCollection<Document> accounts) {
		// Retrieving the documents
		ArrayList<Document> documents = new ArrayList<>();
		FindIterable<Document> iterDoc = accounts.find();
		MongoCursor<Document> it = iterDoc.iterator();
		while (it.hasNext()) {
			documents.add((Document) it.next());
		}
		return documents;
	}
	
	


	public boolean checkLoginCredentials(String uname, String psw) {
		try {
				try {
					// database connection
					mongoClient = new MongoClient(connectionString);
					database = mongoClient.getDatabase("CMS");
	
					System.out.println("Successfully Connected" + " to the database");
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
	
	public void refreshAttributes(HttpServletRequest request) {
		MongoCollection<Document> accounts = database.getCollection("users");
		MongoCollection<Document> courses = database.getCollection("courses");
		MongoCollection<Document> applications = database.getCollection("applications");
		
		request.setAttribute("accounts", parseCollection(accounts));
		request.setAttribute("courses", parseCollection(courses));
		request.setAttribute("applications", parseCollection(applications));
	}

}
