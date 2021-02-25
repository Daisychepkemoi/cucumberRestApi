package apiEngine;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import dataAccess.DataAccess;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
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

		try {
            FileOutputStream outStr = new FileOutputStream(DataAccess.getInstance().getLogLocation(), true);
			PrintStream log = new PrintStream(outStr);
			RestAssured.baseURI = base_Url;
			request = RestAssured.given().filter(RequestLoggingFilter.logRequestTo(log)).filter(ResponseLoggingFilter.logResponseTo(log));
			request.header("Content-Type", "Application/json").header("Authorization", token);
		} catch(FileNotFoundException fnfe) { 
            System.out.println(fnfe.getMessage());
        } 

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
		return request.get(Routes.oneTodo(TODO_ID));
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
