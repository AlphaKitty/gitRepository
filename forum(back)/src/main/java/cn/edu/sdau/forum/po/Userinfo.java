package cn.edu.sdau.forum.po;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the userinfo database table.
 * 
 */
@Entity
@NamedQuery(name="Userinfo.findAll", query="SELECT u FROM Userinfo u")
public class Userinfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int uiid;

	private String birthplace;

	private String bloodtype;

	private String email;

	private String gender;

	private String liveplace;

	private String resume;

	private String tel;

	private int uid;

	public Userinfo() {
	}

	public int getUiid() {
		return this.uiid;
	}

	public void setUiid(int uiid) {
		this.uiid = uiid;
	}

	public String getBirthplace() {
		return this.birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}

	public String getBloodtype() {
		return this.bloodtype;
	}

	public void setBloodtype(String bloodtype) {
		this.bloodtype = bloodtype;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLiveplace() {
		return this.liveplace;
	}

	public void setLiveplace(String liveplace) {
		this.liveplace = liveplace;
	}

	public String getResume() {
		return this.resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getUid() {
		return this.uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

}