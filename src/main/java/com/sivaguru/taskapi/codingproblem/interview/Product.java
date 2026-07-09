package com.sivaguru.taskapi.codingproblem.interview;

public class Product {

  private String name;
  private double price;
  private String internalSupplierCode;

  public Product(String name, double price, String internalSupplierCode) {
    this.name = name;
    this.price = price;
    this.internalSupplierCode = internalSupplierCode;
  }

  public String getName() { return name; }
  public double getPrice() { return price; }
  public String getInternalSupplierCode() { return internalSupplierCode; }

}
