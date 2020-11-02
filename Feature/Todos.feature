@tag
Feature: Todos
	Todos CRUD features

 Scenario: Get All Todos
	 Given get all Todos endpoint
	 When navigate to get all Todos endpoint
	 Then check if All todos data is returned

 Scenario: Get all Todos per user
	 Given get todo per user endpoint
	 When navigate to get Todos per user endpoint
	 Then check if get Todos per user data is returned

 Scenario: Add todo
	 Given add todo endpoint
	 When pass data to the endpoint
	 Then Validate that created todo data is returned

Scenario: Update todo
	 Given update todo endpoint
	 When todo update data is passed
	 Then validate that the todo data is updated
 
Scenario: Get One todo, thats the just created todo
	 Given get On todo endpoint
	 When navigate to the endpoint
	 Then check if the todo data is returned

Scenario: delete todo
	 Given delete todo endpoint
	 When navigate to the endpoint
	 Then check if the todo is successfully deleted