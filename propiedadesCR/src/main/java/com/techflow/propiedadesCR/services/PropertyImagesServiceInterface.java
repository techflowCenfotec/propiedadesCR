package com.techflow.propiedadesCR.services;

import java.util.List;

import com.techflow.propiedadesCR.ejb.TpropertyImage;
import com.techflow.propiedadesCR.pojo.PropertyImagePOJO;

public interface PropertyImagesServiceInterface {

	List<PropertyImagePOJO> getAll();
	TpropertyImage getImageById(int pIdPropiedad);
	
}
