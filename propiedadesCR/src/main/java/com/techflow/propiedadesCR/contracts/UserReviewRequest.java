package com.techflow.propiedadesCR.contracts;

import com.techflow.propiedadesCR.pojo.UserReviewPOJO;

public class UserReviewRequest extends BaseRequest{

	UserReviewPOJO rating;
	int idClient;
	int idVendor;
	
	public UserReviewPOJO getRating() {
		return rating;
	}
	
	public void setRating(UserReviewPOJO rating) {
		this.rating = rating;
	}
	public int getIdClient() {
		return idClient;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	public int getIdVendor() {
		return idVendor;
	}
	public void setIdVendor(int idVendor) {
		this.idVendor = idVendor;
	}
	
	
	
	
}
