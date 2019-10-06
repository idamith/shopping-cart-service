package com.idotrick.shoppingcartservice.repository;

import com.idotrick.shoppingcartservice.model.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Integer> {
}
