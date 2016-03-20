package com.techflow.propiedadesCR.contracts;

import java.util.List;

import com.techflow.propiedadesCR.pojo.GuidePOJO;

public class GuidesResponse extends BaseResponse{
	
	private List<GuidePOJO> guides;

	public GuidesResponse() {
		super();
	}

	public List<GuidePOJO> getGuides() {
		return guides;
	}

	public void setGuides(List<GuidePOJO> guides) {
		this.guides = guides;
	}
	

}
