package com.idotrick.shoppingcartservice;

import com.idotrick.shoppingcartservice.demo.ShoppingItemSelectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShoppingCartServiceApplicationTest {

  @Autowired
  ShoppingCartServiceApplication shoppingCartServiceApplication;

  @MockBean
  ShoppingItemSelectService shoppingItemSelectService;

  @Test
  public void run_test_call() {
    verify(shoppingItemSelectService, times(1)).simulateBatchItemSelect();
  }
}