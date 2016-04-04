package com.techflow.propiedadesCR.contracts;

import com.techflow.propiedadesCR.pojo.ReviewPropertyPOJO;

public class ReviewPropertyRequest extends BaseRequest {
	ReviewPropertyPOJO rating;

	public ReviewPropertyPOJO getRating() {
		return rating;
	}

	public void setRating(ReviewPropertyPOJO rating) {
		this.rating = rating;
	}
}
