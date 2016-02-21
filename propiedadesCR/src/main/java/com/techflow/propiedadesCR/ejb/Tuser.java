package com.techflow.propiedadesCR.ejb;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tusers database table.
 * 
 */
@Entity
@Table(name="tusers")
@NamedQuery(name="Tuser.findAll", query="SELECT t FROM Tuser t")
public class Tuser implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idUser;
	private byte active;
	private String email;
	private String firstName;
	private byte firstTime;
	private String lastName;
	private String password;
	private String phone1;
	private String phone2;
	private String userName;
	private List<Tevent> tevents;
	private List<Tmessage> tmessages1;
	private List<Tmessage> tmessages2;
	private List<TpropertyComment> tpropertyComments;
	private List<TpropertyRating> tpropertyRatings;
	private List<TuserComment> tuserComments1;
	private List<TuserComment> tuserComments2;
	private List<TuserPattern> tuserPatterns;
	private List<TuserRating> tuserRatings1;
	private List<TuserRating> tuserRatings2;
	private List<TuserSurvey> tuserSurveys;
	private List<Tproperty> tproperties;
	private Trole trole;

	public Tuser() {
	}


	@Id
	@Column(name="id_user")
	public int getIdUser() {
		return this.idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}


	public byte getActive() {
		return this.active;
	}

	public void setActive(byte active) {
		this.active = active;
	}


	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	@Column(name="first_name")
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	@Column(name="first_time")
	public byte getFirstTime() {
		return this.firstTime;
	}

	public void setFirstTime(byte firstTime) {
		this.firstTime = firstTime;
	}


	@Column(name="last_name")
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	@Column(name="phone_1")
	public String getPhone1() {
		return this.phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}


	@Column(name="phone_2")
	public String getPhone2() {
		return this.phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}


	@Column(name="user_name")
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	//bi-directional many-to-one association to Tevent
	@OneToMany(mappedBy="tuser")
	public List<Tevent> getTevents() {
		return this.tevents;
	}

	public void setTevents(List<Tevent> tevents) {
		this.tevents = tevents;
	}

	public Tevent addTevent(Tevent tevent) {
		getTevents().add(tevent);
		tevent.setTuser(this);

		return tevent;
	}

	public Tevent removeTevent(Tevent tevent) {
		getTevents().remove(tevent);
		tevent.setTuser(null);

		return tevent;
	}


	//bi-directional many-to-one association to Tmessage
	@OneToMany(mappedBy="tuser1")
	public List<Tmessage> getTmessages1() {
		return this.tmessages1;
	}

	public void setTmessages1(List<Tmessage> tmessages1) {
		this.tmessages1 = tmessages1;
	}

	public Tmessage addTmessages1(Tmessage tmessages1) {
		getTmessages1().add(tmessages1);
		tmessages1.setTuser1(this);

		return tmessages1;
	}

	public Tmessage removeTmessages1(Tmessage tmessages1) {
		getTmessages1().remove(tmessages1);
		tmessages1.setTuser1(null);

		return tmessages1;
	}


	//bi-directional many-to-one association to Tmessage
	@OneToMany(mappedBy="tuser2")
	public List<Tmessage> getTmessages2() {
		return this.tmessages2;
	}

	public void setTmessages2(List<Tmessage> tmessages2) {
		this.tmessages2 = tmessages2;
	}

	public Tmessage addTmessages2(Tmessage tmessages2) {
		getTmessages2().add(tmessages2);
		tmessages2.setTuser2(this);

		return tmessages2;
	}

	public Tmessage removeTmessages2(Tmessage tmessages2) {
		getTmessages2().remove(tmessages2);
		tmessages2.setTuser2(null);

		return tmessages2;
	}


	//bi-directional many-to-one association to TpropertyComment
	@OneToMany(mappedBy="tuser")
	public List<TpropertyComment> getTpropertyComments() {
		return this.tpropertyComments;
	}

	public void setTpropertyComments(List<TpropertyComment> tpropertyComments) {
		this.tpropertyComments = tpropertyComments;
	}

	public TpropertyComment addTpropertyComment(TpropertyComment tpropertyComment) {
		getTpropertyComments().add(tpropertyComment);
		tpropertyComment.setTuser(this);

		return tpropertyComment;
	}

	public TpropertyComment removeTpropertyComment(TpropertyComment tpropertyComment) {
		getTpropertyComments().remove(tpropertyComment);
		tpropertyComment.setTuser(null);

		return tpropertyComment;
	}


	//bi-directional many-to-one association to TpropertyRating
	@OneToMany(mappedBy="tuser")
	public List<TpropertyRating> getTpropertyRatings() {
		return this.tpropertyRatings;
	}

	public void setTpropertyRatings(List<TpropertyRating> tpropertyRatings) {
		this.tpropertyRatings = tpropertyRatings;
	}

	public TpropertyRating addTpropertyRating(TpropertyRating tpropertyRating) {
		getTpropertyRatings().add(tpropertyRating);
		tpropertyRating.setTuser(this);

		return tpropertyRating;
	}

	public TpropertyRating removeTpropertyRating(TpropertyRating tpropertyRating) {
		getTpropertyRatings().remove(tpropertyRating);
		tpropertyRating.setTuser(null);

		return tpropertyRating;
	}


	//bi-directional many-to-one association to TuserComment
	@OneToMany(mappedBy="tuser1")
	public List<TuserComment> getTuserComments1() {
		return this.tuserComments1;
	}

	public void setTuserComments1(List<TuserComment> tuserComments1) {
		this.tuserComments1 = tuserComments1;
	}

	public TuserComment addTuserComments1(TuserComment tuserComments1) {
		getTuserComments1().add(tuserComments1);
		tuserComments1.setTuser1(this);

		return tuserComments1;
	}

	public TuserComment removeTuserComments1(TuserComment tuserComments1) {
		getTuserComments1().remove(tuserComments1);
		tuserComments1.setTuser1(null);

		return tuserComments1;
	}


	//bi-directional many-to-one association to TuserComment
	@OneToMany(mappedBy="tuser2")
	public List<TuserComment> getTuserComments2() {
		return this.tuserComments2;
	}

	public void setTuserComments2(List<TuserComment> tuserComments2) {
		this.tuserComments2 = tuserComments2;
	}

	public TuserComment addTuserComments2(TuserComment tuserComments2) {
		getTuserComments2().add(tuserComments2);
		tuserComments2.setTuser2(this);

		return tuserComments2;
	}

	public TuserComment removeTuserComments2(TuserComment tuserComments2) {
		getTuserComments2().remove(tuserComments2);
		tuserComments2.setTuser2(null);

		return tuserComments2;
	}


	//bi-directional many-to-one association to TuserPattern
	@OneToMany(mappedBy="tuser")
	public List<TuserPattern> getTuserPatterns() {
		return this.tuserPatterns;
	}

	public void setTuserPatterns(List<TuserPattern> tuserPatterns) {
		this.tuserPatterns = tuserPatterns;
	}

	public TuserPattern addTuserPattern(TuserPattern tuserPattern) {
		getTuserPatterns().add(tuserPattern);
		tuserPattern.setTuser(this);

		return tuserPattern;
	}

	public TuserPattern removeTuserPattern(TuserPattern tuserPattern) {
		getTuserPatterns().remove(tuserPattern);
		tuserPattern.setTuser(null);

		return tuserPattern;
	}


	//bi-directional many-to-one association to TuserRating
	@OneToMany(mappedBy="tuser1")
	public List<TuserRating> getTuserRatings1() {
		return this.tuserRatings1;
	}

	public void setTuserRatings1(List<TuserRating> tuserRatings1) {
		this.tuserRatings1 = tuserRatings1;
	}

	public TuserRating addTuserRatings1(TuserRating tuserRatings1) {
		getTuserRatings1().add(tuserRatings1);
		tuserRatings1.setTuser1(this);

		return tuserRatings1;
	}

	public TuserRating removeTuserRatings1(TuserRating tuserRatings1) {
		getTuserRatings1().remove(tuserRatings1);
		tuserRatings1.setTuser1(null);

		return tuserRatings1;
	}


	//bi-directional many-to-one association to TuserRating
	@OneToMany(mappedBy="tuser2")
	public List<TuserRating> getTuserRatings2() {
		return this.tuserRatings2;
	}

	public void setTuserRatings2(List<TuserRating> tuserRatings2) {
		this.tuserRatings2 = tuserRatings2;
	}

	public TuserRating addTuserRatings2(TuserRating tuserRatings2) {
		getTuserRatings2().add(tuserRatings2);
		tuserRatings2.setTuser2(this);

		return tuserRatings2;
	}

	public TuserRating removeTuserRatings2(TuserRating tuserRatings2) {
		getTuserRatings2().remove(tuserRatings2);
		tuserRatings2.setTuser2(null);

		return tuserRatings2;
	}


	//bi-directional many-to-one association to TuserSurvey
	@OneToMany(mappedBy="tuser")
	public List<TuserSurvey> getTuserSurveys() {
		return this.tuserSurveys;
	}

	public void setTuserSurveys(List<TuserSurvey> tuserSurveys) {
		this.tuserSurveys = tuserSurveys;
	}

	public TuserSurvey addTuserSurvey(TuserSurvey tuserSurvey) {
		getTuserSurveys().add(tuserSurvey);
		tuserSurvey.setTuser(this);

		return tuserSurvey;
	}

	public TuserSurvey removeTuserSurvey(TuserSurvey tuserSurvey) {
		getTuserSurveys().remove(tuserSurvey);
		tuserSurvey.setTuser(null);

		return tuserSurvey;
	}


	//bi-directional many-to-many association to Tproperty
	@ManyToMany
	@JoinTable(
		name="tfavorites"
		, joinColumns={
			@JoinColumn(name="id_user")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_property")
			}
		)
	public List<Tproperty> getTproperties() {
		return this.tproperties;
	}

	public void setTproperties(List<Tproperty> tproperties) {
		this.tproperties = tproperties;
	}


	//bi-directional many-to-one association to Trole
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_rol")
	public Trole getTrole() {
		return this.trole;
	}

	public void setTrole(Trole trole) {
		this.trole = trole;
	}

}