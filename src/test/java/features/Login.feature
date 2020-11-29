Feature: Log in

Background:
	Given I am on the login page
	   
Scenario: log in successful
	Given I type "admin" as username
	And I type "admin" as password
	Then my account credentials should be verified as "valid"
	
Scenario: log in unsuccessful
	Given I type "invalid" as username
	And I type "invalid" as password
	Then my account credentials should be verified as "invalid"

