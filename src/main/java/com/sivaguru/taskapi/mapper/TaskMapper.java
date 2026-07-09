package com.sivaguru.taskapi.mapper;

import com.sivaguru.taskapi.codingproblem.interview.Product;
import com.sivaguru.taskapi.codingproblem.interview.ProductDTO;
import com.sivaguru.taskapi.dto.TaskRequestDTO;
import com.sivaguru.taskapi.dto.TaskResponseDTO;
import com.sivaguru.taskapi.model.Task;
import jakarta.persistence.criteria.CriteriaBuilder.In;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.hibernate.event.internal.DefaultPersistOnFlushEventListener;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

  public TaskResponseDTO toResponseDTO(Task task) {
    return new TaskResponseDTO(
        task.getId(),
        task.getTitle(),
        task.getDescription(),
        task.getStatus()
    );
  }

  public Task toEntity(TaskRequestDTO dto) {
    Task task = new Task();
    task.setTitle(dto.title());
    task.setDescription(dto.description());
    task.setStatus(dto.status());
    return task;
  }

  public static Map<String, Integer> countStatuses(List<String> statuses) {
    Map<String, Integer> counts = new HashMap<>();

    for (String status : statuses) {
      int current = counts.getOrDefault(status, 0);

      counts.put(status, current+1);


    }
    return counts;
  }

  public static Map<String, Long> countStatusesStream(List<String> statuses) {
    return statuses.stream()
        .collect(Collectors.groupingBy(
            s -> s,
            Collectors.counting()
        ));
  }







}
