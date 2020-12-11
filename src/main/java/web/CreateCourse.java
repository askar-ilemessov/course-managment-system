package web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

@WebServlet(asyncSupported = false, name = "Create Course Page", urlPatterns = { "/CreateCourse" })

public class CreateCourse extends HttpServlet {

	private static MongoClient mongoClient;
	private static MongoClientURI connectionString = new MongoClientURI(
			"mongodb+srv://admin:admin@cluster0.bwy7e.mongodb.net/CMS?retryWrites=true&w=majority");
	private static MongoDatabase database = null;

	private static final long serialVersionUID = 1L;

	public CreateCourse() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		RequestDispatcher view = request.getRequestDispatcher("/home");
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		mongoClient = new MongoClient(connectionString);
		database = mongoClient.getDatabase("CMS");
		
		String course_name = request.getParameter("course_name");
		String course_code = request.getParameter("course_code");
		String section = request.getParameter("section");
		String prof_name = request.getParameter("prof_name");
		String term = request.getParameter("term");
		
		if (courseNotDuplicate(course_code, database)) {
			addCourse(course_name, course_code, section, prof_name, term, database);
		}
		
		mongoClient.close();
		

		doGet(request, response);
	}

	private boolean courseNotDuplicate(String course_code, MongoDatabase db) {
		MongoCollection<Document> courses = db.getCollection("courses");

		FindIterable<Document> courseList = courses.find();
		//System.out.println(cursor);
		for (Document c : courseList) {
				if (c.get("course_code").equals(course_code)) {
					System.out.println(course_code + " already exists");
					return false;
				}
		}
		return true;
	}

	class Assignment {
		String name;
		String deadline;
		
		Assignment (String name, String deadline) {
			this.name = name;
			this.deadline = deadline;
		}
	}
	
	public void addCourse(String course_name, String course_code, String section, String prof_name, String term, MongoDatabase db) {
		
		try {
			MongoCollection<Document> courses = db.getCollection("courses");
			
			ArrayList<Assignment> assignments  = new ArrayList<>();
			assignments.add(new Assignment("Assignment 1", new Date(System.currentTimeMillis() + 604800000).toString()));
			assignments.add(new Assignment("Assignment 2", new Date(System.currentTimeMillis() + 1209600000).toString()));
			assignments.add(new Assignment("Assignment 3", new Date(System.currentTimeMillis() + 1814400000).toString()));
			
			Document newCourse = new Document("_id", new ObjectId());
			newCourse.append("course_name", course_name).append("course_code", course_code).append("section", section).append("prof_name", prof_name).append("term", term).append("capacity", 100).append("assignments", new ArrayList<>());
			
			if(courses.count(Filters.eq("course_code",course_code)) == 0L ) {
				courses.insertOne(newCourse);
				
				courses.updateOne(Filters.eq("course_code", course_code), new Document().append("$push", new Document("assignments",
						new Document( "name", "Assignment 1"))));
				courses.updateOne(Filters.eq("course_code", course_code), new Document().append("$push", new Document("assignments",
						new Document( "name", "Assignment 2"))));
				courses.updateOne(Filters.eq("course_code", course_code), new Document().append("$push", new Document("assignments",
						new Document( "name", "Assignment 3"))));
				

				System.out.println("Successfully added " + course_name +  " to courses Database");
			}else {
				System.out.println("Course with this course_code" + course_code + " already exists");
				System.out.println("Course not added");
			}
			
			

		} catch (Exception e) {
			System.out.println("Unsuccessfully" + e);
		}
		
	}

}
