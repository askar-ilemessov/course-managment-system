Feature: Student

Background:
	Given I am on the Student page

#21
Scenario: Register in a course
	Given a course with the course code "TEST" exists
	When I input "TEST" as the course code
	And I press register
	Then I am registered in "TEST"

#21
Scenario: Course is full
	Given a course with the course code "TEST_FULL" exists
	Given I input "TEST_FULL" as the course code
	And the capacity is full
	And I press register
	Then I am not registered in "TEST_FULL"


#28
Scenario: Drop a course
	Given I am enrolled in "TEST"
	When I drop "TEST"
	Then I am not registered in "STU"
	And I am not in the class list for "TEST"

#30
Scenario: Submit a deliverable

