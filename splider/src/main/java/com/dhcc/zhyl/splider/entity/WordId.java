package com.dhcc.zhyl.splider.entity;
// Generated 2017-11-17 22:23:39 by Hibernate Tools 5.1.4.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * WordId generated by hbm2java
 */
@Embeddable
public class WordId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2325524480476657772L;
	private String b;
	private String c;

	public WordId() {
	}

	public WordId(String b, String c) {
		this.b = b;
		this.c = c;
	}

	@Column(name = "b", length = 20)
	public String getB() {
		return this.b;
	}

	public void setB(String b) {
		this.b = b;
	}

	@Column(name = "c", length = 20)
	public String getC() {
		return this.c;
	}

	public void setC(String c) {
		this.c = c;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof WordId))
			return false;
		WordId castOther = (WordId) other;

		return ((this.getB() == castOther.getB()) || (this.getB() != null && castOther.getB() != null && this.getB().equals(castOther.getB())))
				&& ((this.getC() == castOther.getC()) || (this.getC() != null && castOther.getC() != null && this.getC().equals(castOther.getC())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getB() == null ? 0 : this.getB().hashCode());
		result = 37 * result + (getC() == null ? 0 : this.getC().hashCode());
		return result;
	}

}
