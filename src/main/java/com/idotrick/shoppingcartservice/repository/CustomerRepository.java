package com.idotrick.shoppingcartservice.repository;

import com.idotrick.shoppingcartservice.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
  Iterable<Customer> findAllByOrderByIdAsc();
}
