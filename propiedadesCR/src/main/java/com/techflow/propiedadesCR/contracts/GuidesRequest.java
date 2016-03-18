package com.techflow.propiedadesCR.contracts;

import com.techflow.propiedadesCR.pojo.GuidePOJO;

public class GuidesRequest extends BaseRequest{
	
	private GuidePOJO guide;

	public GuidesRequest() {
		super();
	}

	public GuidePOJO getGuide() {
		return guide;
	}

	public void setGuide(GuidePOJO guide) {
		this.guide = guide;
	}
	
	
}
