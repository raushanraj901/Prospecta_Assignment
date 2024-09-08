package com.prospecta.services;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.prospecta.exceptions.RemoteApiException;
import com.prospecta.model.Product;

@Service
public class RemoteApiServiceImpl implements RemoteApiService {

	@Value("${remote.api.get.product.url}")
	private String FETCH_Product_URL;

	private static final Logger logger = LoggerFactory.getLogger(RemoteApiServiceImpl.class);

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public Product storeProduct(Product product) throws RemoteApiException {

		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		HttpEntity<Product> request = new HttpEntity<>(product, headers);
		try {
			ResponseEntity<Product> response = restTemplate.exchange(FETCH_Product_URL, HttpMethod.POST, request,
					Product.class);
			return response.getBody();

		} catch (HttpClientErrorException | HttpServerErrorException e) {
			logger.error("Client/Server error occurred while creating product to remote API: {}", e.getMessage());
			throw new RemoteApiException("Error creating product to remote API", e);
		} catch (Exception e) {
			logger.error("Unexpected error occurred while creating product to remote API", e);
			throw new RemoteApiException("Unexpected error creating product to remote API", e);
		}
	}

	@Override
	public List<Product> getProductListByCategory(String category) throws RemoteApiException {
		String url = FETCH_Product_URL + "/category/" + category;
		try {
			ResponseEntity<Product[]> response = restTemplate.exchange(url, HttpMethod.GET, null, Product[].class);
			return Arrays.asList(response.getBody());

		} catch (HttpClientErrorException | HttpServerErrorException e) {
			logger.error("Client/Server error occurred while fetching product from remote API: {}", e.getMessage());
			throw new RemoteApiException("Error fetching product from remote API", e);
		} catch (Exception e) {
			logger.error("Unexpected error occurred while fetching product from remote API", e);
			throw new RemoteApiException("Unexpected error fetching product from remote API", e);
		}
	}
}
