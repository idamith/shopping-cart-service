package com.idotrick.shoppingcartservice.service;

import com.idotrick.shoppingcartservice.message.ItemSelectMessage;
import com.idotrick.shoppingcartservice.model.*;
import com.idotrick.shoppingcartservice.repository.CartItemRepository;
import com.idotrick.shoppingcartservice.repository.CustomerRepository;
import com.idotrick.shoppingcartservice.repository.ProductRepository;
import com.idotrick.shoppingcartservice.repository.ShoppingCartRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
    ItemSelectMessageReceiveService.class,
    CustomerRepository.class,
    ShoppingCartRepository.class,
    CartItemRepository.class,
    ProductRepository.class
  })
public class ItemSelectMessageReceiveServiceTest {

  @Autowired
  ItemSelectMessageReceiveService itemSelectMessageReceiveService;
  @MockBean
  CustomerRepository customerRepository;
  @MockBean
  ShoppingCartRepository shoppingCartRepository;
  @MockBean
  CartItemRepository cartItemRepository;
  @MockBean
  ProductRepository productRepository;

  @Test
  public void execute_with_existing_cart_and_cartItem() {
    ItemSelectMessage message = new ItemSelectMessage(1, 1, 1, 1);

    ShoppingCart shoppingCart = new ShoppingCart();
    when(shoppingCartRepository.findById(anyInt()))
        .thenReturn(Optional.of(shoppingCart))
        .thenReturn(Optional.of(shoppingCart));

    Product product = new Product();
    when(productRepository.findById(anyInt()))
        .thenReturn(Optional.of(product));

    CartItem cartItem = new CartItem(new CartItemId(), 0);
    when(cartItemRepository.findById(any(CartItemId.class)))
        .thenReturn(Optional.of(cartItem));

    when(cartItemRepository.save(any(CartItem.class)))
        .thenReturn(cartItem);

    assertThat(message.getCount()).isGreaterThan(0);
    itemSelectMessageReceiveService.execute(message);

    verify(shoppingCartRepository, times(2)).findById(anyInt());
    verify(productRepository, times(1)).findById(anyInt());
    verify(cartItemRepository, times(1)).findById(any(CartItemId.class));
    verify(cartItemRepository, times(1)).save(any(CartItem.class));
  }

  @Test
  public void execute_with_existing_cart_and_cartItem_with_negative_itemCount() {
    ItemSelectMessage message = new ItemSelectMessage(1, 1, 1, -1);

    ShoppingCart shoppingCart = new ShoppingCart();
    when(shoppingCartRepository.findById(anyInt()))
        .thenReturn(Optional.of(shoppingCart))
        .thenReturn(Optional.of(shoppingCart));

    Product product = new Product();
    when(productRepository.findById(anyInt()))
        .thenReturn(Optional.of(product));

    CartItem cartItem = new CartItem(new CartItemId(), 0);
    when(cartItemRepository.findById(any(CartItemId.class)))
        .thenReturn(Optional.of(cartItem));

    assertThat(message.getCount()).isLessThanOrEqualTo(0);
    itemSelectMessageReceiveService.execute(message);

    verify(shoppingCartRepository, times(2)).findById(anyInt());
    verify(productRepository, times(1)).findById(anyInt());
    verify(cartItemRepository, times(1)).findById(any(CartItemId.class));
    verify(cartItemRepository, times(1)).delete(any(CartItem.class));

  }

  @Test
  public void execute_with_existing_cart_and_nonexisting_cartItem() {
    ItemSelectMessage message = new ItemSelectMessage(1, 1, 1, 1);

    ShoppingCart shoppingCart = new ShoppingCart();
    when(shoppingCartRepository.findById(anyInt()))
        .thenReturn(Optional.of(shoppingCart))
        .thenReturn(Optional.of(shoppingCart));

    Product product = new Product();
    when(productRepository.findById(anyInt()))
        .thenReturn(Optional.of(product));

    when(cartItemRepository.findById(any(CartItemId.class)))
        .thenReturn(Optional.empty());

    when(cartItemRepository.save(any(CartItem.class)))
        .thenReturn(new CartItem());

    assertThat(message.getCount()).isGreaterThan(0);
    itemSelectMessageReceiveService.execute(message);

    verify(shoppingCartRepository, times(2)).findById(anyInt());
    verify(productRepository, times(1)).findById(anyInt());
    verify(cartItemRepository, times(1)).findById(any(CartItemId.class));
    verify(cartItemRepository, times(1)).save(any(CartItem.class));
  }

  @Test
  public void execute_with_existing_cart_and_nonexisting_cartItem_invalid_itemcount() {
    ItemSelectMessage message = new ItemSelectMessage(1, 1, 1, -1);

    ShoppingCart shoppingCart = new ShoppingCart();
    when(shoppingCartRepository.findById(anyInt()))
        .thenReturn(Optional.of(shoppingCart))
        .thenReturn(Optional.of(shoppingCart));

    Product product = new Product();
    when(productRepository.findById(anyInt()))
        .thenReturn(Optional.of(product));

    when(cartItemRepository.findById(any(CartItemId.class)))
        .thenReturn(Optional.empty());

    assertThat(message.getCount()).isLessThanOrEqualTo(0);
    itemSelectMessageReceiveService.execute(message);

    verify(shoppingCartRepository, times(2)).findById(anyInt());
    verify(productRepository, times(1)).findById(anyInt());
    verify(cartItemRepository, times(1)).findById(any(CartItemId.class));
    verify(cartItemRepository, times(0)).save(any(CartItem.class));
  }

  @Test
  public void execute_with_nonexisting_cart_and_cartItem() {
    ItemSelectMessage message = new ItemSelectMessage(1, 1, 1, 1);

    ShoppingCart shoppingCart = new ShoppingCart();
    when(shoppingCartRepository.findById(anyInt()))
        .thenReturn(Optional.empty())
        .thenReturn(Optional.of(shoppingCart));

    when(customerRepository.findById(anyInt()))
        .thenReturn(Optional.of(new Customer()));

    when(shoppingCartRepository.save(any(ShoppingCart.class)))
        .thenReturn(shoppingCart);

    Product product = new Product();
    when(productRepository.findById(anyInt()))
        .thenReturn(Optional.of(product));

    when(cartItemRepository.findById(any(CartItemId.class)))
        .thenReturn(Optional.empty());

    when(cartItemRepository.save(any(CartItem.class)))
        .thenReturn(new CartItem());

    assertThat(message.getCount()).isGreaterThan(0);
    itemSelectMessageReceiveService.execute(message);

    verify(shoppingCartRepository, times(2)).findById(anyInt());
    verify(customerRepository, times(1)).findById(anyInt());
    verify(productRepository, times(1)).findById(anyInt());
    verify(cartItemRepository, times(1)).findById(any(CartItemId.class));
    verify(cartItemRepository, times(1)).save(any(CartItem.class));
  }
}