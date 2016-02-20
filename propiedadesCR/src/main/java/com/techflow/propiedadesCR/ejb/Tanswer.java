package propiedadesCREntities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tanswers database table.
 * 
 */
@Entity
@Table(name="tanswers")
@NamedQuery(name="Tanswer.findAll", query="SELECT t FROM Tanswer t")
public class Tanswer implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idAnswer;
	private String result;
	private Tquestion tquestion;
	private TuserSurvey tuserSurvey;

	public Tanswer() {
	}


	@Id
	@Column(name="id_answer")
	public int getIdAnswer() {
		return this.idAnswer;
	}

	public void setIdAnswer(int idAnswer) {
		this.idAnswer = idAnswer;
	}


	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}


	//bi-directional many-to-one association to Tquestion
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_question")
	public Tquestion getTquestion() {
		return this.tquestion;
	}

	public void setTquestion(Tquestion tquestion) {
		this.tquestion = tquestion;
	}


	//bi-directional many-to-one association to TuserSurvey
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_survey")
	public TuserSurvey getTuserSurvey() {
		return this.tuserSurvey;
	}

	public void setTuserSurvey(TuserSurvey tuserSurvey) {
		this.tuserSurvey = tuserSurvey;
	}

}