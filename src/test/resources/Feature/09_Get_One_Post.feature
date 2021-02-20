#Author :dchepkemoi408@gmail.com 
@2_Posts
Feature: Get One Post
 Scenario: View One Post
	 Given user has posts Endpoint
	 When navigate to the onePost endpoint
	 Then check if response code 200 is returned
     Then check if responses postID is equal to urlID


