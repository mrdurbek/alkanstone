package com.alkan.AlkanStone.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alkan.AlkanStone.entity.Work;
import com.alkan.AlkanStone.payload.ApiResponse;
import com.alkan.AlkanStone.payload.FileDto;
import com.alkan.AlkanStone.repository.WorkRepository;

@Service
public class WorkService {

	@Autowired
	WorkRepository workRepository;
	
	public Page<Work> get(Integer page){
		Pageable pageable = PageRequest.of(page, 10);
		return workRepository.findAll(pageable);
	}
	
	public ApiResponse add(List<FileDto> files) {
		Iterator<FileDto> fileList = files.iterator();
		while(fileList.hasNext()) {
			FileDto file = fileList.next();
			Work work = new Work();
			work.setAttachment(file.getAttachment());
			work.setImageContent(file.getContent());
			workRepository.save(work);
		}
		return new ApiResponse("Succesfully inserted" , true);
	}
	
	public ApiResponse delete(Integer id) {
		try {
			workRepository.deleteById(id);
		} catch (Exception e) {
			return new ApiResponse(e.getMessage() , false);
		}
		return new ApiResponse("Succesfully deleted", true);
	}
	
}

