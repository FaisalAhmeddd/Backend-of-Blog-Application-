package com.blog.app.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.blog.app.entity.Comment;
import com.blog.app.entity.Post;
import com.blog.app.exceptions.ResourceNotFoundException;
import com.blog.app.payloads.CommentDto;
import com.blog.app.repositories.CommentRepository;
import com.blog.app.repositories.PostRepository;
import com.blog.app.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
	Post post =	this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "postId", postId));
	Comment comment = this.modelMapper.map(commentDto, Comment.class);
	comment.setPost(post);
	Comment savedComment=this.commentRepository.save(comment);
	return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
	Comment comment =this.commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "commentId", commentId));
	this.commentRepository.delete(comment);
		
	}
}
