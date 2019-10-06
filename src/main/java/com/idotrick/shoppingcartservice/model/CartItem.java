package com.idotrick.shoppingcartservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class CartItem {
  @EmbeddedId
  CartItemId cartItemId;
  @Column(nullable = false)
  private Integer quantity;

  public CartItem() {
  }

  public CartItem(CartItemId cartItemId, Integer quantity) {
    this.cartItemId = cartItemId;
    this.quantity = quantity;
  }

  public CartItemId getCartItemId() {
    return cartItemId;
  }

  public void setCartItemId(CartItemId cartItemId) {
    this.cartItemId = cartItemId;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof CartItem)) return false;
    CartItem cartItem = (CartItem) o;
    return Objects.equals(getCartItemId(), cartItem.getCartItemId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getCartItemId());
  }

  @Override
  public String toString() {
    return "CartItem{" +
        "cartItemId=" + cartItemId +
        ", quantity=" + quantity +
        '}';
  }
}
