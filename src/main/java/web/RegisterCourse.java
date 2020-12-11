package web;

import java.io.IOException;
import java.util.ArrayList;

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
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try {
			// database connection
			mongoClient = new MongoClient(connectionString);
			database = mongoClient.getDatabase("CMS");

			System.out.println("Successfully Connected" + " to the database");
		} catch (Exception e) {
			System.out.println("Failed to Connected" + e);

		}
		
		String course_code = request.getParameter("courseSelection");
		HttpSession session = request.getSession(true);
		String uname = (String) session.getAttribute("userid");
		
		MongoCollection<Document> courses = database.getCollection("courses");
		FindIterable<Document> courseInfo = findCourseByName(courses, course_code);

		for (Document course : courseInfo) {
			
			if(course.getInteger("capacity") != 0) {
				registerCourse(course_code, uname, database);
			}else {
				System.out.println("The course is full");
			}
		}
		
		RequestDispatcher view = request.getRequestDispatcher("/home");
		view.forward(request, response);
	}
	

	public void registerCourse(String course_code, String uname, MongoDatabase db) {

		if (seatsAvailable(course_code, db)) {
		MongoCollection<Document> users = db.getCollection("users");
		MongoCollection<Document> courses = db.getCollection("courses");

		FindIterable<Document> courseInfo = findCourseByName(courses, course_code);
		FindIterable<Document> userInfo = findUserByName(users, uname);

		for (Document course : courseInfo) {

			users.updateOne(Filters.eq("name", uname), new Document().append("$push", new Document("reg_courses",
					new Document("name", course.getString("course_name")).append("term", course.getString("term")).append("assignments", new ArrayList<>()))));
			
		}
		BasicDBObject newDocument =
				new BasicDBObject().append("$inc",
				new BasicDBObject().append("capacity", -1));
		courses.updateOne(new BasicDBObject().append("course_code", course_code), newDocument);
		
		courses.updateOne(Filters.eq("course_code", course_code), new Document().append("$push", new Document("class_list",
				new Document( "student_name", uname))));
		
		}
	}
	


	
	private boolean seatsAvailable(String course_code, MongoDatabase db) {
		MongoCollection<Document> courses = db.getCollection("courses");
		BasicDBObject query = new BasicDBObject();
		query.put("course_code", course_code);

		FindIterable<Document> course = courses.find(query);
		//System.out.println(cursor);
		for (Document c : course) {
			if ((int)c.get("capacity") > 0) {
				return true;
			} else return false;
		}
		return true;
	}


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
