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

public class GetAllUsers {
	public UsersEndPoints endUserEndPoints;
	public Response response;
	public static int USER_ID;
	public static int rand;
	String email;
	@Given("You have get All users endpoint")
	public void setGetUsersEndPoint() {
		endUserEndPoints = new UsersEndPoints(DataAccess.getInstance().getBaseUrl(),
				DataAccess.getInstance().getToken());

	}

	@When("navigate to get all users endpoint")
	public void sendGetAllUsers() {
		response = endUserEndPoints.getUsers();
	}
	@Then("check response code 200 is returned")
	public void verifyResponseCode200() {
		JsonPath jEvaluator = response.jsonPath();
		System.out.println("Get All Users Response Body" + jEvaluator.get("$"));
		Assert.assertEquals(200,jEvaluator.get("code"));
	}
	@Then("check response body contains email object")
	public void verifyGetAllUsersResponseBody(){
		JsonPath jEvaluator = response.jsonPath();
		Assert.assertTrue(jEvaluator.get("data").toString().contains("email"));
	}	
	@When("The {string} and other data is passed to the endpoint")
	public void sendAddUser(String mails) {
		int min = 1;
		int max = 1000;
		rand = ThreadLocalRandom.current().nextInt(min, max + 1);
		email = mails;
		UsersR createUser = new UsersR("jack", mails, "Male", "Active");
		IRestResponse<Users> restResponse = endUserEndPoints.createUser(createUser);
		response = restResponse.getResponse();
	}

	@Then("Validate {int} code is received")
	public void verifyAddUserResponseCode(int codee) {
		JsonPath jEvaluator = response.jsonPath();
		Assert.assertEquals( codee,jEvaluator.get("code"));
	}
	@Then("Validate response body contains {string} u")
	public void verifyAddUserResponseMessage(String messageResp) {
		JsonPath jEvaluator = response.jsonPath();
		int code = jEvaluator.get("code");
		System.out.println("THIS IS Add User Response Body " + jEvaluator.get("$"));
		if (code == 201 || code == 200) {
			Assert.assertEquals( email,jEvaluator.get("data.email"));
		}
		else {
			Assert.assertEquals(jEvaluator.get("data[0].message").toString(),messageResp);
		}

	}

	@When("navigate to the getOne User endpoint")
	public void sendGetOneUser() {
		response = endUserEndPoints.getOneUser();

	}

	@Then("check response body contains email just created")
	public void verifyGetOneUserResponseMsg() {
		JsonPath jevaluator = response.jsonPath();
		Assert.assertEquals(jevaluator.get("data[0].email"), email);
	}
	

	@When("The {string} and other update data is passed to the endpoint")
	public void sendUpdateUser(String mail) {
		UsersR updateUser = new UsersR("Jackie", mail, "Male", "Active");
		IRestResponse<Users> restResponse = endUserEndPoints.updateUser(updateUser);
		response = restResponse.getResponse();
		email = mail;

	}

	@Then("Validate {int} code is received and data is updated")
	public void verifyUpdateUser(int code) {
		JsonPath jevaluator = response.jsonPath();
		int returnedCode = jevaluator.get("code");
		Assert.assertEquals(returnedCode, code);
		if (returnedCode == 200) {
			Assert.assertEquals(jevaluator.get("data.email"), email);
		}

	}


	// // @Given("delete user endpoint")
	// public void setDeleteUser() {

	// }

	// // @When("navigate to the delete User endpoint")
	// public void sendDeleteUser() {
	// 	response = endUserEndPoints.deleteUser();

	// }

	// // @Then("check if the user is successfully deleted")
	// public void verifyDeleteUser() {
	// 	JsonPath jEvaluator = response.jsonPath();
	// 	Assert.assertEquals(jEvaluator.get("code"), 204);
	// }
}
