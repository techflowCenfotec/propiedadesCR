package com.techflow.propiedadesCR.contracts;

import java.util.List;

import com.techflow.propiedadesCR.pojo.PropertyImagePOJO;

public class PropertyImageResponse extends BaseResponse {

	List<PropertyImagePOJO> images;

	public List<PropertyImagePOJO> getImages() {
		return images;
	}

	public void setImages(List<PropertyImagePOJO> images) {
		this.images = images;
	}
	
}
