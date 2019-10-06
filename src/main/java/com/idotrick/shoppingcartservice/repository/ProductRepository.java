package com.idotrick.shoppingcartservice.repository;

import com.idotrick.shoppingcartservice.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}
