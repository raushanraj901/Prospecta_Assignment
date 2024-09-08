package com.prospecta.model;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Product {

	private Integer id;
	@NotBlank(message = "Title Should Not Null")
	@Size(max = 100, message = "Title cannot be exceed 100 characters")
	private String title;

	@NotNull(message = "Price Should Not Null")
	@DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
	private Double price;

	@Size(max = 500, message = "Description cannot be exceed 500 characters")
	private String description;

	@NotBlank(message = "Category Should Not Null")
	@Size(max = 50, message = "Category cannot exceed 50 characters")
	private String category;

	@URL(message = "Please enter a valid URL")
	private String image;
}
