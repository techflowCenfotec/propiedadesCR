package com.techflow.propiedadesCR.services;

import java.util.List;

import com.techflow.propiedadesCR.contracts.ReviewPropertyRequest;
import com.techflow.propiedadesCR.ejb.TpropertyReview;
import com.techflow.propiedadesCR.pojo.ReviewPropertyPOJO;


public interface RatingServiceInterface {

	TpropertyReview addRating(ReviewPropertyRequest pRating);
	TpropertyReview editRating(ReviewPropertyRequest pRating);
	ReviewPropertyPOJO getRatingById(ReviewPropertyRequest pRating);
}
