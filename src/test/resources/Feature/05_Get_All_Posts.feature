# Author :dchepkemoi408@gmail.com 
@Posts
Feature: Get All Posts
 Scenario: Get All Posts
	 Given user has posts Endpoint
	 When navigate to get all posts endpoint
	 Then check if response code 200 is returned
     Then check if response has post in data object
