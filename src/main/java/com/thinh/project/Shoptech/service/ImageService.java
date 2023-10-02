package com.thinh.project.Shoptech.service;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
	
	public String storeFile(MultipartFile files);

	public Stream<Path> loadAll();

	public byte[] readFileContent(String fileName);

	public void deleteAllFiles();
	
	
}
