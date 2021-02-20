@Todos
Feature: Get All Todos
 Scenario: Get All Todos
	 Given get all Todos endpoint
	 When navigate to get all Todos endpoint
     Then check if todos response code 200 is returned
	 Then check response body contains todos object

