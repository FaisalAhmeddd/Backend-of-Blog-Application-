package com.blog.app.controllers;

import java.util.List;

import javax.validation.Valid;

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

import com.blog.app.payloads.ApiResponse;
import com.blog.app.payloads.CategoryDto;
import com.blog.app.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	//create
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
	CategoryDto createdCategoryDto =  	this.categoryService.createCategory(categoryDto);
	return new ResponseEntity<CategoryDto>(createdCategoryDto, HttpStatus.CREATED);
	}
	
	
	//update
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,
			@PathVariable("categoryId")Integer categoryId ){
	CategoryDto updatedCategoryDto=	this.categoryService.updateCategory(categoryDto, categoryId);
	return new ResponseEntity<CategoryDto>(updatedCategoryDto, HttpStatus.OK);
	}
	
	
	//delete
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryId") Integer categoryId){
		this.categoryService.deleteCategory(categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted successfully!!!", true), HttpStatus.OK);
	}
	
	
	
	//getbyId
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getCategoryIdBy(@PathVariable("categoryId")Integer categoryId){
		return new ResponseEntity<CategoryDto>(this.categoryService.getById(categoryId), HttpStatus.OK);
	}
	
	//getAllCategories
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>>getAllCategories(){
		return  ResponseEntity.ok(this.categoryService.getAll());
	}

}
