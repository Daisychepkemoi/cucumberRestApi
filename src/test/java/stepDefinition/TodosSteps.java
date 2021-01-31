package stepDefinition;

import org.junit.Assert;

import apiEngine.IRestResponse;
import apiEngine.TodosEndPoints;
import dataAccess.DataAccess;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import model.Todos;
import pojos.TodosR;

public class TodosSteps {
	public TodosEndPoints todoEndPoints;
	public Response response;

	@Given("get all Todos endpoint")
	public void setGetAllTodos() {
		todoEndPoints = new TodosEndPoints(DataAccess.getInstance().getBaseUrl(), DataAccess.getInstance().getToken());
	}

	@When("navigate to get all Todos endpoint")
	public void sendGetAllTodos() {
		response = todoEndPoints.getTodos();

	}

	@Then("check if All todos data is returned")
	public void verifyGetAllTodos() {
		JsonPath jEvaluator = response.jsonPath();
		int code = jEvaluator.get("code");
		Assert.assertEquals(code, 200);
	}

	@When("The todo {string} and other data is passed to the endpoint")
	public void sendAddodo(String title) {
		TodosR createTodo = new TodosR(apiEngine.UsersEndPoints.USER_ID, title, true);
		IRestResponse<Todos> CreateNewTodo = todoEndPoints.createTodo(createTodo);
		response = CreateNewTodo.getResponse();

	}

	@Then("Validate this todos endpoints {int} code is received")
	public void verifyAddTodo(int codees) {
		JsonPath jEvaluator = response.jsonPath();
		int code = jEvaluator.get("code");
		Assert.assertEquals(codees, code);
		if (code == 201 || code == 200) {
			int user_id = jEvaluator.get("data.user_id");
			Assert.assertEquals(user_id, apiEngine.UsersEndPoints.USER_ID);
		}

	}

	@When("navigate to get Todos per user endpoint")
	public void sendGetTodoPerUser() {
		response = todoEndPoints.getTodosPerUser();

	}

	@Then("check if get Todos per user data is returned")
	public void verifyGetTodoPerUser() {
		JsonPath jEvaluator = response.jsonPath();
		int code = jEvaluator.get("code");
		int user_id = jEvaluator.get("data[0].user_id");
		Assert.assertEquals(code, 200);
		Assert.assertEquals(user_id, apiEngine.UsersEndPoints.USER_ID);
	}

	@When("The todos {string} and other data is passed to the endpoint")
	public void sendUpdateTodo(String title) {
		TodosR updateTodo = new TodosR(apiEngine.UsersEndPoints.USER_ID, title, true);
		IRestResponse<Todos> updatenewTodo = todoEndPoints.updateTodo(updateTodo);
		response = updatenewTodo.getResponse();

	}
	// @Then("validate that the todo data is updated")
	// public void VerifyUpdateTodo() {
	// JsonPath jEvaluator = response.jsonPath();
	// int code = jEvaluator.get("code");
	// int user_id = jEvaluator.get("data.id");
	// Assert.assertEquals(code,200);
	// Assert.assertEquals(user_id,apiEngine.TodosEndPoints.TODO_ID);
	// }

	@When("navigate to the getOntTodo endpoint")
	public void sendGetOneTodo() {
		response = todoEndPoints.getCreatedTodo();

	}

	@Then("check if the todo data is returned")
	public void VerifyGetOneTodo() {
		JsonPath jEvaluator = response.jsonPath();
		int code = jEvaluator.get("code");
		int user_id = jEvaluator.get("data.id");
		Assert.assertEquals(code, 200);
		Assert.assertEquals(user_id, apiEngine.TodosEndPoints.TODO_ID);
	}

	@When("navigate to the delete Todo endpoint")
	public void sendDeleteTodo() {
		response = todoEndPoints.deleteTodo();

	}

	@Then("check if the todo is successfully deleted")
	public void verifyDeleeTodoResponse() {
		JsonPath jEvaluator = response.jsonPath();
		int code = jEvaluator.get("code");
		Assert.assertEquals(code, 204);
	}
}
