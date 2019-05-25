package beans;

public class Post {
	  private int id_post;
	  private String post_img_url;
	  private String description;
	  private int nbrUpvotes;
	  private int nbrComments;
	  private String date_publication;
	  private int user_id;
	  private String user_profileImg;
	  private String user_fullName;
	public int getId_post() {
		return id_post;
	}
	public void setId_post(int id_post) {
		this.id_post = id_post;
	}
	public String getPost_img_url() {
		return post_img_url;
	}
	public void setPost_img_url(String post_img_url) {
		this.post_img_url = post_img_url;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getNbrUpvotes() {
		return nbrUpvotes;
	}
	public void setNbrUpvotes(int nbrUpvotes) {
		this.nbrUpvotes = nbrUpvotes;
	}
	public int getNbrComments() {
		return nbrComments;
	}
	public void setNbrComments(int nbrComments) {
		this.nbrComments = nbrComments;
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
	
	
	
	
	
	
	public Post(String post_img_url, String description, int user_id) {
		super();
		this.post_img_url = post_img_url;
		this.description = description;
		this.user_id = user_id;
	}
	public Post(int id_post, String post_img_url, String description, int nbrUpvotes, int nbrComments,
			String date_publication, int user_id, String user_profileImg, String user_fullName) {
		super();
		this.id_post = id_post;
		this.post_img_url = post_img_url;
		this.description = description;
		this.nbrUpvotes = nbrUpvotes;
		this.nbrComments = nbrComments;
		this.date_publication = date_publication;
		this.user_id = user_id;
		this.user_profileImg = user_profileImg;
		this.user_fullName = user_fullName;
	}
	@Override
	public String toString() {
		return "Posts [id_post=" + id_post + "]";
	}

	
	

}
