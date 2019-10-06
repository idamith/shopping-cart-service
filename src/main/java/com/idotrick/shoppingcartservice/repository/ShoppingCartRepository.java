package com.idotrick.shoppingcartservice.repository;

import com.idotrick.shoppingcartservice.model.ShoppingCart;
import org.springframework.data.repository.CrudRepository;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Integer> {
}
