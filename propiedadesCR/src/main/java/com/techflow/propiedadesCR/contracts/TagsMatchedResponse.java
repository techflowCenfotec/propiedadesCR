package com.techflow.propiedadesCR.contracts;

import java.util.List;

import com.techflow.propiedadesCR.pojo.AnswerPOJO;

public class TagsMatchedResponse extends BaseResponse {

	private List<AnswerPOJO> tags;
	private boolean[] matchedTags;
	
	public TagsMatchedResponse() {
		super();
	}

	

	public List<AnswerPOJO> getTags() {
		return tags;
	}



	public void setTags(List<AnswerPOJO> tags) {
		this.tags = tags;
	}



	public boolean[] getMatchedTags() {
		return matchedTags;
	}

	public void setMatchedTags(boolean[] matchedTags) {
		this.matchedTags = matchedTags;
	}
	
	

}
