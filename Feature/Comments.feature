@tag
Feature: Comments
	Comments CRUD features

 Scenario: Get All Comments
	 Given get all Comments endpoint
	 When navigate to get all Comments endpoint
	 Then check if All comment data is returned

# Scenario: Get all Comments per post
#	 Given get comment per post endpoint
	 

# Scenario: Add comment
#	 Given add comment endpoint
	 When pass data to the createComment endpoint
	 Then Validate that created comment data is returned
	 
	 When navigate to get Comments per post endpoint
	 Then check if get Comments per post data is returned

#Scenario: Update comment
#	 Given update comment endpoint
	 When comment update data is passed
	 Then validate that the comment data is updated
 
#Scenario: Get One comment, thats the just created comment
#	 Given get On comment endpoint
	 When navigate to the getOneComment endpoint
	 Then check if the comment data is returned

#Scenario: delete comment
#	 Given delete comment endpoint
	 When navigate to the deleteComment endpoint
	 Then check if the comment is successfully deleted