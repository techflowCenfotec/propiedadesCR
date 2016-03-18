package com.techflow.propiedadesCR.services;

import com.techflow.propiedadesCR.contracts.RatingRequest;
import com.techflow.propiedadesCR.ejb.TpropertyRating;

public interface RatingServiceInterface {

	TpropertyRating addRating(RatingRequest pRating);
}
