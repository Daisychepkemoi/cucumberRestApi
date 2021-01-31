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

	@Then("check if All comment data is returned")
	public void verifyGetAllComments() {
		JsonPath jEvaluator = response.jsonPath();
		int code = jEvaluator.get("code");
		Assert.assertEquals(code, 200);
	}

	@When("The comment {string} config and other data is passed to the endpoint")
	public void sendAddComment(String body) {
		JsonPath jEvaluator = apiEngine.UsersEndPoints.userData.jsonPath();
		CommentsR createComment = new CommentsR(PostsEndPoints.POST_ID, jEvaluator.get("data.name").toString(), body,
				jEvaluator.get("data.email").toString());
		IRestResponse<Comments> createNewComment = commentsEndPoints.createComment(createComment);
		response = createNewComment.getResponse();
	}

	@Then("Validate this endpoints {int} code is received")
	public void verifyAddComment(int responsecode) {
		JsonPath eval = response.jsonPath();
		int code = eval.get("code");
		System.out.println("this is my response code" + eval.get("$"));
		System.out.println("this is my response code" + eval.get("code"));
		Assert.assertEquals(code, responsecode);
		if (code == 201 || code == 200) {
			int postID = eval.get("data.post_id");
			Assert.assertEquals(postID, PostsEndPoints.POST_ID);
		}

	}

	@When("navigate to get Comments per post endpoint")
	public void sendCommentsPerPost() {
		response = commentsEndPoints.getCommentsPerPost();

	}

	@Then("check if get Comments per post data is returned")
	public void verifyCommentsPerPost() {
		JsonPath JEvaluator = response.jsonPath();
		int code = JEvaluator.get("code");
		int postID = JEvaluator.get("data[0].post_id");
		Assert.assertEquals(code, 200);
		Assert.assertEquals(postID, PostsEndPoints.POST_ID);
	}

	@When("The comments {string} and other data is passed to the endpoint")
	public void sendUpdateComment(String body) {
		JsonPath jEvaluator = apiEngine.UsersEndPoints.userData.jsonPath();
		CommentsR updateComment = new CommentsR(PostsEndPoints.POST_ID, jEvaluator.get("data.name").toString(), body,
				jEvaluator.get("data.email").toString());
		IRestResponse<Comments> updatepost = commentsEndPoints.updateComment(updateComment);
		response = updatepost.getResponse();

	}

	@Then("Validate this comments endpoints {int} code is received")
	public void verifyUpdateComment(int codess) {
		JsonPath eval = response.jsonPath();
		System.out.println("THIS is AN UPdated Comment" + response.asString());
		int code = eval.get("code");
		// int commentID = eval.get("data[0].id");
		Assert.assertEquals(code, codess);
		// Assert.assertEquals(commentID,apiEngine.CommentsEndPoints.COMMENT_ID);
	}

	@When("navigate to the getOneComment endpoint")
	public void sendGetOneComment() {
		response = commentsEndPoints.getCreatedComment();

	}

	@Then("check if the comment data is returned")
	public void verifyGetOneComment() {
		JsonPath JEvaluator = response.jsonPath();
		int code = JEvaluator.get("code");
		int commentID = JEvaluator.get("data.id");
		Assert.assertEquals(code, 200);
		Assert.assertEquals(commentID, apiEngine.CommentsEndPoints.COMMENT_ID);
	}

	@When("navigate to the deleteComment endpoint")
	public void sendDeleteComment() {
		response = commentsEndPoints.deleteComment();

	}

	@Then("check if the comment is successfully deleted")
	public void verifyDeleteComment() {
		JsonPath eval = response.jsonPath();
		int code = eval.get("code");
		Assert.assertEquals(code, 204);
	}
}
