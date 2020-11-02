package pojos;

public class TodosR {
	public int user_id;
	public String title;
	public boolean completed;
	public TodosR (int user_id,String title,boolean completed) {
		 this.user_id = user_id;
		 this.title = title;
		 this.completed = completed;
	}
}
