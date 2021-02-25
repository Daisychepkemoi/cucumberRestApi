@Comments
Feature: Get All Comments

 Scenario: Get All Comments
	 Given get all Comments endpoint
	 When navigate to get all Comments endpoint
	 Then check if comments response code 200 is returned
	 Then check response body contains comment object

# Scenario: Create Comment
# 	 Given get all Comments endpoint
# 	 When pass data to the createComment endpoint
# 	 Then Validate that created comment data is returned

