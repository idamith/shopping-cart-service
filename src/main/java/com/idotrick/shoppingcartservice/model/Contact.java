package com.idotrick.shoppingcartservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Contact {
  @Id
  @GeneratedValue
  private int id;
  @OneToOne(mappedBy = "contact")
  private Customer customer;
  @OneToOne
  private Address shippingAddress;
  private int phoneNumber;
  private String email;

  public Contact(){}

  public Contact(Customer customer, Address shippingAddress, int phoneNumber, String email) {
    this.customer = customer;
    this.shippingAddress = shippingAddress;
    this.phoneNumber = phoneNumber;
    this.email = email;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public Address getShippingAddress() {
    return shippingAddress;
  }

  public void setShippingAddress(Address shippingAddress) {
    this.shippingAddress = shippingAddress;
  }

  public int getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(int phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString() {
    return "Contact{" +
        "id=" + id +
        //", shippingAddress=" + shippingAddress +
        ", phoneNumber=" + phoneNumber +
        ", email='" + email + '\'' +
        '}';
  }
}
