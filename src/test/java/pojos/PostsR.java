package pojos;

public class PostsR {
	public int user_id;
	public String title;
	public String body;
	public PostsR(int user_id,String title, String body) {
		this.user_id= user_id;
		this.body  = body;
		this.title = title;
	}
}
