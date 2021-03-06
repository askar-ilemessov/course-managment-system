package steps;
import web.*;
import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import cucumber.api.java.en.*;

public class StudentSteps {
	String course_code;
	
	
	private static MongoClient mongoClient;
	private static MongoClientURI connectionString = new MongoClientURI(
			"mongodb+srv://admin:admin@cluster0.bwy7e.mongodb.net/CMS?retryWrites=true&w=majority");
	private static MongoDatabase database = null;
	
	CreateCourse courseManager = new CreateCourse();
	RegisterCourse student = new RegisterCourse();
	Course course = new Course();
	
	@Given("^I am on the Student page$")
	public void i_am_on_the_Student_page() throws Exception {
		try {
			// database connection
			mongoClient = new MongoClient(connectionString);
			database = mongoClient.getDatabase("CMS");

			System.out.println("Successfully Connected" + " to the database");
		} catch (Exception e) {
			System.out.println("Failed to Connected" + e);
		}
	}
	@Given("^a course with the course code \"([^\"]*)\" exists$")
	public void a_course_with_the_course_code_exists(String arg1) throws Exception {
	   courseManager.addCourse(arg1, arg1, "test",	"test", "test", database);
	}

	@When("^I input \"([^\"]*)\" as the course code$")
	public void i_input_as_the_course_code(String arg1) throws Exception {
	  this.course_code = arg1;
	}

	@When("^I press register$")
	public void i_press_register() throws Exception {
	    student.registerCourse(course_code, "Test Student", database);
	}

	@Then("^I am registered in \"([^\"]*)\"$")
	public void i_am_registered_in(String arg1) throws Exception {
	  checkDatabase(course_code);
	  assertTrue(checkDatabase(course_code));
	}

	@Given("^the capacity is full$")
	public void the_capacity_is_full() throws Exception {
	  fillCapacity(this.course_code);
	}

	@Then("^I am not registered in \"([^\"]*)\"$")
	public void i_am_not_registered_in(String arg1) throws Exception {
		assertFalse(checkDatabase(course_code));
	}

	@Given("^I am enrolled in \"([^\"]*)\"$")
	public void i_am_enrolled_in(String arg1) throws Exception {
		student.registerCourse(arg1, "Test Student", database);
	}

	@When("^I drop \"([^\"]*)\"$")
	public void i_drop(String arg1) throws Exception {
	    course.dropCourse("Test Student", arg1, database);
	}

	@Then("^I am not in the class list for \"([^\"]*)\"$")
	public void i_am_not_in_the_class_list_for(String arg1) throws Exception {
	   assertFalse(checkClassList(arg1));
	}
	
	@Then("^I am not registered again into \"([^\"]*)\"$")
	public void i_am_not_registered_again_into(String arg1) throws Exception {
	    assertFalse(student.notAlreadyRegistered(arg1, "Test Student", database));
	}
	
	@Given("^I submit an assignment to \"([^\"]*)\"$")
	public void i_submit_an_assignment_to(String arg1) throws Exception {
		File file = new File("filename.txt");
	    course.submitDeliverable("Test Student", arg1, database, file);
	}

	@Then("^my deliverable is submitted$")
	public void my_deliverable_is_submitted() throws Exception {
	    assertFalse(checkDatabase("Test Student"));
	}

	private void fillCapacity(String course_code) {
		MongoCollection<Document> courses = database.getCollection("courses");
	
		courses.findOneAndUpdate(Filters.eq("course_code", course_code), Updates.set("capacity", 0));
	}
	
	private boolean checkDatabase(String arg1) {
		MongoCollection<Document> students = database.getCollection("users");
		
		BasicDBObject query = new BasicDBObject();
	    query.put("name", "Test Student");
	    
		FindIterable<Document> student = students.find(query);

		 for (Document c : student ) {
		    	List<Document> list = (List<Document>)c.get("reg_courses");
		    	for (Document course  : list ) {
		    		if (course.get("name").equals(course_code)) {
		    			return true;
		    		} else return false;
		    	}
		    
		    }
		 return false;
	}
	
	private boolean checkClassList(String arg1) {
		MongoCollection<Document> courses = database.getCollection("courses");
		BasicDBObject query = new BasicDBObject();
		query.put("course_code", arg1);

		FindIterable<Document> course = courses.find(query);
		//System.out.println(cursor);
		for (Document c : course) {
			List<Document> classlist = (List<Document>) c.get("class_list");
			for (Document student : classlist) {
				if (student.get("student_name").equals("Test_Student")) {
					return true;
				}
			}
		}
		return false;
	}
}
