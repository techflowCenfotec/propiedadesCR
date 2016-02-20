package propiedadesCREntities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tevents database table.
 * 
 */
@Entity
@Table(name="tevents")
@NamedQuery(name="Tevent.findAll", query="SELECT t FROM Tevent t")
public class Tevent implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idEvent;
	private byte active;
	private String description;
	private String name;
	private Date startDate;
	private Tuser tuser;

	public Tevent() {
	}


	@Id
	@Column(name="id_event")
	public int getIdEvent() {
		return this.idEvent;
	}

	public void setIdEvent(int idEvent) {
		this.idEvent = idEvent;
	}


	public byte getActive() {
		return this.active;
	}

	public void setActive(byte active) {
		this.active = active;
	}


	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="start_date")
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
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