package stepDefinition;

import org.junit.Assert;

import apiEngine.CommentsEndPoints;
import apiEngine.IRestResponse;
import apiEngine.PostsEndPoints;
import apiEngine.UsersEndPoints;
import dataAccess.DataAccess;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import model.Comments;
import pojos.CommentsR;

public class CommentsSteps {
	public static CommentsEndPoints commentsEndPoints;
	public static UsersEndPoints EndPoints;
	public static Response response;

	@Given("get all Comments endpoint")
	public void setGetAllComments() {
		commentsEndPoints = new CommentsEndPoints(DataAccess.getInstance().getBaseUrl(),
				DataAccess.getInstance().getToken());
	}

	@When("navigate to get all Comments endpoint")
	public void sendGetAllComments() {
		response = commentsEndPoints.getComments();

	}
	@Then("check if comments response code 200 is returned")
	public void verifyResponseCode200() {
		JsonPath jEvaluator = response.jsonPath();
		System.out.println("Get All Users Response Body" + jEvaluator.get("$"));
		Assert.assertEquals(200,jEvaluator.get("code"));
	}
	@Then("check response body contains comment object")
	public void verifyGetAllCommentsResponseBody() {
		JsonPath jEvaluator = response.jsonPath();
		Assert.assertTrue(jEvaluator.get("data").toString().contains("name"));
	}
	@When("The comment {string} config and other data is passed to the endpoint")
	public void sendAddComment(String body) {
		// JsonPath jEvaluator = apiEngine.UsersEndPoints.userData.jsonPath();
		// CommentsR createComment = new CommentsR(PostsEndPoints.POST_ID, jEvaluator.get("data[0].name").toString(), body,
				// jEvaluator.get("data[0].email").toString());//emaill22@gmail.com
		CommentsR createComment = new CommentsR(PostsEndPoints.POST_ID, "Jackie", body,"emaill22@gmail.com");//emaill22@gmail.com
		IRestResponse<Comments> createNewComment = commentsEndPoints.createComment(createComment);
		response = createNewComment.getResponse();
	}
	@Then("Validate {int} code is received for comments")
	public void verifyAddPostResponseCode(int codes) {
		JsonPath jEvaluator = response.jsonPath();
		System.out.println("This is the response" + jEvaluator.get("$"));
		int code = jEvaluator.get("code");

		Assert.assertEquals(code, codes);

	}
	@Then("Validate response body contains {string} c")
	public void verifyAddCommentResponseBody(String responseMsg) {
		JsonPath eval = response.jsonPath();
		System.out.println("this is my response code" + eval.get("$"));
		
		int code = eval.get("code");
		if(code == 201 || code == 200){
			int postID = eval.get("data.post_id");
			Assert.assertEquals(postID, PostsEndPoints.POST_ID);
		}
		else {
		  Assert.assertEquals(eval.get("data[0].message").toString(),responseMsg);
		}
		

	}

	@When("navigate to get Comments per post endpoint")
	public void sendCommentsPerPost() {
		response = commentsEndPoints.getCommentsPerPost();

	}

	@Then("check response body contains post_ID object")
	public void verifyCommentsPerPostResponseBody() {
		JsonPath JEvaluator = response.jsonPath();
		int postID = JEvaluator.get("data[0].post_id");
		Assert.assertEquals(postID, PostsEndPoints.POST_ID);
	}
	@When("navigate to the getOneComment endpoint")
	public void sendGetOneComment() {
		response = commentsEndPoints.getCreatedComment();

	}

	@Then("check response body contains commentID object")
	public void verifyGetOneCommentResponseBody() {
		JsonPath JEvaluator = response.jsonPath();
		int commentID = JEvaluator.get("data.id");
		Assert.assertEquals(commentID, apiEngine.CommentsEndPoints.COMMENT_ID);
	}

	@When("The comments {string} and other data is passed to the endpoint")
	public void sendUpdateComment(String body) {
		JsonPath jEvaluator = apiEngine.UsersEndPoints.userData.jsonPath();
		CommentsR updateComment = new CommentsR(PostsEndPoints.POST_ID, jEvaluator.get("data.name").toString(), body,
				jEvaluator.get("data.email").toString());
		IRestResponse<Comments> updatepost = commentsEndPoints.updateComment(updateComment);
		response = updatepost.getResponse();

	}
	@When("navigate to the deleteComment endpoint")
	public void sendDeleteComment() {
		response = commentsEndPoints.deleteComment();

	}
	@Then("check if comments response code 204 is returned")
	public void verifyDeleteCommentResponseCode200() {
		JsonPath jEvaluator = response.jsonPath();
		System.out.println("Get Delete Comment Response Body" + jEvaluator.get("$"));
		Assert.assertEquals(204,jEvaluator.get("code"));
	}
	@Then("check if the comment is successfully deleted")
	public void verifyDeleteComment() {
		JsonPath eval = response.jsonPath();
		Assert.assertTrue(eval.get("$").toString().contains("null"));
	}
}
