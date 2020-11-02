#Author: your.email@your.domain.com
#Keywords Summary :
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
	 When naviate to get all users endpoint
	 Then check if All user data is returned

 Scenario: Add user
	 Given add user endpoint
	 When pass data to the endpoint
	 Then Validate that created user data is returned

Scenario: UpdateOneUser
	 Given update user endpoint
	 When user update data is passed
	 Then validate that the user data is updated
 
Scenario: Get One User, thats the just created user
	 Given get On user endpoint
	 When navigate to the endpoint
	 Then check if the user data is returned

Scenario: delete User
	 Given delete user endpoint
	 When navigate to the endpoint
	 Then check if the user is successfully deleted





