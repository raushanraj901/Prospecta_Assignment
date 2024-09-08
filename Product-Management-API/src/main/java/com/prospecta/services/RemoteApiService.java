package com.prospecta.services;

import java.util.List;

import com.prospecta.exceptions.RemoteApiException;
import com.prospecta.model.Product;

public interface RemoteApiService {
	Product storeProduct(Product product) throws RemoteApiException;

	List<Product> getProductListByCategory(String category) throws RemoteApiException;
}
