@tag
Feature: Posts
	Posts CRUD features

 Scenario: Get All Posts
	 Given get all posts endpoint
	 When navigate to get all posts endpoint
	 Then check if All post data is returned

# Scenario: Add Posts
# 	 Given get all posts endpoint 
# 	 When pass data to the Add Post endpoint
# 	 Then Validate that created post data is returned

Scenario Outline: Add Posts
	 Given get all posts endpoint
	 When The post "<title>", "<body>" and other data is passed to the endpoint
	 Then Validate this <response> code is received 


Examples:
	 		 |title|body|response|
			||This is a body|422|
			|This is a title||422|
			|||422|
			|This is a title|This is a body|201 |
	 
Scenario: Get Posts Per User
	 Given get all posts endpoint
	 When navigate to get posts per user endpoint
	 Then check if get posts per user data is returned

Scenario Outline: Update Posts
	 Given get all posts endpoint
	 When The post "<title>", "<body>" and other data is passed to the update post endpoint
	 Then Validate that this <response> code is received 

Examples:
	 		 |title|body|response|
			||This is a body|200|
			|This is a title||200|
			|||200|
			|This is a title|This is a body|200 |
 
 Scenario: View One Post
	 Given get all posts endpoint
	 When navigate to the onePost endpoint
	 Then check if the post data is returned

#	 When navigate to the delete User endpoint
#	 Then check if the user is successfully deleted
