@Comments
Feature: Get All Comments Per Post
Scenario: Get All Comments Per Post
	 Given get all Comments endpoint
	 When navigate to get Comments per post endpoint
	 Then check if comments response code 200 is returned
	 Then check response body contains post_ID object
