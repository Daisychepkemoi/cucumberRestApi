@Comments
Feature: Add Comment
Scenario Outline: Add Comment
	 Given get all Comments endpoint
	 When The comment "<body>" config and other data is passed to the endpoint
	 Then Validate <response> code is received for comments
     Then Validate response body contains "<variable>" c

Examples:
	 		 |body|response|variable|
			 ||422|can't be blank|
			 |This is a body|201 |created_at|

