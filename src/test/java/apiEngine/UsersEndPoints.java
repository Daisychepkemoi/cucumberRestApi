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

	public static RequestSpecification request;
	public UsersEndPoints(String base_Url, String token) {
		RestAssured.baseURI = base_Url;
		request = RestAssured.given().log().all();
		request.header("Content-Type","Application/json").header("Authorization",token);
		
		
	}
	public Response getUsers() {
		return request.get(Routes.users());
	}
	public  IRestResponse<Users> createUser(UsersR createUser) {
		Response response = request.body(createUser).post(Routes.users());
		// System.out.println("we are here now");
	    JsonPath jsonPathEvaluator = response.jsonPath();
	    int code = jsonPathEvaluator.get("code");
	    // System.out.println("Hey DATA IS HERE   "+jsonPathEvaluator.get("$"));
	    if(code == 201 ) {
	    USER_ID  =jsonPathEvaluator.get("data.id");
	    System.out.println("Hey    "+jsonPathEvaluator.get("data"));
	    userData =response;
	    }
	    else {
	    }
	    
	    return new RestResponse<Users>(Users.class, response,userData);
}	
	public Response getOneUser() {
		return request.get(Routes.oneUser(USER_ID));
	}
	public IRestResponse<Users> updateUser(UsersR updateUser){
		System.out.println("We are here Now");
		Response response = request.body(updateUser).patch(Routes.oneUser(USER_ID));
		System.out.println("An now here" + response.asString());
	    JsonPath jsonPathEvaluator = response.jsonPath();
		int code = jsonPathEvaluator.get("code");
		System.out.println("Hey DATA IS HERE   "+jsonPathEvaluator.get("$"));
	    if(code == 201  ) {
		    USER_ID  =jsonPathEvaluator.get("data.id");
		    userData =jsonPathEvaluator.get("data");
		    }
		    else {
		    }
		return new RestResponse<Users>(Users.class, response,userData);
	}
	public Response deleteUser() {
		return request.delete(Routes.oneUser(USER_ID));
	}
}
