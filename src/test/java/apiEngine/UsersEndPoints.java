package apiEngine;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojos.UsersR;
import apiEngine.Routes;
//import model.Users;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

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
	public  IRestResponse<UsersR> createUser(UsersR createUser) {
	    Response response = request.body(createUser).post(Routes.users());
	    JsonPath jsonPathEvaluator = response.jsonPath();
	    int code = jsonPathEvaluator.get("code");
	    System.out.println("Hey DATA IS HERE   "+jsonPathEvaluator.get("$"));
	    if(code == 201 ) {
	    USER_ID  =jsonPathEvaluator.get("data.id");
	    System.out.println("Hey    "+jsonPathEvaluator.get("data"));
	    userData =response;
	    }
	    else {
	    }
	    
	    return new RestResponse<UsersR>(UsersR.class, response);
}	
	public Response getOneUser() {
		return request.get(Routes.oneUser(USER_ID));
	}
	public IRestResponse<UsersR> updateUser(UsersR UpdateUser){
		Response response = request.body(UpdateUser).patch(Routes.oneUser(USER_ID));
	    JsonPath jsonPathEvaluator = response.jsonPath();
	    int code = jsonPathEvaluator.get("code");
	    if(code == 201  ) {
		    USER_ID  =jsonPathEvaluator.get("data.id");
		    userData =jsonPathEvaluator.get("data");
		    }
		    else {
		    }
		return new RestResponse<UsersR>(UsersR.class, response);
	}
	public Response deleteUser() {
		return request.delete(Routes.oneUser(USER_ID));
	}
}
