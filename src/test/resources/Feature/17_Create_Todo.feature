@Todos
Feature: Create Todo
Scenario Outline: Create Todo
	 Given get all Todos endpoint
	 When The todo "<title>" and other data is passed to the endpoint
	 Then Validate this todos endpoints <response> code is received
     Then Validate response body contains "<variable>" t


Examples:
	 		 |title|response|variable|
			 ||422|can't be blank|
             |This is a body|201|title|
			
