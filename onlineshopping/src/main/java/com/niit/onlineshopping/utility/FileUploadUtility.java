package com.niit.onlineshopping.utility;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;


public class FileUploadUtility {
	
	private static final Logger logger = LoggerFactory.getLogger(FileUploadUtility.class);
	
	//Represent Project location
	private static final String ABS_PATH = "E:\\Digital Trainsformation\\diginxt_projects\\online-shopping\\onlineshopping\\src\\main\\webapp\\assets\\images\\";
	//Represent path where tomcat deploy this application
	private static  String REAL_PATH = "";
	
	public static void uploadFile(HttpServletRequest request, MultipartFile file, String code) {
		
		//get the real path
		REAL_PATH = request.getSession().getServletContext().getRealPath("/assets/images/");
		
		logger.info(REAL_PATH);
		
		//simple check to make sure all the directory exist , create the directory
		if(!new File(ABS_PATH).exists()){
			//create the directory
			new File(ABS_PATH).mkdirs();
		}
		if(!new File(REAL_PATH).exists()){
			//create the directory
			new File(REAL_PATH).mkdirs();
		}
		
		//aftrer creating the directory
		try{
			//server upload
			file.transferTo(new File(REAL_PATH + code + ".jpg"));
			//project directory upload
			file.transferTo(new File(ABS_PATH + code + ".jpg"));
		}catch(IOException ie){}
	}
	

}
