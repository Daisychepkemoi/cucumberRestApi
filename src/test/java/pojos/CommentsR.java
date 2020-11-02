package pojos;

public class CommentsR {
	public int post_id;
	public String name;
	public String body;
	public String email;
	public CommentsR(String name, String body,String email) {
		this.name = name;
		this.body= body;
		this.email = email;
	}
}
