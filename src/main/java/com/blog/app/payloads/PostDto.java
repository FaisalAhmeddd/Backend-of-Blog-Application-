package com.blog.app.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;


import com.blog.app.entity.Comment;


import lombok.Data;

@Data
public class PostDto {
	
	private Integer postId;
	
	@NotEmpty
	private String title;
	
	@NotEmpty
	private String content;
	
	private String imageName;
	
	private Date addedDate;
	
	private CategoryDto category;
	
	private UserDto user;
	
	private Set<CommentDto> comments = new HashSet<>();
	
	

}
