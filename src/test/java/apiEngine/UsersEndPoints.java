package apiEngine;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.Users;
import pojos.UsersR;

public class UsersEndPoints {
	public static int USER_ID;
	public static Response userData;
	public static String EMAIL;
	public static RequestSpecification request;

	public UsersEndPoints(String base_Url, String token) {
		RestAssured.baseURI = base_Url;
		request = RestAssured.given().log().all();
		request.header("Content-Type", "Application/json").header("Authorization", token);

	}

	public Response getUsers() {
		return request.get(Routes.users());
	}

	public IRestResponse<Users> createUser(UsersR createUser) {
		Response response = request.body(createUser).post(Routes.users());
		JsonPath jsonPathEvaluator = response.jsonPath();
		int code = jsonPathEvaluator.get("code");
		if (code == 201) {
			USER_ID = jsonPathEvaluator.get("data.id");
			userData = response;
			EMAIL = jsonPathEvaluator.get("data.email");
		} else {
		}

		return new RestResponse<Users>(Users.class, response, userData);
	}

	public Response getOneUser() {
		return request.get(Routes.oneUser(USER_ID));
	}

	public IRestResponse<Users> updateUser(UsersR updateUser) {
		Response response = request.body(updateUser).patch(Routes.oneUser(USER_ID));
		JsonPath jsonPathEvaluator = response.jsonPath();
		int ResponseCode = jsonPathEvaluator.get("code");
		if (ResponseCode == 200) {
			USER_ID = jsonPathEvaluator.get("data.id");
			userData = response;
			EMAIL = jsonPathEvaluator.get("data.email");
		} else {
		}
		return new RestResponse<Users>(Users.class, response, userData);
	}

	public Response deleteUser() {
		return request.delete(Routes.oneUser(USER_ID));

	}
}
