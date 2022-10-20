package com.alkan.AlkanStone.entity.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alkan.AlkanStone.entity.Attachment;
import com.alkan.AlkanStone.entity.Gallery;
import com.alkan.AlkanStone.payload.ApiResponse;
import com.alkan.AlkanStone.payload.FileDto;
import com.alkan.AlkanStone.service.GalleryService;

@RestController
@RequestMapping("/api/gallery")
public class GalleryController {
	
	@Autowired
	GalleryService galleryService;
	
	@GetMapping
	public ResponseEntity<Page<Gallery>> getStudents(@RequestParam(value = "page") Integer page){
		return ResponseEntity.ok(galleryService.get(page));
	}
	
	@PostMapping
	public ResponseEntity<?> add(MultipartHttpServletRequest request) throws IOException{
		
		List<FileDto> fileList = new ArrayList<FileDto>();
		
		Iterator<String> fileNames = request.getFileNames();
		while(fileNames.hasNext()) {
			MultipartFile file = request.getFile(fileNames.next());
			String orginalFileName = file.getOriginalFilename();
			long size = file.getSize();
			String contentType = file.getContentType();
			Attachment attachment = new Attachment(orginalFileName, contentType, size);
			FileDto fileDto = new FileDto(attachment, file.getBytes());
			fileList.add(fileDto);
		}
		ApiResponse apiResponse = galleryService.add(fileList);
		if(apiResponse.isResponse()) {
			return ResponseEntity.ok(apiResponse.getMessage());
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse.getMessage());
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id){
		ApiResponse apiResponse = galleryService.delete(id);
		if(apiResponse.isResponse()) {
			return ResponseEntity.ok(apiResponse.getMessage());
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse.getMessage());
	}
}
