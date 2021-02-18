#Author: dchepkemoi408@gmail.com
@AddUser
Feature: Add User

Scenario Outline: Add User
	 Given You have get All users endpoint
	 When The "<email>" and other data is passed to the endpoint
	 Then Validate <response> code is received 
	 Then Validate response body contains "<variable>" t

Examples:
	 		|email|response|variable|
	 		|correct57@gmail.com|422|has already been taken|
			||422|can't be blank|
			|emaill80@gmail.com|201|email|

