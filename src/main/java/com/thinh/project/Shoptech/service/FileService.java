package com.thinh.project.Shoptech.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.thinh.project.Shoptech.payload.request.FileUploadDto;

public interface FileService {
	
	List<FileUploadDto> uploadFiles(MultipartFile[] files);
	
}
