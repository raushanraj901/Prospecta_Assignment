package com.prospecta.services;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.prospecta.model.Product;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {

    private final RestTemplate restTemplate;

    public ProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Product> getProductsByCategory(String category) {
        
        String url = "https://fakestoreapi.com/products/category/" + category;

        Product[] products = restTemplate.getForObject(url, Product[].class);

        return Arrays.asList(products);
    }

    public Product addProduct(Product product) {
        String url = "https://fakestoreapi.com/products";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<Product> requestEntity = new HttpEntity<>(product, headers);

        try {
            ResponseEntity<Product> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Product.class);
            return response.getBody();
        } catch (RestClientException e) {
            System.out.println("Failed to create product " + e.getMessage());
        }
        return null;
    }
}
