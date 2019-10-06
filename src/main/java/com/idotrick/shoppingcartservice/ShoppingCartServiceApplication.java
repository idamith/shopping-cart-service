package com.idotrick.shoppingcartservice;

import com.idotrick.shoppingcartservice.demo.ShoppingItemSelectService;
import com.idotrick.shoppingcartservice.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShoppingCartServiceApplication implements CommandLineRunner {

  @Autowired
  ShoppingItemSelectService shoppingItemSelectService;
  @Autowired
  ItemSelectMessageProcessService itemSelectMessageProcessService;

  public static void main(String[] args) {
    SpringApplication.run(ShoppingCartServiceApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    System.out.println(". . . . . . W E L C O M E T-O M-I~N-I M-A~R-T . . . . . . . .");

    System.out.println(shoppingItemSelectService.simulateBatchItemSelect());
    shoppingItemSelectService.simulateBatchItemSelect().stream()
        .forEach(message -> itemSelectMessageProcessService.execute(message));

    System.out.println(". . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . ");
  }
}
