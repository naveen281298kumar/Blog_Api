package com.blog.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl {

	public String uploadImage(String path, MultipartFile file) throws IOException {
		
//		File Name
		String name = file.getOriginalFilename();
		
//		random name generate file
		String randomId = UUID.randomUUID().toString();
		String fileName = randomId.concat(name.substring(name.lastIndexOf(".")));  
		
//		Full path
		String filePath = path + File.separator + fileName;
		
//		create folder if not created
		File f = new File(path);
		if(!f.exists()) {
			f.mkdir();
		}
		
//		file copy
		Files.copy(file.getInputStream(), Paths.get(filePath));
		
		return fileName;
	}
	
	public InputStream getResource(String path, String fileName) throws FileNotFoundException{
		
		String fullpath = path + File.separator + fileName;
		InputStream i = new FileInputStream(fullpath);
		return i;
		
	}
}
