package com.sivaguru.taskapi.codingproblem.interview;

import com.sivaguru.taskapi.codingproblem.interview.Day3;
import com.sivaguru.taskapi.codingproblem.interview.Day3.Task;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

  public static Map<String, List<String>> tasksByStatus(List<Task> tasks) {
    return tasks.stream()
        .filter(task -> task.priority() >=2)
        .sorted(Comparator.comparing(Task::title))
        .collect(Collectors.groupingBy(
            Task::status,
            Collectors.mapping(Task::title, Collectors.toList())
        ));
  }




}
