package com.idotrick.shoppingcartservice.model;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ItemSelectMessage implements Serializable {
  @GeneratedValue
  private int id;
  private ShoppingCart shoppingCart;
  private Product product;
  private int count;

  public ItemSelectMessage() {
  }

  public ItemSelectMessage(ShoppingCart shoppingCart, Product product, int count) {
    this.shoppingCart = shoppingCart;
    this.product = product;
    this.count = count;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public ShoppingCart getShoppingCart() {
    return shoppingCart;
  }

  public void setShoppingCart(ShoppingCart shoppingCart) {
    this.shoppingCart = shoppingCart;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ItemSelectMessage)) return false;
    ItemSelectMessage that = (ItemSelectMessage) o;
    return getId() == that.getId();
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId());
  }

  @Override
  public String toString() {
    return "ItemSelectMessage{" +
        "id=" + id +
        ", shoppingCart=" + shoppingCart +
        ", product=" + product +
        ", count=" + count +
        '}';
  }
}
