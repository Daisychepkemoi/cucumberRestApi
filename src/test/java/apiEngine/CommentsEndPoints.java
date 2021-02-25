package apiEngine;

import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import dataAccess.DataAccess;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import model.Comments;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojos.CommentsR;

public class CommentsEndPoints {
	public static PostsEndPoints postsEndpoints;
	public static int COMMENT_ID;
	public static Response commentData;
	public static RequestSpecification request;

	public CommentsEndPoints(String base_Url, String token) {
		try {
			FileOutputStream outStr = new FileOutputStream(DataAccess.getInstance().getLogLocation(), true);
			
			PrintStream log = new PrintStream(outStr);
			RestAssured.baseURI = base_Url;
			request = RestAssured.given().filter(RequestLoggingFilter.logRequestTo(log))
					.filter(ResponseLoggingFilter.logResponseTo(log));
			request.header("Content-Type", "Application/json").header("Authorization", token);
		} catch (FileNotFoundException fnfe) {
			System.out.println(fnfe.getMessage());
		}

	}

	public Response getComments() {
		return request.get(Routes.comments());
	}

	public Response getCommentsPerPost() {
		return request.get(Routes.commentsPerPost(PostsEndPoints.POST_ID));
	}

	public IRestResponse<Comments> createComment(CommentsR createComment) {
		Response response = request.body(createComment).post(Routes.commentsPerPost(PostsEndPoints.POST_ID));
		JsonPath jsonPathEvaluator = response.jsonPath();
		int code = jsonPathEvaluator.get("code");
		if (code == 201) {
			COMMENT_ID = jsonPathEvaluator.get("data.id");
			commentData = response;
		} else {
		}
		return new RestResponse<Comments>(Comments.class, response, commentData);
	}

	public Response getCreatedComment() {
		return PostsEndPoints.request.get(Routes.oneComment(COMMENT_ID));
	}

	public IRestResponse<Comments> updateComment(CommentsR updatePost) {
		Response response = request.body(updatePost).patch(Routes.oneComment(COMMENT_ID));
		JsonPath jsonPathEvaluator = response.jsonPath();
		int code = jsonPathEvaluator.get("code");
		if (code == 201) {
			COMMENT_ID = jsonPathEvaluator.get("data.id");
			commentData = response;
		} else {
		}

		return new RestResponse<Comments>(Comments.class, response, commentData);
	}

	public Response deleteComment() {
		return request.delete(Routes.oneComment(COMMENT_ID));
	}
}

// }
