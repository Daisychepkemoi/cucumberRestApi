#Author :dchepkemoi408@gmail.com 
@2_Posts
Feature: Get All One Users Post
 Scenario: Get All One Users Post
	 Given user has posts Endpoint
	 When navigate to get posts per user endpoint
	 Then check if response code 200 is returned
     Then check if responses UserId is equal to urlUserID


