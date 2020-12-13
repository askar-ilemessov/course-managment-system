package web;

import java.io.File;
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
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

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
			MongoCollection<Document> accounts = database.getCollection("users");

			if (request.getParameterMap().containsKey("deregisterCourse")) {
				//remove class from reg_courses, change capacity + 1, and remove student from course class_list
				
				HttpSession session = request.getSession(true);
				String name = (String) session.getAttribute("userid");
				String courseName = (String) request.getParameter("deregisterCourse");
				
				dropCourse(name,courseName,database);
	    	
				
				RequestDispatcher view = request.getRequestDispatcher("/home");
				view.forward(request, response);
				
			} else {
				BasicDBObject query = new BasicDBObject();
			    query.put("course_name", request.getParameter("viewCourse"));
			    
			    FindIterable<Document> course = courses.find(query);
			    
			    
			    for (Document c : course) {
//			    	System.out.println(c);
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
				
			RequestDispatcher view = request.getRequestDispatcher("/CoursePage.jsp");
			view.forward(request, response);
			}
		} catch (Exception e) {
			
		}
	}
	
	public void dropCourse(String name, String courseName, MongoDatabase db) {
		MongoCollection<Document> courses = db.getCollection("courses");
		MongoCollection<Document> accounts = db.getCollection("users");
		
		Bson query = new Document().append("name", name);
		Bson fields = new Document().append("reg_courses", new Document().append( "name", courseName));
		Bson update = new Document("$pull",fields);

		accounts.updateOne( query, update );
		
		
		
		
		
		Bson query2 = new Document().append("course_name", courseName);
		Bson fields2 = new Document().append("class_list", new Document().append( "student_name", name));
		Bson update2 = new Document("$pull",fields2);
		courses.updateOne(query2, update2);
		
	    
	    //incrementing capacity by one, since we deregister user from  courses
	    BasicDBObject newDocument = new BasicDBObject().append("$inc", new BasicDBObject().append("capacity", 1));
		courses.updateOne(query2, newDocument);
	    
	   
		
		
		
		
		
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

	public void submitDeliverable(String student, String courseName, MongoDatabase db, File file) {

		MongoCollection<Document> courses = db.getCollection("courses");

	
	    BasicDBObject query = new BasicDBObject();
	    List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
	    obj.add(new BasicDBObject("name", courseName));
	    
	    FindIterable<Document> course = courses.find(query);
	    
	    
	    for (Document c : course) {
//	    	System.out.println(c);
	    	List<Document> list = (List<Document>)c.get("assignments");
	    	
	    	 for (Document assignment : list) {
	    		 MongoCollection<Document> users = db.getCollection("users");
	    		 BasicDBObject userQuery = new BasicDBObject();
	    	
	    	 }
	    }
	    
		
	}

}
