#Author: dchepkemoi408@gmail.com
#Keywords Summary : implementing Cucumber
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Users
  Users CRUD Features

 Scenario: Get All Users
	 Given get all users endpoint
	 When navigate to get all users endpoint
	 Then check if All user data is returned with response code 
#
Scenario Outline: Add User
	 Given get all users endpoint
	 When The "<email>" and other data is passed to the endpoint
	 Then Validate <response> code is received 


Examples:
	 		|email|response|
	 		|correct3@gmail.com|422|
			||422|
			|email16@gmail.com|201|
			
#
Scenario Outline: UpdateOneUser
  	 Given get all users endpoint
	 When The "<email>" and other update data is passed to the endpoint
	 Then Validate <response> code is received and data is updated

	Examples:
	 		 |email|response|
	 		#  |correct1@gmail.com|200| //endpooint thrwoing internal server error if email alrady exists.
			||422|
			|gmail15@gmail.com|200|
# # # 
Scenario: Get One User, thats the just created user
	 Given get all users endpoint
	 When navigate to the getOne User endpoint
	 Then check if the user data is returned

#Scenario: delete User
#	 Given delete user endpoint
#	 When navigate to the delete User endpoint
#	 Then check if the user is successfully deleted





