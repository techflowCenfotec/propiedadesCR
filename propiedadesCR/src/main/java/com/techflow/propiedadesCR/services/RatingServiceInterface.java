package com.techflow.propiedadesCR.services;

import java.util.List;

import com.techflow.propiedadesCR.contracts.RatingRequest;
import com.techflow.propiedadesCR.ejb.TpropertyRating;
import com.techflow.propiedadesCR.pojo.RatingPOJO;


public interface RatingServiceInterface {

	TpropertyRating addRating(RatingRequest pRating);
	TpropertyRating editRating(RatingRequest pRating);
	RatingPOJO getRatingById(RatingRequest pRating);
}
