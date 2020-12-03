package web;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * Servlet implementation class Applications
 */
@WebServlet(asyncSupported = false, name = "Applications", urlPatterns = { "/applications" })
public class Applications extends HttpServlet {
	private static MongoClient mongoClient;
	private static MongoClientURI connectionString = new MongoClientURI(
			"mongodb+srv://admin:admin@cluster0.bwy7e.mongodb.net/CMS?retryWrites=true&w=majority");
	private static MongoDatabase database = null;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Applications() {
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
		// TODO Auto-generated method stub
		try {
//			Enumeration<String> params = request.getParameterNames();
//			while (params.hasMoreElements()) {
//				String paramName = params.nextElement();
//				System.out.println("Parameter Name - " + paramName + ", Value - " + request.getParameter(paramName));
//			}
			mongoClient = new MongoClient(connectionString);
			database = mongoClient.getDatabase("CMS");
			MongoCollection<Document> applications = database.getCollection("applications");
			MongoCollection<Document> users = database.getCollection("users");

			if (request.getParameterMap().containsKey("ConfirmApp")) {
				Document newCourse = new Document("_id", new ObjectId());
				newCourse.append("name", request.getParameter("ConfirmApp")).append("password", "password");

				users.insertOne(newCourse);
				
			} 			
			applications.deleteOne(new Document("_id", new ObjectId(request.getParameter("DenyApp"))));
		} catch (Exception e) {
			
		}
		RequestDispatcher view = request.getRequestDispatcher("/home");
		view.forward(request, response);
	}

}