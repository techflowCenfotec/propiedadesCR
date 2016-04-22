package com.techflow.propiedadesCR.services;

import com.techflow.propiedadesCR.contracts.GuidesRequest;
import com.techflow.propiedadesCR.contracts.GuidesResponse;
import com.techflow.propiedadesCR.ejb.Tguide;
import com.techflow.propiedadesCR.pojo.GuidePOJO;

public interface GuidesServiceInterface {
	
	Tguide saveGuide(GuidePOJO pguide);
	GuidesResponse getAllByBank(GuidesRequest pguideRequest);

}
