Feature: Student

Background:
	Given I am on the Student page

#T33
Scenario: Register in a course
	Given a course with the course code "TEST" exists
	When I input "TEST" as the course code
	And I press register
	Then I am registered in "TEST"
	
#T35
Scenario: Student already registered
	Given a course with the course code "TEST_REGISTERED" exists
	And I am enrolled in "TEST_REGISTERED"
	When I input "TEST_REGISTERED" as the course code
	Then I am not registered again into "TEST_REGISTERED"

#T37
Scenario: Course is full
	Given a course with the course code "TEST_FULL" exists
	Given I input "TEST_FULL" as the course code
	And the capacity is full
	Then I am not registered in "TEST_FULL"

#T48
Scenario: Submit a deliverable
	Given I am enrolled in "TEST"

#T38
Scenario: Drop a course
	Given I am enrolled in "TEST"
	When I drop "TEST"
	Then I am not registered in "TEST"
	And I am not in the class list for "TEST"
