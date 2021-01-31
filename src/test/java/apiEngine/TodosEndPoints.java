package apiEngine;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.Todos;
import pojos.TodosR;

public class TodosEndPoints {

	public UsersEndPoints endPoints;
	public static int TODO_ID;
	public static Response todoData;
	public static RequestSpecification request;

	public TodosEndPoints(String base_Url, String token) {
		RestAssured.baseURI = base_Url;
		request = RestAssured.given();//.log().all();
		request.header("Content-Type", "Application/json").header("Authorization", token);

	}

	public Response getTodos() {
		return request.get(Routes.todos());
	}

	public Response getTodosPerUser() {
		return request.get(Routes.todosPerUser(UsersEndPoints.USER_ID));
	}

	public IRestResponse<Todos> createTodo(TodosR createTodo) {
		Response response = request.body(createTodo).post(Routes.todosPerUser(UsersEndPoints.USER_ID));
		JsonPath jsonPathEvaluator = response.jsonPath();
		int code = jsonPathEvaluator.get("code");
		if (code == 201) {
			TODO_ID = jsonPathEvaluator.get("data.id");
			todoData = response;
		} else {
		}
		return new RestResponse<Todos>(Todos.class, response, todoData);
	}

	public Response getCreatedTodo() {
		return UsersEndPoints.request.get(Routes.oneTodo(TODO_ID));
	}

	public IRestResponse<Todos> updateTodo(TodosR updateTodos) {
		Response response = request.body(updateTodos).patch(Routes.oneTodo(TODO_ID));
		todoData = response;
		return new RestResponse<Todos>(Todos.class, response, todoData);
	}

	public Response deleteTodo() {
		return request.delete(Routes.oneTodo(TODO_ID));
	}
}
