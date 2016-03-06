package com.techflow.propiedadesCR.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletContext;

import org.springframework.web.multipart.MultipartFile;

public class Utils {
	
	private static String RESOURCES_PATH = "resources/images/";
	private static String HOST_PATH = "http://localhost:8080";
	
	// save uploaded file to new location
		public static String writeToFile(MultipartFile file, ServletContext servletContext) {
			String extension = getExtension(file.getOriginalFilename(),".").toLowerCase();
			String consecutiveName = ""+new Date().getTime();
			
			String uploadedFileLocation = servletContext.getRealPath("") + RESOURCES_PATH + consecutiveName + extension;
			String databaseFileName = HOST_PATH + servletContext.getContextPath() + "/" + RESOURCES_PATH + consecutiveName + extension;
			
			byte[] bytes;
			try {
				bytes = file.getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(uploadedFileLocation)));
				stream.write(bytes);
				stream.close();
				
				System.out.println("uploadedFileLocation "+uploadedFileLocation);
				System.out.println("databaseFileName "+databaseFileName);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
	      
			return databaseFileName;
		}
		
		private static String getExtension(String filename, String extensionSeparator) {
		    int dot = filename.lastIndexOf(extensionSeparator);
		    return "."+filename.substring(dot + 1);
		}

}
