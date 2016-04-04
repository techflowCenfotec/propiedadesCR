package com.techflow.propiedadesCR.contracts;

import java.util.List;

import com.techflow.propiedadesCR.pojo.ReviewPropertyPOJO;

public class ReviewPropertyResponse extends BaseResponse {

	List<ReviewPropertyPOJO> ratings;
	ReviewPropertyPOJO rating;

	public List<ReviewPropertyPOJO> getRatings() {
		return ratings;
	}

	public void setRatings(List<ReviewPropertyPOJO> ratings) {
		this.ratings = ratings;
	}

	public ReviewPropertyPOJO getRating() {
		return rating;
	}

	public void setRating(ReviewPropertyPOJO rating) {
		this.rating = rating;
	}
}
