Feature: Admin

Background:
	Given I am on the Admin page

#21
Scenario: Create a course
	When I input "DISCRETE MATHEMATICS" "2804" "A" "PROF" "FALL"
	And I press submit
	Then the "DISCRETE MATHEMATICS" course should be created

#28
Scenario: Delete a student
	Given there is a student named "Test Student"
	And "Test Student" is registered in "DISCRETE MATHEMATICS"
	When I delete "Test Student"
	Then "Test Student" should be removed from the database
	And "Test Student" should be removed from "DISCRETE MATHEMATICS" class list

#30
Scenario: Delete a course
	Given "Test Student" is registered in "DISCRETE MATHEMATICS"
	When I delete "DISCRETE MATHEMATICS"
	Then "DISCRETE MATHEMATICS" should be removed from the database
	And "Test Student" should no longer be registered in "DISCRETE MATHEMATICS"
