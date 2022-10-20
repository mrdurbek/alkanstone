package com.alkan.AlkanStone.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkan.AlkanStone.entity.Attachment;
import com.alkan.AlkanStone.entity.Post;
import com.alkan.AlkanStone.entity.PostImage;
import com.alkan.AlkanStone.payload.ApiResponse;
import com.alkan.AlkanStone.payload.FileDto;
import com.alkan.AlkanStone.repository.AttachmentRepository;
import com.alkan.AlkanStone.repository.PostImageRepository;
import com.alkan.AlkanStone.repository.PostRepository;

@Service
public class PostService {
	
	@Autowired
	PostRepository postRepository;
	@Autowired
	PostImageRepository postImageRepository;
	@Autowired
	AttachmentRepository attachmentRepository;
	
	public ApiResponse add(Post post) {
		postRepository.save(post);
		return new ApiResponse("Succesfully inserted" , true);
	}
	
	public ApiResponse addImage(List<FileDto> fileDto , Integer id) {
		
		Optional<Post> optionalPost = postRepository.findById(id);
		if(optionalPost.isEmpty()) {
			return new ApiResponse("Error occured" , false);
		}
		Post post = optionalPost.get();
		
		Iterator<FileDto> fileList = fileDto.iterator();
		while(fileList.hasNext()) {
			FileDto file = fileList.next();
			Attachment savedAttachment = attachmentRepository.save(file.getAttachment());
			PostImage postImage = new PostImage();
			postImage.setImageContent(file.getContent());
			postImage.setAttachment(savedAttachment);
			postImage.setPost(post);
			postImageRepository.save(postImage);
		}
		return new ApiResponse("Succesfully inserted" , true);
	}
	
	public ApiResponse delete(Integer id) {
		try {
			postRepository.deleteById(id);
		} catch (Exception e) {
			return new ApiResponse(e.getMessage(), false);
		}
		return new ApiResponse("Succesfully deleted", true);
	}
	
	public ApiResponse update(Post post , Integer id) {
		Optional<Post> optionalPost = postRepository.findById(id);
		if(optionalPost.isEmpty()) {
			return new ApiResponse("Not Found" ,false);
		}
		
		Post updatedPost = optionalPost.get();
		updatedPost.setHeader(post.getHeader());
		updatedPost.setIntroduction(post.getIntroduction());
		updatedPost.setText(post.getHeader());
		postRepository.save(updatedPost);
		return new ApiResponse("Succesfully updated" , true);
	}
}
