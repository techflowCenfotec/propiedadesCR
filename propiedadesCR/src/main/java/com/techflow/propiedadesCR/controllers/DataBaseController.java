package com.techflow.propiedadesCR.controllers;

import java.sql.DriverManager;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.jdbc.Connection;
import com.techflow.propiedadesCR.contracts.BaseResponse;

@RestController
@RequestMapping(value="rest/database")
public class DataBaseController {

	
	
	
	@RequestMapping(value="/checkDB", method= RequestMethod.GET)
	public BaseResponse checkDB(){
		BaseResponse response = new BaseResponse();
	Connection connection = null;
	try {
	    connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/propiedades_sch", "root", "mjjv");
	    response.setCode(200);
	    return response;
	} catch (Exception e) {
		response.setCode(500);
	   return response;
	} finally {
	    if (connection != null) try { connection.close(); } catch (Exception ignore) {}
	}
			
	
}
}
