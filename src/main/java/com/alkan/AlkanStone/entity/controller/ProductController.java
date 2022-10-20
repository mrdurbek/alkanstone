package com.alkan.AlkanStone.entity.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alkan.AlkanStone.entity.Attachment;
import com.alkan.AlkanStone.payload.ApiResponse;
import com.alkan.AlkanStone.payload.FileDto;
import com.alkan.AlkanStone.payload.ProductDto;
import com.alkan.AlkanStone.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping
	public ResponseEntity<?> get(){
		return ResponseEntity.ok(productService.get());
	}
	
	@PostMapping
	public ResponseEntity<?> add(@RequestBody ProductDto productDto) throws IOException{
		
		ApiResponse apiResponse = productService.add(productDto);
		
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
		ApiResponse apiResponse = productService.addImage(fileList, id);
		if(apiResponse.isResponse()) {
			return ResponseEntity.ok(apiResponse.getMessage());
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse.getMessage());
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id){
		ApiResponse apiResponse = productService.delete(id);
		if(apiResponse.isResponse()) {
			return ResponseEntity.ok(apiResponse.getMessage());
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse.getMessage());
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id , @RequestBody ProductDto productDto){
		ApiResponse apiResponse = productService.delete(id);
		if(apiResponse.isResponse()) {
			return ResponseEntity.ok(apiResponse.getMessage());
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse.getMessage());
	}
}
