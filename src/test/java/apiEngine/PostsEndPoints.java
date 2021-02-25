package apiEngine;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import dataAccess.DataAccess;

import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojos.PostsR;
import model.Posts;

public class PostsEndPoints {

	public static int POST_ID ;
	public static Response postData;
	public static RequestSpecification request;
	public static String base_url;
	public static String token;

	public PostsEndPoints(String base_Url, String token) {
	
		try {
            FileOutputStream outStr = new FileOutputStream(DataAccess.getInstance().getLogLocation(), true);
			PrintStream log = new PrintStream(outStr);
			RestAssured.baseURI = base_Url;
			request = RestAssured.given().filter(RequestLoggingFilter.logRequestTo(log)).filter(ResponseLoggingFilter.logResponseTo(log));
			request.header("Content-Type", "Application/json").header("Authorization", token);
		} catch(FileNotFoundException fnfe) { 
            System.out.println(fnfe.getMessage());
        } 
	}

	public Response getPosts() {
		return request.get(Routes.posts());
	}

	public Response getPostsPerUser() {
		return request.get(Routes.oneUserPosts(UsersEndPoints.USER_ID));
	}

	public IRestResponse<Posts> createPost(PostsR createPost) {
		Response response = request.body(createPost).post(Routes.oneUserPosts(UsersEndPoints.USER_ID));
		JsonPath jsonPathEvaluator = response.jsonPath();
		int code = jsonPathEvaluator.get("code");
		if (code == 201) {
			POST_ID = jsonPathEvaluator.get("data.id");
			postData = response;
		} else {
		}
		return new RestResponse<Posts>(Posts.class, response, postData);
	}

	public Response getCreatedPost() {
		return request.get(Routes.onePost(POST_ID));
	}

	public IRestResponse<Posts> updatePost(PostsR updatePost) {
		Response response = request.body(updatePost).patch(Routes.onePost(POST_ID));
		postData = response;
		return new RestResponse<Posts>(Posts.class, response, postData);
	}

	public Response deleteUserPosts() {
		return request.delete(Routes.onePost(POST_ID));
	}
}

// }
