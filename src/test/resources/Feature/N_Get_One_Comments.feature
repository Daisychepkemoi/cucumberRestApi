@3_Comments
Feature: Get All Comments Per Post
Scenario: View One Comment
	 Given get all Comments endpoint
	 When navigate to the getOneComment endpoint
	 Then check if comments response code 200 is returned
	 Then check response body contains commentID object

