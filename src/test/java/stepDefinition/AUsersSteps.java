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
import pojos.UsersR;

public class AUsersSteps {
	public UsersEndPoints endUserEndPoints;
	public Response response;
	public static int USER_ID;
	public static int rand;
	public static String email;
	@Given("get all users endpoint")
	public void setGetUsersEndPoint() {
		endUserEndPoints = new UsersEndPoints(DataAccess.getInstance().getBaseUrl(),DataAccess.getInstance().getToken());
		response = endUserEndPoints.getUsers();
		JsonPath jEvaluator = response.jsonPath();
		System.out.println("THIS IS THE CODE"+ jEvaluator.get("code"));
		Assert.assertEquals(jEvaluator.get("code"),200);
		
		
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
	}
//	@Given("add user endpoint")
	public void setAddUser() {
//		UsersR createUser = new UsersR("jack", "jacky@test.com", "Male", "Active") ; 
		
	}
	@When("pass data to the endpoint")
	public void sendAddUser() {
		int min = 1;
		int max = 1000;
		rand = ThreadLocalRandom.current().nextInt(min, max + 1);
		email = "smiley"+rand+"@test.com";
		UsersR createUser = new UsersR("jack",email, "Male", "Active") ; 
		IRestResponse<UsersR> restResponse =  endUserEndPoints.createUser(createUser);
		System.out.println("HELLOO"  + restResponse);
		
		response = restResponse.getResponse();
	}
	@Then("Validate that created user data is returned with response code") 
	public void verifyAddUser() {
		JsonPath jEvaluator = response.jsonPath();
		Assert.assertEquals(jEvaluator.get("code"),201);
//		throw new io.cucumber.java.PendingException();
	}
//	@Given(" update user endpoint")
	public void setUpdateUser() {
		UsersR createUser = new UsersR("jack", "jacky@test.com", "Male", "Active") ; 
		
	}
	@When("user update data is passed")
	public void sendUpdateUser() {
		UsersR updateUser = new UsersR("Jackie", email, "Male", "Active");
		IRestResponse<UsersR> restResponse = endUserEndPoints.updateUser(updateUser);
		response = restResponse.getResponse();
		
	}
	@Then("validate that the user data is updated") 
	public void verifyUpdateUser() {
		JsonPath jevaluator = response.jsonPath();
		Assert.assertEquals(jevaluator.get("code"),200);
	}
//	@Given("get On user endpoint")
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
	}
//	@Given("delete user endpoint")
	public void setDeleteUser() {
		UsersR createUser = new UsersR("jack", "jacky@test.com", "Male", "Active") ; 
		
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
