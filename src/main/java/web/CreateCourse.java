package web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.util.Enumeration;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

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
		
		RequestDispatcher view = request.getRequestDispatcher("/ManageApplications.jsp");
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// database connection
			mongoClient = new MongoClient(connectionString);
			database = mongoClient.getDatabase("CMS");

			System.out.println("Successfully Connected" + " to the database");
		} catch (Exception e) {
			System.out.println("Failed to Connected" + e);
		}
		
		String course_name = request.getParameter("course_name");
		String course_code = request.getParameter("course_code");
		String section = request.getParameter("section");
		String prof_name = request.getParameter("prof_name");
		String term = request.getParameter("term");
		
		try {
			MongoCollection<Document> courses = database.getCollection("courses");
			
			Document newCourse = new Document("_id", new ObjectId());
			newCourse.append("course_name", course_name).append("course_code", course_code).append("section", section).append("prof_name", prof_name).append("term", term);

			
			courses.insertOne(newCourse);
			

			System.out.println("Successfully added " + course_name +  " to courses Database");

		} catch (Exception e) {
			System.out.println("Unsuccessfully" + e);
		}
		
		mongoClient.close();
		

		doGet(request, response);
	}

}
