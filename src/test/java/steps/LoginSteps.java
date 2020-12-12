package steps;
import web.*;
import static org.junit.Assert.*;

import cucumber.api.java.en.*;

public class LoginSteps {
	
	String uname;
	String psw;
	Home loginPage;
	
	@Given("^I am on the login page$")
	public void the_server_is_running() throws Exception {
	    loginPage = new Home();
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
	
	@When("^I click apply$")
	public void i_click_apply() throws Exception {
	  
	}

	@Then("^an application for \"([^\"]*)\" should be created$")
	public void an_application_for_should_be_created(String arg1) throws Exception {
	    
	}

	@Given("^an account for \"([^\"]*)\" already exists$")
	public void an_account_for_already_exists(String arg1) throws Exception {
	   
	}

	@Then("^an application for \"([^\"]*)\" should not be created$")
	public void an_application_for_should_not_be_created(String arg1) throws Exception {
	   
	}

	@Given("^I press logout$")
	public void i_press_logout() throws Exception {
	   
	}

	@Then("^I am not logged in$")
	public void i_am_not_logged_in() throws Exception {
	 
	}

}
