package com.ecommerce.demo.services;

import java.util.List;

import com.ecommerce.demo.entities.ProductEntity;
import com.ecommerce.demo.responseDto.ListResponse;

public interface ProductService {
	ListResponse<ProductEntity>  getAllProducts(int pageNumber, int pageSize);

	ProductEntity getProductById(Long id);

	ProductEntity createProduct(ProductEntity product);

	ProductEntity updateProduct(Long id, ProductEntity updatedProduct);

	void deleteProduct(Long id);
}
