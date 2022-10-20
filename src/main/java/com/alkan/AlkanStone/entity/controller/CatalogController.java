package com.alkan.AlkanStone.entity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkan.AlkanStone.payload.ApiResponse;
import com.alkan.AlkanStone.payload.CatalogDto;
import com.alkan.AlkanStone.service.CatalogService;

@RestController
@RequestMapping("/api/catalog")
public class CatalogController {
	
	@Autowired
	CatalogService catalogService;
	
	@GetMapping
	@PreAuthorize(value = "hasRole('ADMIN')")
	public ResponseEntity<?> get(){ 
		return ResponseEntity.ok(catalogService.get());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> get(@PathVariable Integer id){
		return ResponseEntity.ok(catalogService.getCatalog(id));
	}
	
	@PostMapping
	public ResponseEntity<?> add(@RequestBody CatalogDto catalogDto){
		ApiResponse apiResponse = catalogService.add(catalogDto);
		if(apiResponse.isResponse()) {
			return ResponseEntity.ok(apiResponse.getMessage());
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse.getMessage());
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id){
		ApiResponse apiResponse = catalogService.delete(id);
		if(apiResponse.isResponse()) {
			return ResponseEntity.ok(apiResponse.getMessage());
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse.getMessage());
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@RequestBody CatalogDto catalogDto , @PathVariable Integer id){
		ApiResponse apiResponse = catalogService.update(catalogDto, id);
		if(apiResponse.isResponse()) {
			return ResponseEntity.ok(apiResponse.getMessage());
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse.getMessage());
	}
}
