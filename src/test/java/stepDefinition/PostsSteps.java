package stepDefinition;

import org.junit.Assert;

import apiEngine.IRestResponse;
import apiEngine.PostsEndPoints;
import apiEngine.UsersEndPoints;
import dataAccess.DataAccess;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import model.Posts;
import pojos.PostsR;

public class PostsSteps {
	public static PostsEndPoints postsEndPoints;
	public static UsersEndPoints EndPoints;
	public static Response response;
	
	@Given("user has posts Endpoint")
	public void setGetAllPosts() {
		postsEndPoints = new PostsEndPoints(DataAccess.getInstance().getBaseUrl(), DataAccess.getInstance().getToken());
	}
	@When("navigate to get all posts endpoint")
	public void sendGetAllPosts() {
		response = postsEndPoints.getPosts();
	}
	@Then("check if response code 200 is returned")
	public void verifyPostsResponseCode200() {
		JsonPath jEvaluator = response.jsonPath();
		int code = jEvaluator.get("code");
		System.out.println("Get All Posts Body is:" +jEvaluator.get("$"));
		Assert.assertEquals(code, 200);
	}
	@Then("check if response has post in data object")
	public void verifyGetAllPostsResponseBody() {
		JsonPath jEvaluator = response.jsonPath();
		Assert.assertTrue(jEvaluator.get("data").toString().contains("body"));
	}

	@When("The post {string}, {string} and other data is passed to the endpoint")
	public void sendAddPost(String title, String body) {
		PostsR createPost = new PostsR(apiEngine.UsersEndPoints.USER_ID, title, body);
		IRestResponse<Posts> createnewPost = postsEndPoints.createPost(createPost);
		response = createnewPost.getResponse();
		Assert.assertTrue(createnewPost.isSuccessful());
	}

	@Then("Validate this {int} code is received")
	public void verifyAddPostResponseCode(int codes) {
		JsonPath jEvaluator = response.jsonPath();
		System.out.println("This is the response" + jEvaluator.get("$"));
		int code = jEvaluator.get("code");

		Assert.assertEquals(code, codes);

	}
	@Then("Validate response body contains {string} p")
	public void verifyAddPostResponseBody(String ResponseMsg) {
		JsonPath jEvaluator = response.jsonPath();
		int code = jEvaluator.get("code");
		if (code == 201 || code == 200) {
			int user_id = jEvaluator.get("data.user_id");
			Assert.assertEquals(user_id, apiEngine.UsersEndPoints.USER_ID);
		}
		else {
			Assert.assertEquals(ResponseMsg, jEvaluator.get("data[0].message"));
		}

	}
	@When("navigate to the onePost endpoint")
	public void sendGetOnePost() {
		response = postsEndPoints.getCreatedPost();

	}

	@Then("check if responses postID is equal to urlID")
	public void verifyGetOnepost() {
		JsonPath JEvaluator = response.jsonPath();
		int id = JEvaluator.get("data.id");
		Assert.assertEquals(id, apiEngine.PostsEndPoints.POST_ID);
	}
	@When("navigate to get posts per user endpoint")
	public void sendGetPostPerUser() {
		response = postsEndPoints.getPostsPerUser();

	}

	@Then("check if responses UserId is equal to urlUserID")
	public void verifyGetPostPerUser() {
		JsonPath JEvaluator = response.jsonPath();
		System.out.println("One Users Posts Are :: " + JEvaluator.get("$"));
		int user_id = JEvaluator.get("data[0].user_id");
		Assert.assertEquals(user_id, apiEngine.UsersEndPoints.USER_ID);
	}

	@When("The post {string}, {string} and other data is passed to the update post endpoint")
	public void sendUpdatePost(String title, String body) {
		PostsR updatePost = new PostsR(apiEngine.UsersEndPoints.USER_ID, title, body);
		IRestResponse<Posts> updatepost = postsEndPoints.updatePost(updatePost);
		response = updatepost.getResponse();
		System.out.println("This is update response" + response.asString());

	}

	// @Then("Validate that this {int} code is received")
	// public void verifyUpdatePost(int codess) {
	// 	JsonPath JEvaluator = response.jsonPath();
	// 	int code = JEvaluator.get("code");

	// 	Assert.assertEquals(code, codess);
	// 	if (codess == 200) {
	// 		int user_id = JEvaluator.get("data.user_id");
	// 		Assert.assertEquals(user_id, apiEngine.UsersEndPoints.USER_ID);
	// 	}

	// }

	

// 	// @When("navigate to the delete User endpoint")
// 	// public void sendDeleteUser() {
// 	// response = postsEndPoints.deleteUserPosts();
// 	//
// 	// }
// 	// @Then("check if the user is successfully deleted")
// 	// public void verifyDeleteUser() {
// 	// JsonPath jEvaluator = response.jsonPath();
// 	// Assert.assertEquals(jEvaluator.get("code"),204);
// 	// }
}
