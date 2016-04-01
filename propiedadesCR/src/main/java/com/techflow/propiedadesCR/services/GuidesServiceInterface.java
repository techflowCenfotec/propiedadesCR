package com.techflow.propiedadesCR.services;

import java.util.List;
import com.techflow.propiedadesCR.ejb.Tguide;
import com.techflow.propiedadesCR.pojo.GuidePOJO;
import com.techflow.propiedadesCR.contracts.GuidesRequest;

public interface GuidesServiceInterface {
	
	Tguide saveGuide(GuidePOJO pguide);
	List<GuidePOJO>getAllByBank(GuidesRequest pguideRequest);

}
