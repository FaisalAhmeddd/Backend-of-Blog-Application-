package com.blog.app.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class CategoryDto {
	
	private int categoryId;
	@NotBlank
	@Size(min = 4,max = 50)
	private String categoryTitle;
	@NotBlank
	@Size(min = 10)
	private String categoryDescription;
	

}
