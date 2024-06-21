package com.ecommerce.ecommercebackend.model.dao;

import com.ecommerce.ecommercebackend.model.Product;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface ProductDAO extends ListCrudRepository<Product, Long> {
    List<Product> findByName(String name);
}
