package com.techflow.propiedadesCR.services;

import com.techflow.propiedadesCR.contracts.UserReviewRequest;
import com.techflow.propiedadesCR.ejb.TuserReview;
import com.techflow.propiedadesCR.pojo.UserReviewPOJO;

public interface UserReviewServiceInterface {
	
	UserReviewPOJO saveRating(UserReviewRequest puserRating);

	UserReviewPOJO getRating(UserReviewRequest puserRating);

	UserReviewPOJO modifyRating(UserReviewRequest puserRequest);
}
