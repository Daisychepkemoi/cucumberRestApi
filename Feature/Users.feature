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
	 Given navigate and get all users data
	 When user is added
	 Then check if user is added
	 
Scenario: ViewOneUser
	 Given navigate and get all users data
	 When One created user is gotten
	 Then Validate that created user is gotten
	 #	 Given navigate and get one users data

Scenario: UpdateOneUser
	 Given navigate and get all users data
	 When user data is updated
	 Then validate that the user data is updated
 
#Scenario: Get One User, thats the just created user
#	 When user is deleted
#	 Then check if the user is infact deleted