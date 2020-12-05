package web;

import java.io.IOException;
import java.util.ArrayList;

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
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;



@WebServlet(asyncSupported = false, name = "Register Course", urlPatterns = { "/RegisterCourse" })

public class RegisterCourse extends HttpServlet {

	private static MongoClient mongoClient;
	private static MongoClientURI connectionString = new MongoClientURI(
			"mongodb+srv://admin:admin@cluster0.bwy7e.mongodb.net/CMS?retryWrites=true&w=majority");
	private static MongoDatabase database = null;
	private static final long serialVersionUID = 1L;
	Home home;
       
    public RegisterCourse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try {
			// database connection
			mongoClient = new MongoClient(connectionString);
			database = mongoClient.getDatabase("CMS");

			System.out.println("Successfully Connected" + " to the database");
		} catch (Exception e) {
			System.out.println("Failed to Connected" + e);

		}
		
		
		String course_code = request.getParameter("course_code");
		HttpSession session = request.getSession(true);
		String uname = (String) session.getAttribute("userid");
		
		
		MongoCollection<Document> users = database.getCollection("users");
		MongoCollection<Document> courses = database.getCollection("courses");
		
		FindIterable<Document> courseInfo = findCourseByName(courses, course_code);
		FindIterable<Document> userInfo = findUserByName(users, uname);
		String courseName="";
		String capacity = "";

		for (Document course : courseInfo) {

			for (Document user : userInfo) {
				DBObject findQuery = new BasicDBObject("name", uname);

				//Document course22 = new Document("name", course.getString("course_name")).append("term", course.getString("term"));
				DBObject course2 = new BasicDBObject("req_courses", new BasicDBObject("name",course.getString("course_name")).append("term", course.getString("term")));

				
				DBObject updateQuery = new BasicDBObject("$push", course2);
				users.updateOne(Filters.eq("name", uname), new Document().append(
				        "$push",
				        new Document("reg_courses", new Document("name", course.getString("course_name")).append("term", course.getString("term")))
				    ));
				
				System.out.println(course);
				System.out.println(user);

				//System.out.println(user.getString("reg_courses"));
				courseName = course.getString("course_name");
				capacity = course.getString("capacity");
			}
		}
		//System.out.println(courseName);
		//System.out.println(capacity);

		

		// System.out.println(uname);
		// System.out.println(course_code);
		// doGet(request, response);
	}
	


	// need to implenet "doesnt exist" case
	
	private static FindIterable<Document> findCourseByName(MongoCollection<Document> courses, String name) {
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("course_code", name);

		FindIterable<Document> cursor = courses.find(whereQuery);
		//System.out.println(cursor);

		for (Document s : cursor) {
			//System.out.println(s);
		}
		return cursor;

	}
	
	
	private static FindIterable<Document> findUserByName(MongoCollection<Document> users, String name) {
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("name", name);

		FindIterable<Document> cursor = users.find(whereQuery);
		//System.out.println(cursor);

		for (Document s : cursor) {
			//System.out.println(s);
		}
		return cursor;

	}

}
