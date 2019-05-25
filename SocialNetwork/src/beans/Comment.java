package beans;

public class Comment {
	
	  private int id_comment;
	  private String content;
	  private int post_id;
	  private String date_publication;
	  private int user_id;
	  private String user_profileImg;
	  private String user_fullName;
	  
	  
	  
	  
	  
	  
	public Comment(int id_comment, String content, int post_id, String date_publication, int user_id,
			String user_profileImg, String user_fullName) {
		super();
		this.id_comment = id_comment;
		this.content = content;
		this.post_id = post_id;
		this.date_publication = date_publication;
		this.user_id = user_id;
		this.user_profileImg = user_profileImg;
		this.user_fullName = user_fullName;
	}
	
	
	public Comment(String content, int post_id, int user_id) {
		super();
		this.content = content;
		this.post_id = post_id;
		this.user_id = user_id;
	}


	


	public int getId_comment() {
		return id_comment;
	}
	public void setId_comment(int id_comment) {
		this.id_comment = id_comment;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getPost_id() {
		return post_id;
	}
	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}
	public String getDate_publication() {
		return date_publication;
	}
	public void setDate_publication(String date_publication) {
		this.date_publication = date_publication;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
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


	@Override
	public String toString() {
		return "Comment [id_comment=" + id_comment + ", content=" + content + ", post_id=" + post_id
				+ ", date_publication=" + date_publication + ", user_id=" + user_id + ", user_profileImg="
				+ user_profileImg + ", user_fullName=" + user_fullName + "]";
	}
	  
	  
	

}
