package com.techflow.propiedadesCR.pojo;

import java.util.List;

import com.techflow.propiedadesCR.ejb.Tproperty;

public class BenefitsPOJO {

	private int idBenefit;
	private String benefit;
	private List<Tproperty> tproperties;
	
	public BenefitsPOJO() {
		super();
	}
	public int getIdBenefit() {
		return idBenefit;
	}
	public void setIdBenefit(int idBenefit) {
		this.idBenefit = idBenefit;
	}
	public String getBenefit() {
		return benefit;
	}
	public void setBenefit(String benefit) {
		this.benefit = benefit;
	}
	public List<Tproperty> getTproperties() {
		return tproperties;
	}
	public void setTproperties(List<Tproperty> tproperties) {
		this.tproperties = tproperties;
	}
	
}
