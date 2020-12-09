Feature: Student

Background:
	Given I am on the Student page

#21
Scenario: Register in a course
	Given a course with the course code "2804" exists
	When I input "2804" as the course code
	And I press submit
	Then I am registered in "DISCRETE MATHEMATICS"

#28
Scenario: Drop a course
	Given there is a student named "Test Student"
	And "Test Student" is registered in "DISCRETE MATHEMATICS"
	When I delete "Test Student" from "Students"
	Then "Test Student" should be removed from the "Students" database
	And "Test Student" should be removed from "DISCRETE MATHEMATICS" class list

#30
Scenario: Submit a deliverable
	Given "Test Student" is registered in "DISCRETE MATHEMATICS"
	When I delete "DISCRETE MATHEMATICS" from "Courses"
	Then "DISCRETE MATHEMATICS" should be removed from the "Courses" database
	And "Test Student" should no longer be registered in "DISCRETE MATHEMATICS"
