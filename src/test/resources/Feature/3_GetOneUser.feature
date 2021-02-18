#Author: dchepkemoi408@gmail.com
@getOneUser
Feature: Get One User
Scenario: Get One User, thats the just created user
	 Given You have get All users endpoint
	 When navigate to the getOne User endpoint
	 Then check response code 200 is returned
	 Then check response body contains email just created

#Scenario: delete User
#	 Given delete user endpoint
#	 When navigate to the delete User endpoint
#	 Then check if the user is successfully deleted





