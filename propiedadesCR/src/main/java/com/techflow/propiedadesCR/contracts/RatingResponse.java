package com.techflow.propiedadesCR.contracts;

import java.util.List;

import com.techflow.propiedadesCR.pojo.RatingPOJO;

public class RatingResponse extends BaseResponse {

	List<RatingPOJO> ratings;
	RatingPOJO rating;

	public List<RatingPOJO> getRatings() {
		return ratings;
	}

	public void setRatings(List<RatingPOJO> ratings) {
		this.ratings = ratings;
	}

	public RatingPOJO getRating() {
		return rating;
	}

	public void setRating(RatingPOJO rating) {
		this.rating = rating;
	}
}
