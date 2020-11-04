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

public class BPostsSteps {
	public static PostsEndPoints postsEndPoints;
	public static UsersEndPoints EndPoints;
	public static Response response;
	
	
	@Given("get all posts endpoint")
	public void setGetAllPosts() {
		postsEndPoints = new PostsEndPoints(DataAccess.getInstance().getBaseUrl(),DataAccess.getInstance().getToken());
	}
	
	
	@When("pass data to the Add Post endpoint")
	public void sendAddPost() {
		PostsR createPost = new PostsR(apiEngine.UsersEndPoints.USER_ID, "This is the title", "This is the Body");
		IRestResponse<Posts> createnewPost = postsEndPoints.createPost(createPost);
		response =createnewPost.getResponse();
		Assert.assertTrue(createnewPost.isSuccessful());
		System.out.println("RETURNED DATA ON ADD ::" + response.asString());
	}
	@Then("Validate that created post data is returned") 
	public void verifyAddPost() {
		System.out.println("RETURNED DATA 2222 ON ADD ::" + response.asString());
		JsonPath jEvaluator = response.jsonPath();
		System.out.println("RETURNED DATA 333 ON ADD ::" + response.asString());
		int code  = jEvaluator.get("code");
		int user_id  = jEvaluator.get("data.user_id");
		System.out.println("RETURNED DATA 4444 ON ADD :" + jEvaluator.get("data[0].user_id"));
		System.out.println("USER_ID ISS : " +apiEngine.UsersEndPoints.USER_ID );
		System.out.println("Actual Userud ISS : "+response.asString() );
		Assert.assertEquals(code,201);
		Assert.assertEquals(user_id,apiEngine.UsersEndPoints.USER_ID);
		
	}
	@When("navigate to get all posts endpoint")
	public void sendGetAllPosts() {
		response = postsEndPoints.getPosts();
	}
	@Then("check if All post data is returned")
	public void verifyGetAllPostsResponse() {
		JsonPath jEvaluator = response.jsonPath();
		int code  = jEvaluator.get("code");
		Assert.assertEquals(code,200);
	}
	@When("navigate to get posts per user endpoint")
	public void sendGetPostPerUser() {
		response = postsEndPoints.getPostsPerUser();
	
	}
	@Then("check if get posts per user data is returned") 
	public void verifyGetPostPerUser() {
		JsonPath JEvaluator = response.jsonPath();
		int code  = JEvaluator.get("code");
		System.out.println("Post Data is :; "+ JEvaluator);
		int user_id  = JEvaluator.get("data[0].user_id");
		Assert.assertEquals(code,200);
		Assert.assertEquals(user_id,apiEngine.UsersEndPoints.USER_ID);
	}
	@When("post update data is passed")
	public void sendUpdatePost() {
		PostsR updatePost = new PostsR(apiEngine.UsersEndPoints.USER_ID, "This is the title", "This is the Body");
		IRestResponse<Posts> updatepost = postsEndPoints.updatePost(updatePost);
		Response response = updatepost.getResponse();
		
	}
	@Then("validate that the post data is updated") 
	public void verifyUpdateUser() {
		JsonPath JEvaluator = response.jsonPath();
		int code  = JEvaluator.get("code");
		int user_id  = JEvaluator.get("data[0].user_id");
		Assert.assertEquals(code,200);
		Assert.assertEquals(user_id,apiEngine.UsersEndPoints.USER_ID);
	}

	@When("navigate to the onePost endpoint")
	public void sendGetOnePost() {
		response = postsEndPoints.getCreatedPost();
	
		
	}
	@Then("check if the post data is returned") 
	public void verifyGetOneUser() {
		JsonPath JEvaluator = response.jsonPath();
		int code  = JEvaluator.get("code");
		int id  = JEvaluator.get("data.id");
		Assert.assertEquals(code, 200);
		Assert.assertEquals(id,apiEngine.PostsEndPoints.POST_ID);
	}

//	@When("navigate to the delete User endpoint")
//	public void sendDeleteUser() {
//		response = postsEndPoints.deleteUserPosts();
//		
//	}
//	@Then("check if the user is successfully deleted") 
//	public void verifyDeleteUser() {
//		JsonPath jEvaluator = response.jsonPath();
//		Assert.assertEquals(jEvaluator.get("code"),204);
//	}
}
