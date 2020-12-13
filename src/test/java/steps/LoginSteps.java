package steps;
import web.*;
import static org.junit.Assert.*;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import cucumber.api.java.en.*;

public class LoginSteps {
	
	String uname;
	String psw;
	
	Home loginPage;
	RegistrationPage registrationPage;
	
	private static MongoClient mongoClient;
	private static MongoClientURI connectionString = new MongoClientURI(
			"mongodb+srv://admin:admin@cluster0.bwy7e.mongodb.net/CMS?retryWrites=true&w=majority");
	private static MongoDatabase database = null;
	
	String name;
	String lastname;
	String email;
	String accType;

	
	@Given("^I am on the login page$")
	public void the_server_is_running() throws Exception {
	    loginPage = new Home();
	    registrationPage = new RegistrationPage();
	    
		try {
			// database connection
			mongoClient = new MongoClient(connectionString);
			database = mongoClient.getDatabase("CMS");

			System.out.println("Successfully Connected" + " to the database");
		} catch (Exception e) {
			System.out.println("Failed to Connected" + e);
		}
	}
	
	@Given("^I type \"([^\"]*)\" as username$")
	public void i_type_as_username(String arg1) throws Exception {
	    this.uname = arg1;
	}
	
	@Given("^I type \"([^\"]*)\" as password$")
	public void i_type_as_password(String arg1) throws Exception {
		 this.psw = arg1;
	}
	
	@Then("^my account credentials should be verified as \"([^\"]*)\"")
	public void i_should_be_verified_by_mongo(String arg1) throws Exception {
		switch (arg1) {
		case "valid":
		    assertTrue(loginPage.checkLoginCredentials(this.uname,this.psw));
		    break;
		case "invalid":
		    assertFalse(loginPage.checkLoginCredentials(this.uname,this.psw));
		    break;
		}
	}
	
	@Given("^I apply for \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void i_apply_for(String arg1, String arg2, String arg3, String arg4) throws Exception {
	   this.name = arg1;
	   this.lastname = arg2;
	   this.email = arg3;
	   this.accType = arg4;
	}
	
	@When("^I click apply$")
	public void i_click_apply() throws Exception {
		MongoCollection<Document> applications = database.getCollection("applications");
		
		Document newUser = new Document("_id", new ObjectId());
		newUser.append("name", name).append("lastname", lastname).append("email", email).append("accType", accType);

		applications.insertOne(newUser);
		
	}

	@Then("^an application for \"([^\"]*)\" should be created$")
	public void an_application_for_should_be_created(String arg1) throws Exception {
	    checkDatabase(arg1);
	}

	@Given("^an account for \"([^\"]*)\" already exists$")
	public void an_account_for_already_exists(String arg1) throws Exception {
		assertTrue(registrationPage.accountExists("Test", database));
	}

	@Then("^an application for \"([^\"]*)\" should not be created$")
	public void an_application_for_should_not_be_created(String arg1) throws Exception {
	   assertTrue(registrationPage.accountExists(arg1, database));
	}

	@Given("^I press logout$")
	public void i_press_logout() throws Exception {
	   loginPage.logout();
	}

	@Then("^I am not logged in$")
	public void i_am_not_logged_in() throws Exception {
		assertTrue(loginPage.logout());
	}
	
	private boolean checkDatabase(String arg1) {
		MongoCollection<Document> applications = database.getCollection("applications");
		BasicDBObject query = new BasicDBObject();
	    query.put("name", arg1);
		long application = applications.countDocuments(query);
		
		if (application > 0) {
		    applications.deleteOne(query);
			return true;
		} else return false;
	}

}
