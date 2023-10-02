package com.thinh.project.Shoptech.service.Impl;


import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Stream;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import com.thinh.project.Shoptech.repository.ImageRepository;
import com.thinh.project.Shoptech.service.ImageService;
@Service
public class ImageServiceImpl implements ImageService{
	private final Path storageFolder = Paths.get("E:\\Shoptech\\src\\main\\resources\\images","category");

	@Autowired
	ImageRepository imageRepository;
	
	public ImageServiceImpl() {
		try {
			Files.createDirectories(storageFolder);
		} catch (Exception e) {
			throw new RuntimeException("Can not initialize storage", e);
		}
	}

	private boolean isImageFile(MultipartFile file) {
		String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
		return Arrays.asList(new String[] {"png", "jpg", "jpeg", "bmp"})
				.contains(fileExtension.trim().toLowerCase());
	}
	

	@Override
	public String storeFile(MultipartFile file) {
		
		try {
			if (file.isEmpty()) {
				throw new RuntimeException("Failed to store empty file");
			}
			
			// check is image file
			if(!isImageFile(file)) {
				throw new RuntimeException("You can only upload image file");
			}
			
			// file must be <= 5mb
			float fileSizeInMegabytes = file.getSize() / 1_000_000.0f;
			
			if (fileSizeInMegabytes > 5.0f) {
				throw new RuntimeException("File must be <= 5Mb");
			}
			
			// file must be rename, except file duplicate
			String fileExtexsion = FilenameUtils.getExtension(file.getOriginalFilename());
			
			String generateFileName = UUID.randomUUID().toString().replace("-", "");
			
			generateFileName = generateFileName + "." + fileExtexsion;
			
			Path destinationFilePath = this.storageFolder.resolve(Paths.get(generateFileName)).normalize().toAbsolutePath();
			  
			if (!destinationFilePath.getParent().equals(this.storageFolder.toAbsolutePath())) {
				throw new RuntimeException("Can not store file outside current directory");
			}
			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, destinationFilePath, StandardCopyOption.REPLACE_EXISTING);
			} 
			
			return generateFileName;
			
		} catch (Exception e) {
			throw new RuntimeException("Failed to store file");
		}
	}

	@Override
	public Stream<Path> loadAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] readFileContent(String fileName) {
		try {
			Path file = storageFolder.resolve(fileName);
			UrlResource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				byte[] bytes = StreamUtils.copyToByteArray(resource.getInputStream());
				return bytes;
			} else {
				throw new RuntimeException("Could not read file" + fileName);
			}
			
		} catch (IOException e) {
			throw new RuntimeException("Could not read file" + fileName, e);
		}
	}

	@Override
	public void deleteAllFiles() {
		// TODO Auto-generated method stub
		
	}
	

	
}
