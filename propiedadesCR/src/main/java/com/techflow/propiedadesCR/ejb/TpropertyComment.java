package com.techflow.propiedadesCR.ejb;
import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tproperty_comments database table.
 * 
 */
@Entity
@Table(name="tproperty_comments")
@NamedQuery(name="TpropertyComment.findAll", query="SELECT t FROM TpropertyComment t")
public class TpropertyComment implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idComment;
	private String comment;
	private Tproperty tproperty;
	private Tuser tuser;

	public TpropertyComment() {
	}


	@Id
	@Column(name="id_comment")
	public int getIdComment() {
		return this.idComment;
	}

	public void setIdComment(int idComment) {
		this.idComment = idComment;
	}


	@Lob
	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}


	//bi-directional many-to-one association to Tproperty
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_property")
	public Tproperty getTproperty() {
		return this.tproperty;
	}

	public void setTproperty(Tproperty tproperty) {
		this.tproperty = tproperty;
	}


	//bi-directional many-to-one association to Tuser
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_user")
	public Tuser getTuser() {
		return this.tuser;
	}

	public void setTuser(Tuser tuser) {
		this.tuser = tuser;
	}

}