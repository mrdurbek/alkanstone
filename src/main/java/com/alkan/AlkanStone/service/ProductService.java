package com.alkan.AlkanStone.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkan.AlkanStone.entity.Attachment;
import com.alkan.AlkanStone.entity.Catalog;
import com.alkan.AlkanStone.entity.Images;
import com.alkan.AlkanStone.entity.Product;
import com.alkan.AlkanStone.payload.ApiResponse;
import com.alkan.AlkanStone.payload.FileDto;
import com.alkan.AlkanStone.payload.ProductDto;
import com.alkan.AlkanStone.repository.AttachmentRepository;
import com.alkan.AlkanStone.repository.CatalogRepository;
import com.alkan.AlkanStone.repository.ImageRepository;
import com.alkan.AlkanStone.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;
	@Autowired
	CatalogRepository catalogRepository;
	@Autowired
	AttachmentRepository attachmentRepository;
	@Autowired
	ImageRepository imageRepository;
	
	public List<Product> get(){	
		return productRepository.findAll();
	}
	
	public ApiResponse add(ProductDto productDto) {
		Product product = new Product();
		product.setName(productDto.getName());
		product.setCountry(productDto.getCountry());
		product.setDescription(productDto.getDescription());
		Optional<Catalog> optionalCatalog = catalogRepository.findById(productDto.getCatalogId());
		if(optionalCatalog.isEmpty()) {
			return new ApiResponse("Not Found" , false);
		}
		Catalog catalog = optionalCatalog.get();
		product.setCatalog(catalog);
		productRepository.save(product);
		return new ApiResponse("Succesfully inserted" , true);
	}
	
	public ApiResponse addImage(List<FileDto> fileDto , Integer id) {
		
		Optional<Product> optionalProduct = productRepository.findById(id);
		if(optionalProduct.isEmpty()) {
			return new ApiResponse("Error occured" , false);
		}
		Product product = optionalProduct.get();
		
		Iterator<FileDto> fileList = fileDto.iterator();
		while(fileList.hasNext()) {
			FileDto file = fileList.next();
			Attachment savedAttachment = attachmentRepository.save(file.getAttachment());
			Images image = new Images();
			image.setImageContent(file.getContent());
			image.setAttachment(savedAttachment);
			image.setProduct(product);
			imageRepository.save(image);
		}
		return new ApiResponse("Succesfully inserted" , true);
	}
	
	public ApiResponse delete(Integer id) {
		try {
			productRepository.deleteById(id);
			return new ApiResponse("Succesfully deleted", true);
		} catch (Exception e) {
			return new ApiResponse("Not Occured", false);
		}
	}
	
	public ApiResponse update(Integer id , ProductDto productDto) {
		Optional<Product> optionalProduct = productRepository.findById(id);
		if(optionalProduct.isEmpty()) {
			return new ApiResponse("Not Found" , false);
		}
		Product updatedProduct = optionalProduct.get();
		updatedProduct.setName(productDto.getName());
		updatedProduct.setDescription(productDto.getDescription());
		updatedProduct.setCountry(productDto.getCountry());
		
		Optional<Catalog> optionalCatalog = catalogRepository.findById(productDto.getCatalogId());
		if(optionalCatalog.isEmpty()) {
			return new ApiResponse("Not Found" , false);
		}
		Catalog catalog = optionalCatalog.get();
		updatedProduct.setCatalog(catalog);
		productRepository.save(updatedProduct);
		return new ApiResponse("Succesfully inserted" , true);
	}
}
