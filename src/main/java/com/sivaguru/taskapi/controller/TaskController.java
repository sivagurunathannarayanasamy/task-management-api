package com.sivaguru.taskapi.controller;

import com.sivaguru.taskapi.model.Task;
import com.sivaguru.taskapi.repository.TaskRepository;
import com.sivaguru.taskapi.service.TaskService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

  private final TaskService taskService;

  public TaskController(TaskService taskService) {
    this.taskService = taskService;
  }


  @GetMapping
  public List<Task> getAllTasks() {
    return taskService.getAllTasks();
  }


  @GetMapping("/{id}")
  public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
    return taskService.getTaskById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Task> createTask(@RequestBody Task task) {
    Task saved = taskService.createTask(task);
    return ResponseEntity.status(HttpStatus.CREATED).body(saved);
  }

  @GetMapping("/count")
  public Long getTaskCount() {
    return taskService.getTaskCount();
  }
}
