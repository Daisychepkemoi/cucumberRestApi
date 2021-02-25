# Author :dchepkemoi408@gmail.com 
@Posts
Feature: Update Posts
Scenario Outline: Update Posts
	 Given user has posts Endpoint
	 When The post "<title>", "<body>" and other data is passed to the update post endpoint
	 Then Validate this <response> code is received
	 Then Validate response body contains "<variable>" p 

Examples: 
            |title|body|response|variable|
			||This is a body|422|can't be blank|
			|This is a title||422|can't be blank|
			|||422|can't be blank|
			|This is a title|This is a body|200 |created_at|
 
 
