@Todos
Feature: View Todos Per User
 Scenario: View Todos Per User
	 Given get all Todos endpoint
	 When navigate to get one todo per user endpoint
     Then check if todos response code 200 is returned
	 Then check if todos response body contains USER_ID object

