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
	

	@When("The post {string}, {string} and other data is passed to the endpoint")
	public void sendAddPost(String title,String body) {
		PostsR createPost = new PostsR(apiEngine.UsersEndPoints.USER_ID, title, body);
		IRestResponse<Posts> createnewPost = postsEndPoints.createPost(createPost);
		response =createnewPost.getResponse();
		Assert.assertTrue(createnewPost.isSuccessful());
	}
	@Then("Validate this {int} code is received") 
	public void verifyAddPost(int codes) {
		JsonPath jEvaluator = response.jsonPath();
		int code  = jEvaluator.get("code");
		
		Assert.assertEquals(code,codes);
		if(code == 201){
			int user_id  = jEvaluator.get("data.user_id");
			Assert.assertEquals(user_id,apiEngine.UsersEndPoints.USER_ID);
		}
		
		
	}
	@When("navigate to get all posts endpoint")
	public void sendGetAllPosts() {
		response = postsEndPoints.getPosts();
	}
	@Then("check if All post data is returned")
	public void verifyGetAllPostsResponse() {
		JsonPath jEvaluator = response.jsonPath();
		int code  = jEvaluator.get("code");
//		JSONObject obj = new JSONObject(response);
		
		Assert.assertEquals(code,200);
//		Assert.assertTrue("response size is greater than 0",obj.length()>0);
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
	@When("The post {string}, {string} and other data is passed to the update post endpoint")
	public void sendUpdatePost(String title,String body) {
		PostsR updatePost = new PostsR(apiEngine.UsersEndPoints.USER_ID, title, body);
		IRestResponse<Posts> updatepost = postsEndPoints.updatePost(updatePost);
		Response response = updatepost.getResponse();
		
	}
	@Then("Validate that this {int} code is received") 
	public void verifyUpdatePost(int codes) {
		JsonPath JEvaluator = response.jsonPath();
		int code  = JEvaluator.get("code");
		System.out.print("THIS IS A CODE I GET FOR PATCH " + code + "   OFFICIAL");
		Assert.assertEquals(code,codes);
		if(code == codes){
			int user_id  = JEvaluator.get("data[0].user_id");
			Assert.assertEquals(user_id,apiEngine.UsersEndPoints.USER_ID);
		}
		
	}

	@When("navigate to the onePost endpoint")
	public void sendGetOnePost() {
		response = postsEndPoints.getCreatedPost();
	
		
	}
	@Then("check if the post data is returned") 
	public void verifyGetOnepost() {
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
