package cn.edu.sdau.forum.po;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int uid;

	private String code;

	private String email;

	private String password;

	private String portrait;

	private String posts;

	private String regdate;

	private String signature;

	private String username;

//	//bi-directional many-to-one association to Post
//	@OneToMany(mappedBy="user")
//	private List<Post> postsSet;

//	//bi-directional many-to-one association to Reply
//	@OneToMany(mappedBy="user")
//	private List<Reply> replies;
//
//	//bi-directional many-to-one association to Userinfo
//	@OneToMany(mappedBy="user")
//	private List<Userinfo> userinfos;

	public User() {
	}

	public int getUid() {
		return this.uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPortrait() {
		return this.portrait;
	}

	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}

	public String getPosts() {
		return this.posts;
	}

	public void setPosts(String posts) {
		this.posts = posts;
	}

	public String getRegdate() {
		return this.regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getSignature() {
		return this.signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}