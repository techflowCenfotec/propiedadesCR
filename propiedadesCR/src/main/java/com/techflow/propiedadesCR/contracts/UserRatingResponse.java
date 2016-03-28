package com.techflow.propiedadesCR.contracts;

import com.techflow.propiedadesCR.pojo.UserRatingPOJO;

public class UserRatingResponse extends BaseResponse{

	
	UserRatingPOJO userRating;

	public UserRatingPOJO getUserRating() {
		return userRating;
	}

	public void setUserRating(UserRatingPOJO userRating) {
		this.userRating = userRating;
	}
	
	
}
