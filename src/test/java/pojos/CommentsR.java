package pojos;

public class CommentsR {
	public int post_id;
	public String name;
	public String body;
	public String email;
	public CommentsR(int post_id,String name, String body,String email) {
		this.post_id = post_id;
		this.name = name;
		this.body= body;
		this.email = email;
	}
}
