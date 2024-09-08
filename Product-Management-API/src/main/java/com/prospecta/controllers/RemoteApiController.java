package com.prospecta.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prospecta.model.Product;
import com.prospecta.services.RemoteApiService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class RemoteApiController {

	@Autowired
	RemoteApiService remoteApiService;

	@PostMapping("/")
	public Product createProduct(@RequestBody @Valid Product product) {
		return remoteApiService.storeProduct(product);
	}

	@GetMapping("/category/{category}")
	public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String category) {
		List<Product> products = remoteApiService.getProductListByCategory(category);
		return ResponseEntity.ok(products);
	}

}
