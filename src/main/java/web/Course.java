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
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

/**
 * Servlet implementation class Applications
 */
@WebServlet(asyncSupported = false, name = "Course", urlPatterns = { "/Course" })
public class Course extends HttpServlet {
	private static MongoClient mongoClient;
	private static MongoClientURI connectionString = new MongoClientURI(
			"mongodb+srv://admin:admin@cluster0.bwy7e.mongodb.net/CMS?retryWrites=true&w=majority");
	private static MongoDatabase database = null;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Course() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    class Assignment {
		String name;
		Boolean submission;
		String grade;
		
		Assignment(String name, Boolean submission, String grade) {
			this.name = name;
			this.submission = submission;
			this.grade = grade;
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Enumeration<String> params = request.getParameterNames();
			while (params.hasMoreElements()) {
				String paramName = params.nextElement();
				System.out.println("Parameter Name - " + paramName + ", Value - " + request.getParameter(paramName));
			}
			mongoClient = new MongoClient(connectionString);
			database = mongoClient.getDatabase("CMS");
			MongoCollection<Document> courses = database.getCollection("courses");

		
		    BasicDBObject query = new BasicDBObject();
		    query.put("course_name", request.getParameter("viewCourse"));
		    
		    FindIterable<Document> course = courses.find(query);
		    
		    
		    for (Document c : course) {
//		    	System.out.println(c);
		    	List<Document> list = (List<Document>)c.get("assignments");
		    	request.setAttribute("assignments", list);
		    	 for (Document assignment : list) {
		    		 MongoCollection<Document> users = database.getCollection("users");
		    		 BasicDBObject userQuery = new BasicDBObject();
		    		 HttpSession session = request.getSession(true);
		    		 userQuery.put("name", session.getAttribute("userid"));
		    		 
		    		 users.updateOne(query, new Document("$set", new Document(assignment)));
		    	 }
		    }
		    
		    request.setAttribute("course", course);
			
		} catch (Exception e) {
			
		}
		RequestDispatcher view = request.getRequestDispatcher("/CoursePage.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Enumeration<String> params = request.getParameterNames();
			while (params.hasMoreElements()) {
				String paramName = params.nextElement();
				System.out.println("Parameter Name - " + paramName + ", Value - " + request.getParameter(paramName));
			}
			mongoClient = new MongoClient(connectionString);
			database = mongoClient.getDatabase("CMS");
			MongoCollection<Document> users = database.getCollection("users");

		
		    BasicDBObject query = new BasicDBObject();
		    List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
		    obj.add(new BasicDBObject("name", request.getParameter("name")));
		  
		    HttpSession session = request.getSession(true);
		    System.out.println(session.getAttribute("userid"));
		    
		    Assignment a1 = new Assignment(request.getParameter("assignment"), request.getParameter("submission")!= null, "0");
		    
		    //matches name in users, sets assignment param to a1
		    users.updateOne(query, new Document("$set", new Document(request.getParameter("assignment"), a1)));
			
		} catch (Exception e) {
			
		}
		RequestDispatcher view = request.getRequestDispatcher("/StudentView.jsp");
		view.forward(request, response);
	}

}
