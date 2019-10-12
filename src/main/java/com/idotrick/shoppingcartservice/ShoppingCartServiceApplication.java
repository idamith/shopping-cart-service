package com.idotrick.shoppingcartservice;

import com.idotrick.shoppingcartservice.demo.ShoppingItemSelectService;
import com.idotrick.shoppingcartservice.service.ItemSelectMessageReceiveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShoppingCartServiceApplication implements CommandLineRunner {
  private static Logger log = LoggerFactory.getLogger(ShoppingCartServiceApplication.class);

  @Autowired
  ShoppingItemSelectService shoppingItemSelectService;

  public static void main(String[] args) {
    SpringApplication.run(ShoppingCartServiceApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    log.info(". . . . . . W E L C O M E T-O M-I~N-I M-A~R-T . . . . . . . .");

    shoppingItemSelectService.simulateBatchItemSelect();

    log.info(". . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . ");
  }
}
