package propiedadesCREntities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tmessages database table.
 * 
 */
@Entity
@Table(name="tmessages")
@NamedQuery(name="Tmessage.findAll", query="SELECT t FROM Tmessage t")
public class Tmessage implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idMessage;
	private byte active;
	private Date fecha;
	private String message;
	private String subject;
	private Tuser tuser1;
	private Tuser tuser2;

	public Tmessage() {
	}


	@Id
	@Column(name="id_message")
	public int getIdMessage() {
		return this.idMessage;
	}

	public void setIdMessage(int idMessage) {
		this.idMessage = idMessage;
	}


	public byte getActive() {
		return this.active;
	}

	public void setActive(byte active) {
		this.active = active;
	}


	@Temporal(TemporalType.TIMESTAMP)
	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	@Lob
	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}


	//bi-directional many-to-one association to Tuser
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_receiver")
	public Tuser getTuser1() {
		return this.tuser1;
	}

	public void setTuser1(Tuser tuser1) {
		this.tuser1 = tuser1;
	}


	//bi-directional many-to-one association to Tuser
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_sender")
	public Tuser getTuser2() {
		return this.tuser2;
	}

	public void setTuser2(Tuser tuser2) {
		this.tuser2 = tuser2;
	}

}