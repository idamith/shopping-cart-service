package com.idotrick.shoppingcartservice;

import com.idotrick.shoppingcartservice.model.*;
import com.idotrick.shoppingcartservice.repository.CartItemRepository;
import com.idotrick.shoppingcartservice.repository.CustomerRepository;
import com.idotrick.shoppingcartservice.repository.ProductRepository;
import com.idotrick.shoppingcartservice.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemSelectMessageProcessService {
  @Autowired
  ShoppingCartRepository shoppingCartRepository;
  @Autowired
  CartItemRepository cartItemRepository;

  public void execute(ItemSelectMessage message) {

    ShoppingCart shoppingCart = shoppingCartRepository
        .findById(message.getShoppingCart().getId())
        .orElse(null);

    shoppingCart = null != shoppingCart ? shoppingCart : shoppingCartRepository.save(message.getShoppingCart());

    Product product = message.getProduct();
    int itemCount = message.getCount();
    CartItemId cartItemId = new CartItemId(shoppingCart, product);
    CartItem cartItem = cartItemRepository.findById(cartItemId).orElse(null);

    if(null != cartItem) {
      itemCount += cartItem.getQuantity();

      if(itemCount > 0) {
        cartItem.setQuantity(itemCount);
        cartItemRepository.save(cartItem);
      } else {
        cartItemRepository.delete(cartItem);
      }
    } else {
      if(itemCount > 0) {
        cartItemRepository.save(new CartItem(cartItemId, itemCount));
      }
    }

    System.out.println("--: "+shoppingCart);
  }

}
