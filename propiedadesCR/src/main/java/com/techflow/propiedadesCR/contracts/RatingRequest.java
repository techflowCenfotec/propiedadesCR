package com.techflow.propiedadesCR.contracts;

import com.techflow.propiedadesCR.pojo.RatingPOJO;

public class RatingRequest extends BaseRequest {
	RatingPOJO rating;

	public RatingPOJO getRating() {
		return rating;
	}

	public void setRating(RatingPOJO rating) {
		this.rating = rating;
	}
}
