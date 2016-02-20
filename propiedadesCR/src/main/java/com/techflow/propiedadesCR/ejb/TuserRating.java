package propiedadesCREntities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tuser_ratings database table.
 * 
 */
@Entity
@Table(name="tuser_ratings")
@NamedQuery(name="TuserRating.findAll", query="SELECT t FROM TuserRating t")
public class TuserRating implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idRating;
	private double averageRating;
	private Tuser tuser1;
	private Tuser tuser2;

	public TuserRating() {
	}


	@Id
	@Column(name="id_rating")
	public int getIdRating() {
		return this.idRating;
	}

	public void setIdRating(int idRating) {
		this.idRating = idRating;
	}


	@Column(name="average_rating")
	public double getAverageRating() {
		return this.averageRating;
	}

	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
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