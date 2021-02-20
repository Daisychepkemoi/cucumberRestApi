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
	@Then("check if todos response code 200 is returned")
	public void verifyTodosResponseCode200() {
		JsonPath jEvaluator = response.jsonPath();
		int code = jEvaluator.get("code");
		System.out.println("Get All Todos Body is:" +jEvaluator.get("$"));
		Assert.assertEquals(code, 200);
	}

	@Then("check response body contains todos object")
	public void verifyGetAllTodosResponseMsg() {
		JsonPath jEvaluator = response.jsonPath();
		Assert.assertTrue(jEvaluator.get("data").toString().contains("title"));
	}

	@When("The todo {string} and other data is passed to the endpoint")
	public void sendAddTodo(String title) {
		TodosR createTodo = new TodosR(apiEngine.UsersEndPoints.USER_ID, title, true);
		IRestResponse<Todos> CreateNewTodo = todoEndPoints.createTodo(createTodo);
		response = CreateNewTodo.getResponse();
		Assert.assertTrue(CreateNewTodo.isSuccessful());
	}

	@Then("Validate this todos endpoints {int} code is received")
	public void verifyAddTodoResponseCode(int codees) {
		JsonPath jEvaluator = response.jsonPath();
		int code = jEvaluator.get("code");
		Assert.assertEquals(codees, code);

	}
	@Then("Validate response body contains {string} t")
	public void verifyAddTodo(String ResponseMsg) {
		JsonPath jEvaluator = response.jsonPath();
		int code = jEvaluator.get("code");
		if (code == 201 || code == 200) {
			int user_id = jEvaluator.get("data.user_id");
			Assert.assertEquals(user_id, apiEngine.UsersEndPoints.USER_ID);
		}
		else{
			String Msg = jEvaluator.get("data[0].message");
			Assert.assertEquals(Msg, ResponseMsg);
		}

	}

	@When("navigate to get one todo per user endpoint")
	public void sendGetTodoPerUser() {
		response = todoEndPoints.getTodosPerUser();
	}

	@Then("check if todos response body contains USER_ID object")
	public void verifyGetTodoPerUser() {
		JsonPath jEvaluator = response.jsonPath();
		int user_id = jEvaluator.get("data[0].user_id");
		System.out.println("This is the TODOUser ID:: "+ user_id);
		Assert.assertEquals(user_id, apiEngine.UsersEndPoints.USER_ID);
	}

	@When("The todos {string} and other data is passed to the endpoint")
	public void sendUpdateTodo(String title) {
		TodosR updateTodo = new TodosR(apiEngine.UsersEndPoints.USER_ID, title, true);
		IRestResponse<Todos> updatenewTodo = todoEndPoints.updateTodo(updateTodo);
		response = updatenewTodo.getResponse();

	}

	@When("navigate to the getOntTodo endpoint")
	public void sendGetOneTodo() {
		response = todoEndPoints.getCreatedTodo();

	}
	@Then("check if GETOne todos response body contains USER_ID object")
	public void verifyGetOneTodoPerUser() {
		JsonPath jEvaluator = response.jsonPath();
		int user_id = jEvaluator.get("data.user_id");
		System.out.println("This is the TODOUser ID:: "+ user_id);
		Assert.assertEquals(user_id, apiEngine.UsersEndPoints.USER_ID);
	}
	// @Then("check if the todo data is returned")
	// public void VerifyGetOneTodo() {
	// 	JsonPath jEvaluator = response.jsonPath();
	// 	int code = jEvaluator.get("code");
	// 	int user_id = jEvaluator.get("data.id");
	// 	Assert.assertEquals(code, 200);
	// 	Assert.assertEquals(user_id, apiEngine.TodosEndPoints.TODO_ID);
	// }

	@When("navigate to the delete Todo endpoint")
	public void sendDeleteTodo() {
		response = todoEndPoints.deleteTodo();

	}

	@Then("check if todos response code 204 is returned")
	public void verifyDeleteTodoResponseCode200() {
		JsonPath jEvaluator = response.jsonPath();
		System.out.println("Get Delete Todo Response Body" + jEvaluator.get("$"));
		Assert.assertEquals(204,jEvaluator.get("code"));
	}
	@Then("check if todo is successfully deleted")
	public void verifyDeleteTodo() {
		JsonPath eval = response.jsonPath();
		Assert.assertTrue(eval.get("$").toString().contains("null"));
	}
}
