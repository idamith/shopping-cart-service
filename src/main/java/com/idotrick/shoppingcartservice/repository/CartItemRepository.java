package com.idotrick.shoppingcartservice.repository;

import com.idotrick.shoppingcartservice.model.CartItem;
import com.idotrick.shoppingcartservice.model.CartItemId;
import org.springframework.data.repository.CrudRepository;

public interface CartItemRepository extends CrudRepository<CartItem, CartItemId> {
}
