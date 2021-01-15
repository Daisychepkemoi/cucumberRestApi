@tag
Feature: Posts
	Posts CRUD features

 Scenario: Get All Posts
	 Given get all posts endpoint
	 When navigate to get all posts endpoint
	 Then check if All post data is returned

Scenario: Add Posts
	 Given get all posts endpoint 
	 When pass data to the Add Post endpoint
	 Then Validate that created post data is returned
	 
Scenario: Get Posts Per User
	 Given get all posts endpoint
	 When navigate to get posts per user endpoint
	 Then check if get posts per user data is returned

Scenario: Update posts
	 Given get all posts endpoint
	 When post update data is passed
	 Then validate that the post data is updated
 
 Scenario: View One Post
	 Given get all posts endpoint
	 When navigate to the onePost endpoint
	 Then check if the post data is returned

#	 When navigate to the delete User endpoint
#	 Then check if the user is successfully deleted