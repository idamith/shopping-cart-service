package com.idotrick.shoppingcartservice.model;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CartItemId implements Serializable {
  @ManyToOne
  private ShoppingCart shoppingCart;
  @ManyToOne
  private Product product;

  public CartItemId() {
  }

  public CartItemId(ShoppingCart shoppingCart, Product product) {
    this.shoppingCart = shoppingCart;
    this.product = product;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof CartItemId)) return false;
    CartItemId that = (CartItemId) o;
    return Objects.equals(getShoppingCart(), that.getShoppingCart()) &&
        Objects.equals(getProduct(), that.getProduct());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getShoppingCart(), getProduct());
  }
}
