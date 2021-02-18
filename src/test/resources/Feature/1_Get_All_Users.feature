#Author: dchepkemoi408@gmail.com
@GetAllUsers
Feature: GetAllUsers
	
 Scenario: Get All Users response Code
	 Given You have get All users endpoint
	 When navigate to get all users endpoint
	 Then check response code 200 is returned
	 Then check response body contains email object



