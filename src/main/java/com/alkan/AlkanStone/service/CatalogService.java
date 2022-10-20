package com.alkan.AlkanStone.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkan.AlkanStone.entity.Catalog;
import com.alkan.AlkanStone.entity.Description;
import com.alkan.AlkanStone.payload.ApiResponse;
import com.alkan.AlkanStone.payload.CatalogDto;
import com.alkan.AlkanStone.repository.CatalogRepository;
import com.alkan.AlkanStone.repository.DescriptionRepository;

@Service
public class CatalogService {
	
	@Autowired
	DescriptionRepository descriptionRepository;
	@Autowired
	CatalogRepository catalogRepository;
	
	public ApiResponse add(CatalogDto catalogDto) {
		Catalog catalog = new Catalog();
		catalog.setName(catalogDto.getName());
		Description description = new Description();
		description.setHeader(catalogDto.getHeader());
		description.setTheme(catalogDto.getTheme());
		description.setText(catalogDto.getText());
		//Description savedDescription = descriptionRepository.save(description);
		catalog.setDescription(description);
		catalogRepository.save(catalog);
		return new ApiResponse("Succesfully inserted" , true);
	}
	
	public List<Catalog> get(){
		return catalogRepository.findAll();
	}
	public Catalog getCatalog(Integer id) {
		Optional<Catalog> optionalCatalog = catalogRepository.findById(id);
		if(optionalCatalog.isEmpty()) return null;
		return optionalCatalog.get();
	}
	
	public ApiResponse delete(Integer id) {
		try {
			catalogRepository.deleteById(id);
		} catch (Exception e) {
			return new ApiResponse(e.getMessage(), false);
		}
		return new ApiResponse("Succesfully deleted" , true);
	}
	
	public ApiResponse update(CatalogDto catalogDto , Integer id) {
		Optional<Catalog> optionalCatalog = catalogRepository.findById(id);
		if(optionalCatalog.isEmpty()) {
			return new ApiResponse("Not Found" , false);
		}
		Catalog catalog = optionalCatalog.get();
		catalog.setName(catalogDto.getName());
		Description description = new Description();
		description.setHeader(catalogDto.getHeader());
		description.setTheme(catalogDto.getTheme());
		description.setText(catalogDto.getText());
		catalog.setDescription(description);
		catalogRepository.save(catalog);
		return new ApiResponse("Succesfully updated" , true);
	}
}
