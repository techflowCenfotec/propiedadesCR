package propiedadesCREntities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tloans database table.
 * 
 */
@Entity
@Table(name="tloans")
@NamedQuery(name="Tloan.findAll", query="SELECT t FROM Tloan t")
public class Tloan implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idLoan;
	private String bankName;
	private double loanPercentage;
	private double maximumLoan;
	private double monthlyPayment;

	public Tloan() {
	}


	@Id
	@Column(name="id_loan")
	public int getIdLoan() {
		return this.idLoan;
	}

	public void setIdLoan(int idLoan) {
		this.idLoan = idLoan;
	}


	@Column(name="bank_name")
	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}


	@Column(name="loan_percentage")
	public double getLoanPercentage() {
		return this.loanPercentage;
	}

	public void setLoanPercentage(double loanPercentage) {
		this.loanPercentage = loanPercentage;
	}


	@Column(name="maximum_loan")
	public double getMaximumLoan() {
		return this.maximumLoan;
	}

	public void setMaximumLoan(double maximumLoan) {
		this.maximumLoan = maximumLoan;
	}


	@Column(name="monthly_payment")
	public double getMonthlyPayment() {
		return this.monthlyPayment;
	}

	public void setMonthlyPayment(double monthlyPayment) {
		this.monthlyPayment = monthlyPayment;
	}

}