package com.ecommerce.demo.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.ecommerce.demo.entities.ProductEntity;
import com.ecommerce.demo.repositories.ProductRepository;
import com.ecommerce.demo.responseDto.ListResponse;
import com.ecommerce.demo.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

//    public ProductServiceImpl(ProductRepository productRepository) {
//        this.productRepository = productRepository;
//    }

    @Override
    public ListResponse<ProductEntity>  getAllProducts(int pageNumber, int pageSize) {
    	
    	Pageable pagination  = PageRequest.of(pageNumber - 1,pageSize);
  
    	Page<ProductEntity> result = productRepository.findAll(pagination);
    	
    	ListResponse<ProductEntity> response = new ListResponse<>();
    	response.setData(result.getContent());
    	response.setTotal(result.getTotalElements());
    	response.setCurrentPage(result.getNumber() + 1);
    	response.setHasMore(result.hasNext());	
    	if(response.isHasMore()) {
    		response.setNextPage(result.getNumber() + 1);
    	}
    
        return response;
    }

    @Override
    public ProductEntity getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public ProductEntity createProduct(ProductEntity product) {
        return productRepository.save(product);
    }

    @Override
    public ProductEntity updateProduct(Long id, ProductEntity updatedProduct) {
    	ProductEntity existingProduct = getProductById(id);
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());
        return productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

}
