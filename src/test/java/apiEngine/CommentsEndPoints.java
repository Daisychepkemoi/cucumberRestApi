package apiEngine;

import model.Comments;
import io.restassured.RestAssured;
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
		RestAssured.baseURI = base_Url;
		request = RestAssured.given().log().all();
		request.header("Content-Type","Application/json").header("Authorization",token);
		
		
	}
	public Response getComments() {
		return request.get(Routes.comments());
	}
	
	public Response getCommentsPerPost() {
		return request.get(Routes.commentsPerPost(postsEndpoints.POST_ID));
	}
	public IRestResponse<Comments> createComment(Comments createComment){
		Response response = request.body(createComment).post(Routes.commentsPerPost(postsEndpoints.POST_ID));
		JsonPath jsonPathEvaluator = response.jsonPath();
		    int code = jsonPathEvaluator.get("code");
		    if(code == 201) {
		    	COMMENT_ID  =jsonPathEvaluator.get("data.id");
		    	commentData =response;
		    }
		    else {
		    	}
		    return new RestResponse(CommentsR.class, response);
			}
	public Response getCreatedComment() {
		return postsEndpoints.request.get(Routes.oneComment(COMMENT_ID));
	}
	public IRestResponse<Comments> updateComment(Comments updatePost){
		Response response = request.body(updatePost).patch(Routes.oneComment(COMMENT_ID));
		JsonPath jsonPathEvaluator = response.jsonPath();
	    int code = jsonPathEvaluator.get("code");
	    if(code == 201) {
	    	COMMENT_ID  =jsonPathEvaluator.get("data.id");
	    	commentData =response;
	    }
	    else {
	    	}

		return new RestResponse(CommentsR.class,response);
	}
	public Response deleteComment() {
		return request.delete(Routes.oneComment(COMMENT_ID));
	}
}
	
//}
