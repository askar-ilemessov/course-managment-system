package web;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet(asyncSupported = false, name = "Registration Page", urlPatterns = { "/register" })
public class RegistrationPage extends HttpServlet {

	private static MongoClient mongoClient;
	private static MongoClientURI connectionString = new MongoClientURI(
			"mongodb+srv://admin:admin@cluster0.bwy7e.mongodb.net/CMS?retryWrites=true&w=majority");
	private static MongoDatabase database = null;

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
	}

	// Method to handle POST method request.
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			// database connection
			mongoClient = new MongoClient(connectionString);
			database = mongoClient.getDatabase("CMS");

			System.out.println("Successfully Connected" + " to the database");
		} catch (Exception e) {
			System.out.println("Failed to Connected" + e);

		}

		// send application here
//		Enumeration<String> params = request.getParameterNames();
//		while (params.hasMoreElements()) {
//			String paramName = params.nextElement();
//			System.out.println("Parameter Name - " + paramName + ", Value - " + request.getParameter(paramName));
//		}
//
		RequestDispatcher view = request.getRequestDispatcher("/");
		view.forward(request, response);

		String name = request.getParameter("name");
		String lastname = request.getParameter("lastName");
		String email = request.getParameter("email");
		String accType = request.getParameter("accountType");

		try {
			MongoCollection<Document> studentCol = database.getCollection("students");
			MongoCollection<Document> profCol = database.getCollection("professors");
			Document newUser = new Document("_id", new ObjectId());
			newUser.append("name", name).append("lastname", lastname).append("email", email);

			if (accType.equals("Student")) {
				studentCol.insertOne(newUser);
			} else if (accType.equals("Professor")) {
				profCol.insertOne(newUser);
			}

			System.out.println("Successfully added " + name + " " + lastname + " to " + accType + " Database");

		} catch (Exception e) {
			System.out.println("Unsuccessfully" + e);
		}
		
		mongoClient.close();

		// System.out.println(" " + name + " lastname" + " " + email + " "+ accType);

	}

}
