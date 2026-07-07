package com.sivaguru.taskapi.practice;

import com.sivaguru.taskapi.exceptions.TaskNotFoundException;
import com.sivaguru.taskapi.model.Task;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TaskStreamPractice {

  public List<String> getCompletedTaskTitles(List<Task> tasks) {
    return tasks.stream()
        .filter(task -> task.getStatus().equals("COMPLETED"))
        .map(task -> task.getTitle())
        .collect(Collectors.toList());
  }

  public Optional<Task> findFirstInProgress(List<Task> tasks) {
    return tasks.stream()
        .filter(task -> task.getStatus().equals("IN_PROGRESS"))
        .findFirst();
  }

//public List<Task> fetchTaskByIds(List<Long> taskIds) {
//    return taskIds.stream()
//        .map(id -> taskService.getTaskById(id))
//        .filter(Optional -> optional.isPresent())
//        .map(optional -> optional.get())
//        .collect(Collectors.toList());
//}

}
