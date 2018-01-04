package cn.edu.sdau.forum.po;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the post database table.
 * 
 */
@Entity
@NamedQuery(name="Post.findAll", query="SELECT p FROM Post p")
public class Post implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int pid;

	private String content;

	@Column(name="post_time")
	private String postTime;

	private String title;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="uid")
	private User user;

	//bi-directional many-to-one association to Reply
//	@OneToMany(mappedBy="post")
//	private List<Reply> replies;

	public Post() {
	}

	public int getPid() {
		return this.pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPostTime() {
		return this.postTime;
	}

	public void setPostTime(String postTime) {
		this.postTime = postTime;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

//	public List<Reply> getReplies() {
//		return this.replies;
//	}
//
//	public void setReplies(List<Reply> replies) {
//		this.replies = replies;
//	}
//
//	public Reply addReply(Reply reply) {
//		getReplies().add(reply);
//		reply.setPost(this);
//
//		return reply;
//	}
//
//	public Reply removeReply(Reply reply) {
//		getReplies().remove(reply);
//		reply.setPost(null);
//
//		return reply;
//	}

	public Post(int pid, String content, String postTime, String title, User user) {
		super();
		this.pid = pid;
		this.content = content;
		this.postTime = postTime;
		this.title = title;
		this.user = user;
//		this.replies = replies;
	}

}