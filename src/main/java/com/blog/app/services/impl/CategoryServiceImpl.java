package com.blog.app.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.app.entity.Category;
import com.blog.app.exceptions.ResourceNotFoundException;
import com.blog.app.payloads.CategoryDto;
import com.blog.app.repositories.CategoryRepository;
import com.blog.app.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = this.dtoToCategory(categoryDto);
		Category savedCategory = this.categoryRepository.save(category);
		return this.categoryToDto(savedCategory);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		Category updatedCategory = this.categoryRepository.save(category);
		return this.categoryToDto(updatedCategory);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		 this.categoryRepository.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId", categoryId));
		this.categoryRepository.deleteById(categoryId);
		
	}

	@Override
	public CategoryDto getById(Integer categoryId) {
	Category category=	this.categoryRepository.findById(categoryId)
		.orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId", categoryId));
	return this.categoryToDto(category);
		
	}

	@Override
	public List<CategoryDto> getAll() {
		List<Category> categories= this.categoryRepository.findAll();
		List<CategoryDto> categoryDtos =categories.stream()
				.map(category -> this.categoryToDto(category)).collect(Collectors.toList());
		return categoryDtos;
	}
	
	public Category dtoToCategory(CategoryDto categoryDto) {
	Category category=	this.modelMapper.map(categoryDto, Category.class);
		return category;
		}
	
	public CategoryDto categoryToDto(Category category) {
		CategoryDto categoryDto = this.modelMapper.map(category, CategoryDto.class);
		return categoryDto
				;
	}
	

}
