package beans;





public class User {
	
	private int id;
	private String email;
	private String password;
	private String fullName;
	private String profileImg_url = "face.jpg";
	
	
	
	
	
	  
	public User(String email, String password, String fullName) {
		super();
		this.email = email;
		this.password = password;
		this.fullName = fullName;
	}
	public User(String email, String password, String fullName, String profileImg_url) {
		super();
		this.email = email;
		this.password = password;
		this.fullName = fullName;
		this.profileImg_url = profileImg_url;
	}
	public User(int id, String email, String password, String fullName, String profileImg_url) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.fullName = fullName;
		this.profileImg_url = profileImg_url;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getProfileImg_url() {
		return profileImg_url;
	}
	public void setProfileImg_url(String profileImg_url) {
		this.profileImg_url = profileImg_url;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", fullName=" + fullName
				+ ", profileImg_url=" + profileImg_url + "]";
	}
	
		  
}
