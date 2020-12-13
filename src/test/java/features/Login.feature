Feature: Log in

Background:
	Given I am on the login page

#T71
Scenario: request account creation
	Given I apply for "Test" "Student" "test@email.com" "Student" 
	When I click apply
	Then an application for "Test" should be created
	
#T72
Scenario: request account creation when account already exists
	Given an account for "Test" already exists
	And I input "Test" "Student" "test@email.com" "Student"
	When I click apply
	Then an application for "Test" should not be created

#T74
Scenario: log in successful
	Given I type "admin" as username
	And I type "admin" as password
	Then my account credentials should be verified as "valid"

#T75
Scenario: log in unsuccessful
	Given I type "invalid" as username
	And I type "invalid" as password
	Then my account credentials should be verified as "invalid"

#T76
Scenario: log out
	Given I press logout
	Then I am not logged in
