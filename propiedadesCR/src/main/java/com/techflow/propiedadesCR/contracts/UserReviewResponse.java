package com.techflow.propiedadesCR.contracts;

import com.techflow.propiedadesCR.pojo.UserReviewPOJO;

public class UserReviewResponse extends BaseResponse{

	
	UserReviewPOJO userRating;

	public UserReviewPOJO getUserRating() {
		return userRating;
	}

	public void setUserRating(UserReviewPOJO userRating) {
		this.userRating = userRating;
	}
	
	
}
