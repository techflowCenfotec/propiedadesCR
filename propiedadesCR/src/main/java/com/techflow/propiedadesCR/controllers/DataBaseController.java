package com.techflow.propiedadesCR.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.DriverManager;
import java.util.Enumeration;
import java.util.Properties;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.jdbc.Connection;
import com.techflow.propiedadesCR.contracts.BaseResponse;

@RestController
@RequestMapping(value="rest/protected/database")
public class DataBaseController {
		
	@RequestMapping(value="/checkDB", method= RequestMethod.GET)
	public BaseResponse checkDB(){
    BaseResponse response = new BaseResponse();
	Connection connection = null;
	String host ="";
	String userName ="";
	String pass = "";
	try {
		File file = new File("src/main/resources/application.properties");
		FileInputStream fileInput = new FileInputStream(file);
		Properties properties = new Properties();
		properties.load(fileInput);
		fileInput.close();

		host = properties.getProperty("spring.datasource.url");
		userName = 	properties.getProperty("spring.datasource.username");
		pass = 	properties.getProperty("spring.datasource.password");	
		
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}

	try {
	    connection = (Connection) DriverManager.getConnection(host, userName, pass);
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
