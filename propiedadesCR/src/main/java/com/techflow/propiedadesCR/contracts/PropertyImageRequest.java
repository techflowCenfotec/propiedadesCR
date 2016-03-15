package com.techflow.propiedadesCR.contracts;

import com.techflow.propiedadesCR.pojo.PropertyImagePOJO;

public class PropertyImageRequest extends BaseRequest {

	PropertyImagePOJO propertyImage;

	public PropertyImagePOJO getPropertyImage() {
		return propertyImage;
	}

	public void setPropertyImage(PropertyImagePOJO propertyImage) {
		this.propertyImage = propertyImage;
	}
	
	@Override
	public String toString() {
		return "PropertyImageRequest [Image=" + propertyImage + "]";
	}
}
