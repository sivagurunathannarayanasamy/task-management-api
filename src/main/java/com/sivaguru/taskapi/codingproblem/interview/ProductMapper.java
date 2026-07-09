package com.sivaguru.taskapi.codingproblem.interview;

import java.util.HashSet;
import java.util.List;

public class ProductMapper {

  public ProductDTO toDTO(Product product) {
    return new ProductDTO(
        product.getName(),
        product.getPrice()
    );
  }

  public List<ProductDTO> toDTOs(List<Product> products) {
    return products.stream()
        .map(this::toDTO)
        .toList();
  }

  public List<String> expensiveProductNames(List<Product> products) {
    return products.stream()
        .filter(product -> product.getPrice() > 50.0)
        .map(Product::getName)
        .sorted()
        .toList();
  }

  public static boolean hasPairWithSum(int[] nums, int target) {
    HashSet<Integer> seen = new HashSet<>();

    for (int num : nums) {
      int partner = target - num;

      if (seen.contains(partner)) {
        return true;
      }
      seen.add(num);
    }
    return false;
  }




}
