@3_Comments
Feature: Delete Comment
Scenario: Delete comment
	 Given get all Comments endpoint
	 When navigate to the deleteComment endpoint
     Then check if comments response code 204 is returned
     Then check if the comment is successfully deleted 
