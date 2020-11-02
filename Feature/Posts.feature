@tag
Feature: Posts
	Posts CRUD features

 Scenario: Get All Posts
	 Given get all posts endpoint
	 When navigate to get all posts endpoint
	 Then check if All post data is returned

 Scenario: Get all posts per user
	 Given get post per user endpoint
	 When navigate to get posts per user endpoint
	 Then check if get posts per user data is returned

 Scenario: Add post
	 Given add post endpoint
	 When pass data to the endpoint
	 Then Validate that created post data is returned

Scenario: Update post
	 Given update post endpoint
	 When post update data is passed
	 Then validate that the post data is updated
 
Scenario: Get One post, thats the just created post
	 Given get On post endpoint
	 When navigate to the endpoint
	 Then check if the post data is returned

Scenario: delete post
	 Given delete post endpoint
	 When navigate to the endpoint
	 Then check if the post is successfully deleted