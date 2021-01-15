package stepDefinition;


import java.util.concurrent.ThreadLocalRandom;

import org.junit.Assert;

import apiEngine.IRestResponse;
import apiEngine.UsersEndPoints;
import dataAccess.DataAccess;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import model.Users;
import pojos.UsersR;

public class UsersSteps {
	public UsersEndPoints endUserEndPoints;
	public Response response;
	public static int USER_ID;
	public static int rand;
	public static String email;
	@Given("get all users endpoint")
	public void setGetUsersEndPoint() {
		endUserEndPoints = new UsersEndPoints(DataAccess.getInstance().getBaseUrl(),DataAccess.getInstance().getToken());
	
	}
	@When("navigate to get all users endpoint")
	public void sendGetUsers() {
		response = endUserEndPoints.getUsers();
	}
	@Then("check if All user data is returned with response code")
	public void verifyGetUsersResponse() {
		JsonPath jEvaluator = response.jsonPath();
		System.out.println("THIS IS THE CODE"+ jEvaluator.get("code"));
		Assert.assertEquals(jEvaluator.get("code"),200);
//		JSONObject obj = new JSONObject(response);
//		Assert.assertTrue("response size is greater than 0",obj.length()>0);
	}
//	@Given("add user endpoint")
	public void setAddUser() {
//		UsersR createUser = new UsersR("jack", "jacky@test.com", "Male", "Active") ; 
		
	}
	@When("The {string} and other data is passed to the endpoint")
	public void sendAddUser(String mails) {
		int min = 1;
		int max = 1000;
		rand = ThreadLocalRandom.current().nextInt(min, max + 1);
		email = mails;
		UsersR createUser = new UsersR("jack",mails, "Male", "Active") ; 
		IRestResponse<Users> restResponse =  endUserEndPoints.createUser(createUser);
		System.out.println("HELLOO"  + restResponse);
		
		response = restResponse.getResponse();
		System.out.println("this is the response Body"+response.asString());
	} // extracting string and integer REGEX \"([^\"]*)\"$  (-?\\d+)

	@Then("Validate {int} code is received") 
	public void verifyAddUser(int codee) {
		JsonPath jEvaluator = response.jsonPath();
		Assert.assertEquals(jEvaluator.get("code"),codee);
		// Assert.assertEquals(jEvaluator.get("data.email"),email);
	}
	// @Given(" update user endpoint")
	public void setUpdateUser() {
		UsersR createUser = new UsersR("jack", "jacky@test.com", "Male", "Active") ; 
		
	}
	@When("The {string} and other update data is passed to the endpoint")
	public void sendUpdateUser(String mail) {
		UsersR updateUser = new UsersR("Jackie", mail, "Male", "Active");
		IRestResponse<Users> restResponse = endUserEndPoints.updateUser(updateUser);
		response = restResponse.getResponse();
		System.out.println("UpdateResponse"+response.asString());
		
	}
	@Then("Validate {int} code is received and data is updated") 
	public void verifyUpdateUser(int code) {
		JsonPath jevaluator = response.jsonPath();
		Assert.assertEquals(jevaluator.get("code"),code);
		// Assert.assertEquals(jevaluator.get("data.name"),"Jackie");
	}
	@Given("get On user endpoint")
	public void setGetOneUser() {
		response=endUserEndPoints.getOneUser();
		
	}
	@When("navigate to the getOne User endpoint")
	public void sendGetOneUser() {
		response=endUserEndPoints.getOneUser();
		
	}
	@Then("check if the user data is returned") 
	public void verifyGetOneUser() {
		JsonPath jevaluator = response.jsonPath();
		Assert.assertEquals(jevaluator.get("code"),200);
//		JSONObject obj = new JSONObject(response);
//		Assert.assertTrue("response size is greater than 0",obj.length()>0);
	}
//	@Given("delete user endpoint")
	public void setDeleteUser() {
		
	}
//	@When("navigate to the delete User endpoint")
	public void sendDeleteUser() {
		response = endUserEndPoints.deleteUser();
		
	}
//	@Then("check if the user is successfully deleted") 
	public void verifyDeleteUser() {
		JsonPath jEvaluator = response.jsonPath();
		Assert.assertEquals(jEvaluator.get("code"),204);
	}
}
