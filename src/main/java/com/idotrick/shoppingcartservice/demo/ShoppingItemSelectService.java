package com.idotrick.shoppingcartservice.demo;

import com.idotrick.shoppingcartservice.configuration.MessageConfig;
import com.idotrick.shoppingcartservice.message.ItemSelectMessage;
import com.idotrick.shoppingcartservice.model.Customer;
import com.idotrick.shoppingcartservice.model.Product;
import com.idotrick.shoppingcartservice.repository.CustomerRepository;
import com.idotrick.shoppingcartservice.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingItemSelectService {
  private static Logger log = LoggerFactory.getLogger(ShoppingItemSelectService.class);

  @Autowired
  ProductRepository productRepository;
  @Autowired
  CustomerRepository customerRepository;
  @Autowired
  JmsMessagingTemplate jmsMessagingTemplate;

  public void simulateBatchItemSelect() {
    Iterable<Product> products = productRepository.findAll();
    List<Product> productList = new ArrayList<>();
    products.forEach(product -> productList.add(product));

    Iterable<Customer> customers = customerRepository.findAllByOrderByIdAsc();
    List<Customer> customerList = new ArrayList<>();
    customers.forEach(customer -> {
      customerList.add(customer);
    });

    publishSelectedCartItem(new ItemSelectMessage(1, customerList.get(0).getId(), productList.get(0).getId(), 2));
    publishSelectedCartItem(new ItemSelectMessage(1, customerList.get(0).getId(), productList.get(0).getId(), -1));
    publishSelectedCartItem(new ItemSelectMessage(1, customerList.get(0).getId(), productList.get(1).getId(), 1));
    publishSelectedCartItem(new ItemSelectMessage(2, customerList.get(1).getId(), productList.get(2).getId(), 3));
    publishSelectedCartItem(new ItemSelectMessage(1, customerList.get(0).getId(), productList.get(2).getId(), 1));
    publishSelectedCartItem(new ItemSelectMessage(3, customerList.get(2).getId(), productList.get(0).getId(), 1));
    publishSelectedCartItem(new ItemSelectMessage(3, customerList.get(2).getId(), productList.get(1).getId(), 5));
    publishSelectedCartItem(new ItemSelectMessage(3, customerList.get(2).getId(), productList.get(2).getId(), -1));
    publishSelectedCartItem(new ItemSelectMessage(3, customerList.get(2).getId(), productList.get(3).getId(), 2));
    publishSelectedCartItem(new ItemSelectMessage(3, customerList.get(2).getId(), productList.get(3).getId(), -3));
  }

  public void publishSelectedCartItem(ItemSelectMessage message) {
    log.info("--SCS--[Sent]: " + message);
    jmsMessagingTemplate.convertAndSend(MessageConfig.CART_ITEM_SELECT_QUEUE, message);
  }
}
