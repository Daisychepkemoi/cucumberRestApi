@tag
Feature: Comments
	Comments CRUD features

 Scenario: Get All Comments
	 Given get all Comments endpoint
	 When navigate to get all Comments endpoint
	 Then check if All comment data is returned

# Scenario: Create Comment
# 	 Given get all Comments endpoint
# 	 When pass data to the createComment endpoint
# 	 Then Validate that created comment data is returned

Scenario Outline: Create Comment
	 Given get all Comments endpoint
	 When The comment "<name>", "<email>","body" and other data is passed to the endpoint
	 Then Validate this endpoints <response> code is received 

Examples:
	 		 |name|email|body|response|
			||Jackie.com|This is a body|422|
			||||422|
			|jackie|||422|
			|jackie|mail.com||422|
			|Jackie|correctyyG9h@gmail.com|This is a body|201 |

Scenario: Get Comments Per Post
	 Given get all Comments endpoint
	 When navigate to get Comments per post endpoint
	 Then check if get Comments per post data is returned

# Scenario: Update Comment
# 	 Given get all Comments endpoint
# 	 When comment update data is passed
# 	 Then validate that the comment data is updated

Scenario Outline: Update Comment
	 Given get all Comments endpoint
	 When The comments "<name>", "<email>","body" and other data is passed to the endpoint
	 Then Validate this endpoints <response> code is received 

Examples:
	 		 |name|email|body|response|
			||Jackie.com|This is a body|200|
			||||200|
			|jackie|||200|
			|jackie|mail.com||200|
			|Jackie|correctcomyG9th@gmail.com|This is a body|200 |
 
Scenario: View One Comment
	 Given get all Comments endpoint
	 When navigate to the getOneComment endpoint
	 Then check if the comment data is returned

# Scenario: delete comment
# 	 Given get all Comments endpoint
# 	 When navigate to the deleteComment endpoint
# 	 Then check if the comment is successfully deleted