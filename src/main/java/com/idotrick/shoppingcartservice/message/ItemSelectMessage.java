package com.idotrick.shoppingcartservice.message;

import javax.persistence.GeneratedValue;
import java.io.Serializable;
import java.util.Objects;

public class ItemSelectMessage implements Serializable {
  @GeneratedValue
  private int id;
  private int shoppingCartId;
  private int customerId;
  private int productId;
  private int count;

  public ItemSelectMessage() {
  }

  public ItemSelectMessage(int shoppingCartId, int customerId, int productId, int count) {
    this.shoppingCartId = shoppingCartId;
    this.customerId = customerId;
    this.productId = productId;
    this.count = count;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getShoppingCartId() {
    return shoppingCartId;
  }

  public void setShoppingCartId(int shoppingCartId) {
    this.shoppingCartId = shoppingCartId;
  }

  public int getCustomerId() {
    return customerId;
  }

  public void setCustomerId(int customerId) {
    this.customerId = customerId;
  }

  public int getProductId() {
    return productId;
  }

  public void setProductId(int productId) {
    this.productId = productId;
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
        ", shoppingCartId=" + shoppingCartId +
        ", customerId=" + customerId +
        ", productId=" + productId +
        ", count=" + count +
        '}';
  }
}
