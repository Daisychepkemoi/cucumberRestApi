@Todos
Feature: Delete Todo
Scenario: Delete Todo
	 Given get all Todos endpoint
	 When navigate to the delete Todo endpoint
     Then check if todos response code 204 is returned
     Then check if todo is successfully deleted 