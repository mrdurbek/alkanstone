package com.alkan.AlkanStone.entity.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alkan.AlkanStone.entity.Attachment;
import com.alkan.AlkanStone.entity.Post;
import com.alkan.AlkanStone.payload.ApiResponse;
import com.alkan.AlkanStone.payload.FileDto;
import com.alkan.AlkanStone.service.PostService;

@RestController
@RequestMapping("/api/post")
public class PostController {
	
	@Autowired
	PostService postService;
	
	@PostMapping
	public ResponseEntity<?> add(@RequestBody Post post){
		ApiResponse apiResponse = postService.add(post);
		if(apiResponse.isResponse()) {
			return ResponseEntity.ok(apiResponse.getMessage());
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse.getMessage());
	}
	
	@PostMapping("/addImage/{id}")
	public ResponseEntity<?> addImage(MultipartHttpServletRequest request , @PathVariable Integer id) throws IOException{
		List<FileDto> fileList = new ArrayList<FileDto>();
		
		Iterator<String> fileNames = request.getFileNames();
		while(fileNames.hasNext()) {
			MultipartFile file = request.getFile(fileNames.next());
			String orginalFileName = file.getOriginalFilename();
			String contentType = file.getContentType();
			long size = file.getSize();
			Attachment attachment = new Attachment(orginalFileName , contentType , size);
			FileDto fileDto = new FileDto(attachment , file.getBytes());
			fileList.add(fileDto);
		}
		ApiResponse apiResponse = postService.addImage(fileList, id);
		
		if(apiResponse.isResponse()) {
			return ResponseEntity.ok(apiResponse.getMessage());
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse.getMessage());
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id){
		ApiResponse apiResponse = postService.delete(id);
		if(apiResponse.isResponse()) {
			return ResponseEntity.ok(apiResponse.getMessage());
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse.getMessage());
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@RequestBody Post post , @PathVariable Integer id){
		ApiResponse apiResponse = postService.update(post, id);
		
		if(apiResponse.isResponse()) {
			return ResponseEntity.ok(apiResponse.getMessage());
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse.getMessage());
	}
}
