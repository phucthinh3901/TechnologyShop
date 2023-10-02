package com.thinh.project.Shoptech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.thinh.project.Shoptech.payload.response.ApiResponse;
import com.thinh.project.Shoptech.service.ImageService;
import com.thinh.project.Shoptech.service.ImageStorageService;

@RestController
@CrossOrigin()
@RequestMapping("/api/image")
public class ImageController {
	@Autowired
	private ImageService imageService;
	
	@Autowired 
	private ImageStorageService imageStorageService;
	
	@PostMapping("")
	public ResponseEntity<ApiResponse> uploadFiles(@RequestBody() MultipartFile file) {
		try {
			// save file to a folder => use service
			String generateedFileName = imageService.storeFile(file);
			return ResponseEntity.status(HttpStatus.OK).body(
						new ApiResponse(true, "upload file successfully", generateedFileName)
					);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
					new ApiResponse(false, e.getMessage(),"")
				);
		}	
	}
	
	@PostMapping(value = "/product/{id}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<Boolean> uploadImageProduct(@RequestParam("files") List<MultipartFile> file, @PathVariable int id) {
		final Boolean result = imageStorageService.saveProductImage(file, id);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}
	

//	@PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
//	public ResponseEntity<List<FileUploadDto>> addFile(@RequestParam("files") MultipartFile files) {
//		final List<FileUploadDto> result = imageService.storeFile(files);
//		return new ResponseEntity<List<FileUploadDto>>(result, HttpStatus.OK);
//	}
	
	@GetMapping("/files/{fileName:.+}")
	public ResponseEntity<byte[]> readDetailFile(@PathVariable String fileName) {
		try {
			byte[] bytes = imageService.readFileContent(fileName);
			return ResponseEntity
					.ok()
					.contentType(MediaType.IMAGE_JPEG)
					.body(bytes);
		} catch (Exception e) {
			return ResponseEntity.noContent().build();
		}
	}
	
	
}
