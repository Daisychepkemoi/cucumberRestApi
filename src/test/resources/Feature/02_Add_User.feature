#Author: dchepkemoi408@gmail.com
@Users
Feature: Add User

Scenario Outline: Add User
	 Given You have get All users endpoint
	 When The "<email>" and other data is passed to the endpoint
	 Then Validate <response> code is received 
	 Then Validate response body contains "<variable>" u

Examples:
	 		|email|response|variable|
	 		|correct57@gmail.com|422|has already been taken|
			||422|can't be blank|
			|emaill44@gmail.com|201|email|

