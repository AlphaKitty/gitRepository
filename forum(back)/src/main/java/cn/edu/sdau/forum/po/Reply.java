package cn.edu.sdau.forum.po;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the reply database table.
 * 
 */
@Entity
@NamedQuery(name="Reply.findAll", query="SELECT r FROM Reply r")
public class Reply implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int rid;

	private String content;

	@Column(name="reply_time")
	private String replyTime;

	//bi-directional many-to-one association to Post
	@ManyToOne
	@JoinColumn(name="pid")
	private Post post;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="uid")
	private User user;

	public Reply() {
	}

	public int getRid() {
		return this.rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReplyTime() {
		return this.replyTime;
	}

	public void setReplyTime(String replyTime) {
		this.replyTime = replyTime;
	}

	public Post getPost() {
		return this.post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Reply(int rid, String content, String replyTime, Post post, User user) {
		super();
		this.rid = rid;
		this.content = content;
		this.replyTime = replyTime;
		this.post = post;
		this.user = user;
	}

}