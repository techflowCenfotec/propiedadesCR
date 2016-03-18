package com.techflow.propiedadesCR.contracts;

import com.techflow.propiedadesCR.pojo.UserRatingPOJO;

public class UserRatingRequest extends BaseRequest{

	UserRatingPOJO rating;
	int idClient;
	int idVendor;
	
	public UserRatingPOJO getRating() {
		return rating;
	}
	
	public void setRating(UserRatingPOJO rating) {
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
