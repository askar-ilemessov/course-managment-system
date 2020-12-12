Feature: Admin

Background:
	Given I am on the Admin page

#T21, T25
Scenario: Create a course
	When I input "DISCRETE MATHEMATICS" "2804" "A" "FALL"
	And I assign "PROF" as the professor
	And I press submit
	Then the "DISCRETE MATHEMATICS" course should be created
	
#T24
Scenario: Create a course that already existed
	Given "DISCRETE MATHEMATICS" exists
	When I input "DISCRETE MATHEMATICS" "2804" "A" "FALL"
	And I assign "PROF" as the professor
	And I press submit
	Then the "DISCRETE MATHEMATICS" course should not be created

#T28
Scenario: Delete a student
	Given there is a student named "Test Student"
	And "Test Student" is registered in "DISCRETE MATHEMATICS"
	When I delete "Test Student" from "Students"
	Then "Test Student" should be removed from the "Students" database
	And "Test Student" should be removed from "DISCRETE MATHEMATICS" class list

#T30
Scenario: Delete a course
	Given "Test Student" is registered in "DISCRETE MATHEMATICS"
	When I delete "DISCRETE MATHEMATICS" from "Courses"
	Then "DISCRETE MATHEMATICS" should be removed from the "Courses" database
	And "Test Student" should no longer be registered in "DISCRETE MATHEMATICS"
