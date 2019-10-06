package com.idotrick.shoppingcartservice.demo;

import com.idotrick.shoppingcartservice.model.Customer;
import com.idotrick.shoppingcartservice.model.ItemSelectMessage;
import com.idotrick.shoppingcartservice.model.Product;
import com.idotrick.shoppingcartservice.model.ShoppingCart;
import com.idotrick.shoppingcartservice.repository.CustomerRepository;
import com.idotrick.shoppingcartservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ShoppingItemSelectService {
  @Autowired
  ProductRepository productRepository;
  @Autowired
  CustomerRepository customerRepository;

  public List<ItemSelectMessage> simulateBatchItemSelect() {
    Iterable<Product> products = productRepository.findAll();
    List<Product> productList = new ArrayList<>();
    products.forEach(product -> productList.add(product));

    Iterable<Customer> customers = customerRepository.findAllByOrderByIdAsc();
    List<ShoppingCart> shoppingCartList = new ArrayList<>();
    customers.forEach(customer -> {
      shoppingCartList.add(new ShoppingCart(customer));
    });

    return Arrays.asList(
        new ItemSelectMessage(shoppingCartList.get(0), productList.get(0), 2),
        new ItemSelectMessage(shoppingCartList.get(0), productList.get(0), -1),
        new ItemSelectMessage(shoppingCartList.get(0), productList.get(1), 1),
        new ItemSelectMessage(shoppingCartList.get(1), productList.get(2), 3),
        new ItemSelectMessage(shoppingCartList.get(0), productList.get(2), 1),
        new ItemSelectMessage(shoppingCartList.get(2), productList.get(0), 1),
        new ItemSelectMessage(shoppingCartList.get(2), productList.get(1), 5),
        new ItemSelectMessage(shoppingCartList.get(2), productList.get(2), -1),
        new ItemSelectMessage(shoppingCartList.get(2), productList.get(3), 2),
        new ItemSelectMessage(shoppingCartList.get(2), productList.get(3), -3)
    );
  }
}
