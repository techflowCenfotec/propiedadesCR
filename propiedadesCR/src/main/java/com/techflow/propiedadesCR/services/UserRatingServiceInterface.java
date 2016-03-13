package com.techflow.propiedadesCR.services;

import com.techflow.propiedadesCR.contracts.UserRatingRequest;
import com.techflow.propiedadesCR.ejb.TuserRating;

public interface UserRatingServiceInterface {
	
	TuserRating saveRating(UserRatingRequest puserRating);

}
