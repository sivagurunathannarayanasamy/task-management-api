package com.sivaguru.taskapi.codingproblem.interview;

import com.sivaguru.taskapi.mapper.TaskMapper;
import java.util.HashSet;
import java.util.List;

public class Day3 {

  record Task(String title, String status, int priority) {}

  public static void main(String[] args) {

    List<Product> products = List.of(
        new Product("Laptop", 1200, "SUP-001"),
        new Product("Mouse", 25.0, "SUP-002"),
        new Product("Keyboard", 75.0, "SUP-003")
    );

    ProductMapper mapper = new ProductMapper();
    List<ProductDTO> dtos = mapper.toDTOs(products);

    System.out.println(dtos);

    System.out.println(mapper.expensiveProductNames(products));

    HashSet<Integer> hashSet = new HashSet<>();
    hashSet.add(2);
    hashSet.add(7);
    hashSet.add(11);
    hashSet.add(15);

    System.out.println(ProductMapper.hasPairWithSum(new int[]{2, 7, 11, 15}, 9));

    List<String> statuses = List.of("PENDING", "DONE", "PENDING", "IN_PROGRESS", "PENDING");
    System.out.println(TaskMapper.countStatuses(statuses));
    System.out.println(TaskMapper.countStatusesStream(statuses));

    List<Task> tasks = List.of(
        new Task("Fix login bug", "PENDING", 3),
        new Task("Write README", "DONE", 1),
        new Task("Deploy to prod", "PENDING", 5),
        new Task("Update deps", "IN_PROGRESS", 2),
        new Task("Refactor mapper", "PENDING", 4)
    );

    System.out.println(ProductMapper.tasksByStatus(tasks));
  }

}
