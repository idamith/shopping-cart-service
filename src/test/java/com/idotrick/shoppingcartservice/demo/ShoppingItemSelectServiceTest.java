package com.idotrick.shoppingcartservice.demo;

import com.idotrick.shoppingcartservice.message.ItemSelectMessage;
import com.idotrick.shoppingcartservice.model.Customer;
import com.idotrick.shoppingcartservice.model.Product;
import com.idotrick.shoppingcartservice.repository.CustomerRepository;
import com.idotrick.shoppingcartservice.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
    ShoppingItemSelectService.class,
    ProductRepository.class,
    CustomerRepository.class,
    JmsMessagingTemplate.class
})
public class ShoppingItemSelectServiceTest {

  @SpyBean
  ShoppingItemSelectService shoppingItemSelectService;
  @MockBean
  ProductRepository productRepository;
  @MockBean
  CustomerRepository customerRepository;
  @MockBean
  JmsMessagingTemplate jmsMessagingTemplate;

  @Test
  public void simulateBatchItemSelect_test_call() {

    when(productRepository.findAll())
        .thenReturn(IntStream.range(1, 11)
            .mapToObj(i -> {
              Product p = new Product();
              p.setId(i);
              return p;
            }).collect(Collectors.toList()));

    when(customerRepository.findAllByOrderByIdAsc())
        .thenReturn(IntStream.range(1, 11)
            .mapToObj(i -> {
              Customer c = new Customer();
              c.setId(i);
              return c;
            }).collect(Collectors.toList()));

    shoppingItemSelectService.simulateBatchItemSelect();
    verify(productRepository, times(1)).findAll();
    verify(customerRepository, times(1)).findAllByOrderByIdAsc();
    verify(shoppingItemSelectService, times(10)).publishSelectedCartItem(any(ItemSelectMessage.class));
  }

  @Test
  public void publishSelectedCartItem_test_call() {
    ItemSelectMessage message = new ItemSelectMessage(1, 1, 1, 1);
    shoppingItemSelectService.publishSelectedCartItem(message);
    verify(jmsMessagingTemplate, times(1)).convertAndSend(anyString(), any(ItemSelectMessage.class));
  }
}