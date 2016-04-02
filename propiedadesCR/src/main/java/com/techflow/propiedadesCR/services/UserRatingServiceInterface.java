package com.techflow.propiedadesCR.services;

import com.techflow.propiedadesCR.contracts.UserRatingRequest;
import com.techflow.propiedadesCR.ejb.TuserRating;
import com.techflow.propiedadesCR.pojo.UserRatingPOJO;

public interface UserRatingServiceInterface {
	
	TuserRating saveRating(UserRatingRequest puserRating);

	UserRatingPOJO getRating(UserRatingRequest puserRating);

	TuserRating modifyRating(UserRatingRequest puserRequest);
}
