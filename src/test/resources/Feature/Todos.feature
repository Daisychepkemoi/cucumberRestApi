Feature: Todos
	Todos CRUD features

 Scenario: Get All Todos
	 Given get all Todos endpoint
	 When navigate to get all Todos endpoint
	 Then check if All todos data is returned

Scenario: Create TODO
	 Given get all Todos endpoint
	 When pass data to the createTodo endpoint
	 Then Validate that created todo data is returned

Scenario: View ALL TODO per User
	 Given get all Todos endpoint
 	 When navigate to get Todos per user endpoint
	 Then check if get Todos per user data is returned

Scenario: Update TODO
	 Given get all Todos endpoint
	 When todo update data is passed
	 Then validate that the todo data is updated
 
 Scenario: View One TODO
	 Given get all Todos endpoint
	 When navigate to the getOntTodo endpoint
	 Then check if the todo data is returned

Scenario: Delete TODO
	 Given get all Todos endpoint
	 When navigate to the delete Todo endpoint
	 Then check if the todo is successfully deleted