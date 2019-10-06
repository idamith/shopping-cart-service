package com.idotrick.shoppingcartservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Address {
  @Id
  @GeneratedValue
  private int id;
  private String line1;
  private String line2;
  private String line3;
  private String country;
  private String province;
  private String city;
  private String postalCode;
  @OneToOne(mappedBy = "shippingAddress")
  private Contact contact;

  public Address(){}

  public Address(String line1, String line2, String line3, String country, String province, String city, String postalCode) {
    this.line1 = line1;
    this.line2 = line2;
    this.line3 = line3;
    //this.country = country;
    this.province = province;
    this.city = city;
    this.postalCode = postalCode;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getLine1() {
    return line1;
  }

  public void setLine1(String line1) {
    this.line1 = line1;
  }

  public String getLine2() {
    return line2;
  }

  public void setLine2(String line2) {
    this.line2 = line2;
  }

  public String getLine3() {
    return line3;
  }

  public void setLine3(String line3) {
    this.line3 = line3;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public Contact getContact() {
    return contact;
  }

  public void setContact(Contact contact) {
    this.contact = contact;
  }

  @Override
  public String toString() {
    return "Address{" +
        "id=" + id +
        ", line1='" + line1 + '\'' +
        ", line2='" + line2 + '\'' +
        ", line3='" + line3 + '\'' +
        ", country='" + country + '\'' +
        ", province='" + province + '\'' +
        ", city='" + city + '\'' +
        ", postalCode='" + postalCode + '\'' +
        ", contact=" + contact +
        '}';
  }
}
