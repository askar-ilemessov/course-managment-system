package steps;
import web.*;
import static org.junit.Assert.*;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import cucumber.api.java.en.*;

public class StudentSteps {
	String course_name;
	String course_code;
	String section;
	String prof_name;
	String term;
	
	private static MongoClient mongoClient;
	private static MongoClientURI connectionString = new MongoClientURI(
			"mongodb+srv://admin:admin@cluster0.bwy7e.mongodb.net/CMS?retryWrites=true&w=majority");
	private static MongoDatabase database = null;
	
	CreateCourse courseManager = new CreateCourse();
	Home admin = new Home();
	
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

	@When("^I input \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void i_input(String arg1, String arg2, String arg3, String arg4, String arg5) throws Exception {
		this.course_name = arg1;
		this.course_code = arg2;
		this.section = arg3;
		this.prof_name = arg4;
		this.term =arg5;
	}

	@When("^I press submit$")
	public void I_press_submit() throws Exception {
	    courseManager.addCourse(course_name, course_code, section,	prof_name, term);
	}

	@Then("^the \"([^\"]*)\" course should be created$")
	public void the_course_should_be_created(String arg1) throws Exception {
		assertTrue(checkDatabase(arg1, "Courses"));
	}

	@Given("^there is a student named \"([^\"]*)\"$")
	public void there_is_a_student_named(String arg1) throws Exception {
	   Applications addStudent = new Applications();
	   addStudent.acceptApplication(arg1, database);
	}

	@Given("^\"([^\"]*)\" is registered in \"([^\"]*)\"$")
	public void is_registered_in(String arg1, String arg2) throws Exception {
	   
	}

	@When("^I delete \"([^\"]*)\" from \"([^\"]*)\"$" )
	public void i_delete(String arg1, String arg2) throws Exception {
		if (arg2.equals("Courses")) {
			admin.deleteCourse(arg1, database);
		} else {
			admin.deleteAccount(arg1, database);
		}
	}

	@Then("^\"([^\"]*)\" should be removed from the \"([^\"]*)\" database$")
	public void should_be_removed_from_the_database(String arg1, String arg2) throws Exception {
	    assertFalse(checkDatabase(arg1,arg2));
	}

	@Then("^\"([^\"]*)\" should be removed from \"([^\"]*)\" class list$")
	public void should_be_removed_from_class_list(String arg1, String arg2) throws Exception {
	   assertFalse(checkDatabase(arg1,arg2));
	}

	@Then("^\"([^\"]*)\" should no longer be registered in \"([^\"]*)\"$")
	public void should_no_longer_be_registered_in(String arg1, String arg2) throws Exception {
		assertFalse(checkDatabase(arg1,arg2));
	}
	
	private boolean checkDatabase(String arg1, String arg2) {
		//check database to see if arg1 exists
		MongoCollection<Document> courses = database.getCollection("courses");
		MongoCollection<Document> students = database.getCollection("students");

		BasicDBObject query = new BasicDBObject();
	    query.put("course_name", arg1);
	  
	    FindIterable<Document> docsIterable = courses.find(query);
	    
	    switch (arg1) {
	    case "Courses":
	    	 docsIterable = courses.find(query);
	    	 break;
	    	 
	    case "Students" :
	    	docsIterable = students.find(query);
	    	break;
	    }
	   
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

	}

}
