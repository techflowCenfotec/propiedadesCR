package com.techflow.propiedadesCR.ejb;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;


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
	private Date birthday;
	private String email;
	private String firstName;
	private byte firstTime;
	private String gender;
	private String lastName;
	private String password;
	private String phone1;
	private String phone2;
	private String userImage;
	private String userName;
	private List<TToDoList> TToDoLists;
	private List<Tevent> tevents;
	private List<Tmessage> tmessages1;
	private List<Tmessage> tmessages2;
	private List<Tproperty> tproperties1;
	private List<TpropertyReview> tpropertyReviews;
	private List<TuserPattern> tuserPatterns;
	private List<TuserReview> tuserReviews1;
	private List<TuserReview> tuserReviews2;
	private List<TuserSurvey> tuserSurveys;
	private List<Tproperty> tproperties2;
	private Trole trole;

	public Tuser() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
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


	@Temporal(TemporalType.DATE)
	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
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


	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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


	@Column(name="user_image")
	public String getUserImage() {
		return this.userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}


	@Column(name="user_name")
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	//bi-directional many-to-one association to TToDoList
	@OneToMany(mappedBy="tuser")
	public List<TToDoList> getTToDoLists() {
		return this.TToDoLists;
	}

	public void setTToDoLists(List<TToDoList> TToDoLists) {
		this.TToDoLists = TToDoLists;
	}

	public TToDoList addTToDoList(TToDoList TToDoList) {
		getTToDoLists().add(TToDoList);
		TToDoList.setTuser(this);

		return TToDoList;
	}

	public TToDoList removeTToDoList(TToDoList TToDoList) {
		getTToDoLists().remove(TToDoList);
		TToDoList.setTuser(null);

		return TToDoList;
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


	//bi-directional many-to-one association to Tproperty
	@OneToMany(mappedBy="tuser")
	public List<Tproperty> getTproperties1() {
		return this.tproperties1;
	}

	public void setTproperties1(List<Tproperty> tproperties1) {
		this.tproperties1 = tproperties1;
	}

	public Tproperty addTproperties1(Tproperty tproperties1) {
		getTproperties1().add(tproperties1);
		tproperties1.setTuser(this);

		return tproperties1;
	}

	public Tproperty removeTproperties1(Tproperty tproperties1) {
		getTproperties1().remove(tproperties1);
		tproperties1.setTuser(null);

		return tproperties1;
	}


	//bi-directional many-to-one association to TpropertyComment
	@OneToMany(mappedBy="tuser")
	public List<TpropertyReview> getTpropertyReviews() {
		return this.tpropertyReviews;
	}

	public void setTpropertyReviews(List<TpropertyReview> tpropertyReviews) {
		this.tpropertyReviews = tpropertyReviews;
	}

	public TpropertyReview addTpropertyReviews(TpropertyReview tpropertyReview) {
		getTpropertyReviews().add(tpropertyReview);
		tpropertyReview.setTuser(this);

		return tpropertyReview;
	}

	public TpropertyReview removeTpropertyReviews(TpropertyReview tpropertyReview) {
		getTpropertyReviews().remove(tpropertyReview);
		tpropertyReview.setTuser(null);

		return tpropertyReview;
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
	@JsonIgnore
	public List<TuserReview> getTuserReviews1() {
		return this.tuserReviews1;
	}

	public void setTuserReviews1(List<TuserReview> tuserReviews1) {
		this.tuserReviews1 = tuserReviews1;
	}

	public TuserReview addTuserReviews1(TuserReview tuserReviews1) {
		getTuserReviews1().add(tuserReviews1);
		tuserReviews1.setTuser1(this);

		return tuserReviews1;
	}

	public TuserReview removeTuserReviews1(TuserReview tuserReviews1) {
		getTuserReviews1().remove(tuserReviews1);
		tuserReviews1.setTuser1(null);

		return tuserReviews1;
	}


	//bi-directional many-to-one association to TuserRating
	@OneToMany(mappedBy="tuser2")
	@JsonIgnore
	public List<TuserReview> getTuserReviews2() {
		return this.tuserReviews2;
	}

	public void setTuserReviews2(List<TuserReview> tuserReviews2) {
		this.tuserReviews2 = tuserReviews2;
	}

	public TuserReview addTuserReviews2(TuserReview tuserReviews2) {
		getTuserReviews2().add(tuserReviews2);
		tuserReviews2.setTuser2(this);

		return tuserReviews2;
	}

	public TuserReview removeTuserReviews2(TuserReview tuserReviews2) {
		getTuserReviews2().remove(tuserReviews2);
		tuserReviews2.setTuser2(null);

		return tuserReviews2;
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
	@JsonIgnore
	public List<Tproperty> getTproperties2() {
		return this.tproperties2;
	}

	public void setTproperties2(List<Tproperty> tproperties2) {
		this.tproperties2 = tproperties2;
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