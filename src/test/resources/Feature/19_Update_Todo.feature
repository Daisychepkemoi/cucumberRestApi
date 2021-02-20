@Todos
Feature: Update Todo
Scenario Outline: Update Todo
	 Given get all Todos endpoint
	 When The todos "<title>" and other data is passed to the endpoint
	 Then Validate this todos endpoints <response> code is received 
	 Then Validate response body contains "<variable>" t 

Examples:
             |title|response|variable|
			 ||422|can't be blank|
             |This is a body|200|title|
 
 