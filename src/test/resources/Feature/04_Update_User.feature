#Author: dchepkemoi408@gmail.com
@Users
Feature: Update User

Scenario Outline: UpdateOneUser
  	 Given You have get All users endpoint
	 When The "<email>" and other update data is passed to the endpoint
	 Then Validate <response> code is received and data is updated
     Then Validate response body contains "<variable>" u

	Examples:
            |email|response|variable|
	 		# |correct57@gmail.com|422|has already been taken|
			||422|can't be blank|
			|emaill44@gmail.com|200|email|








