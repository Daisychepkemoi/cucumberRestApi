package apiEngine;

public class Routes {
	private static final String POSTS = "/posts/";
	private static final String ENV = "/public-api";
	private static final String COMMENTS = "/comments/";
	private static final String USERS = "/users/";
	private static final String TODOS = "/todos/";

	public static String users() {
		return ENV + USERS;

	}

	public static String oneUser(int USER_ID) {
		return ENV + USERS + USER_ID;
	}

	public static String posts() {
		return ENV + POSTS;
	}

	public static String oneUserPosts(int USER_ID) {
		return ENV + USERS + USER_ID + POSTS;
	}

	public static String onePost(int POST_ID) {
		return ENV + POSTS + POST_ID;
	}

	public static String comments() {
		return ENV + COMMENTS;
	}

	public static String commentsPerPost(int POST_ID) {
		return ENV + POSTS + POST_ID + COMMENTS;
	}

	public static String oneComment(int COMMENT_ID) {
		return ENV + COMMENTS + COMMENT_ID;
	}

	public static String todos() {
		return ENV + TODOS;
	}

	public static String todosPerUser(int USER_ID) {
		return ENV + USERS + USER_ID + TODOS;
	}

	public static String oneTodo(int TODO_ID) {
		return ENV + TODOS + TODO_ID;
	}
}
