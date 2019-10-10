package com.idotrick.shoppingcartservice.service;

import com.idotrick.shoppingcartservice.configuration.MessageConfig;
import com.idotrick.shoppingcartservice.message.ItemSelectMessage;
import com.idotrick.shoppingcartservice.model.*;
import com.idotrick.shoppingcartservice.repository.CartItemRepository;
import com.idotrick.shoppingcartservice.repository.CustomerRepository;
import com.idotrick.shoppingcartservice.repository.ProductRepository;
import com.idotrick.shoppingcartservice.repository.ShoppingCartRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class ItemSelectMessageReceiveService {
  private static Logger log = LoggerFactory.getLogger(ItemSelectMessageReceiveService.class);

  @Autowired
  CustomerRepository customerRepository;
  @Autowired
  ShoppingCartRepository shoppingCartRepository;
  @Autowired
  CartItemRepository cartItemRepository;
  @Autowired
  ProductRepository productRepository;

  @JmsListener(destination = MessageConfig.CART_ITEM_SELECT_QUEUE, containerFactory = "myFactory")
  public void execute(ItemSelectMessage message) {
    log.info("--SCS--[Received]: " + message);

    ShoppingCart shoppingCart = shoppingCartRepository
        .findById(message.getShoppingCartId())
        .orElse(null);

    if (null == shoppingCart) {
      Customer customer = customerRepository.findById(message.getCustomerId()).orElse(null);
      shoppingCart = new ShoppingCart(customer);
      shoppingCart = shoppingCartRepository.save(shoppingCart);
    }

    Product product = productRepository.findById(message.getProductId()).orElse(null);

    int itemCount = message.getCount();
    CartItemId cartItemId = new CartItemId(shoppingCart, product);
    CartItem cartItem = cartItemRepository.findById(cartItemId).orElse(null);

    if (null != cartItem) {
      itemCount += cartItem.getQuantity();

      if (itemCount > 0) {
        cartItem.setQuantity(itemCount);
        cartItemRepository.save(cartItem);
      } else {
        cartItemRepository.delete(cartItem);
      }
    } else {
      if (itemCount > 0) {
        cartItemRepository.save(new CartItem(cartItemId, itemCount));
      }
    }

    log.info("--SCS--[Persisted]: " + shoppingCartRepository.findById(shoppingCart.getId()).orElse(null));
  }

}
