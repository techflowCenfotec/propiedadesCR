package propiedadesCREntities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tuser_comments database table.
 * 
 */
@Entity
@Table(name="tuser_comments")
@NamedQuery(name="TuserComment.findAll", query="SELECT t FROM TuserComment t")
public class TuserComment implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idComment;
	private String comment;
	private Tuser tuser1;
	private Tuser tuser2;

	public TuserComment() {
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


	//bi-directional many-to-one association to Tuser
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_client")
	public Tuser getTuser1() {
		return this.tuser1;
	}

	public void setTuser1(Tuser tuser1) {
		this.tuser1 = tuser1;
	}


	//bi-directional many-to-one association to Tuser
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_seller")
	public Tuser getTuser2() {
		return this.tuser2;
	}

	public void setTuser2(Tuser tuser2) {
		this.tuser2 = tuser2;
	}

}