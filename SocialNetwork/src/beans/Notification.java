package beans;

public class Notification {
	private int notification__id;
	private int post_id;
	private int user_id;
	private String status;
	private String user_profileImg;
	private String user_fullName;
	
	public Notification( String user_profileImg,
			String user_fullName , int post_id, String status) {
		super();
		this.post_id = post_id;
		this.status = status;
		this.user_profileImg = user_profileImg;
		this.user_fullName = user_fullName;
	}

	public Notification(int post_id, int user_id, String status) {
		super();
		this.post_id = post_id;
		this.user_id = user_id;
		this.status = status;
	}

	public int getNotification__id() {
		return notification__id;
	}

	public void setNotification__id(int notification__id) {
		this.notification__id = notification__id;
	}

	public int getPost_id() {
		return post_id;
	}

	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUser_profileImg() {
		return user_profileImg;
	}

	public void setUser_profileImg(String user_profileImg) {
		this.user_profileImg = user_profileImg;
	}

	public String getUser_fullName() {
		return user_fullName;
	}

	public void setUser_fullName(String user_fullName) {
		this.user_fullName = user_fullName;
	}
	
	
}
