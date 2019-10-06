package com.idotrick.shoppingcartservice.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Customer {
  @Id
  @GeneratedValue
  private Integer id;
  private String name;
  private Sex sex;
  @OneToOne
  private Contact contact;
  @OneToMany(mappedBy = "customer")
  private List<ShoppingCart> shoppingCartList = new ArrayList<>();

  public enum Sex {
    MALE, FEMALE;
  }

  public Customer(){}

  public Customer(String name, Sex sex, Contact contact) {
    this.name = name;
    this.sex = sex;
    this.contact = contact;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Sex getSex() {
    return sex;
  }

  public void setSex(Sex sex) {
    this.sex = sex;
  }

  public Contact getContact() {
    return contact;
  }

  public void setContact(Contact contact) {
    this.contact = contact;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Customer)) return false;
    Customer customer = (Customer) o;
    return Objects.equals(getId(), customer.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId());
  }

  @Override
  public String toString() {
    return "Customer{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", sex=" + sex +
        ", contact=" + contact +
        '}';
  }
}
