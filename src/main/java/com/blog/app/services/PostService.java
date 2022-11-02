package com.blog.app.services;

import java.util.List;

import com.blog.app.entity.Post;
import com.blog.app.payloads.PostDto;
import com.blog.app.payloads.PostResponse;

public interface PostService {
	
	//create
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	
	//update
	PostDto updatePost(PostDto postDto,Integer postId);
	
	//delete
	public void deletePost(Integer postId);
	
	//get all posts
	public PostResponse getAllPosts(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	
	//get all posts by post id
	public PostDto getPostById(Integer postId);
	
	//get post by category
	public List<PostDto> getPostByCategory(Integer categoryId);
	
	//get post by user
	public List<PostDto> getPostByUserId(Integer userId);
	
	
	//search post by keyword
	public List<PostDto> searchPost(String keyword);

}
