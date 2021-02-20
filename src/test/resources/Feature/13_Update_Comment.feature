@3_Comments
Feature: Update Comment

Scenario Outline: Update Comment
	 Given get all Comments endpoint
	 When The comments "<body>" and other data is passed to the endpoint
	 Then Validate <response> code is received for comments
	 Then Validate response body contains "<variable>" c 

Examples:
	 		|body|response|variable|
			 ||422|can't be blank|
			 |This is a body|200 |created_at|

