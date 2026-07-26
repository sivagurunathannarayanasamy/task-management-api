package com.sivaguru.taskapi.controller;

import com.sivaguru.taskapi.dto.TaskRequestDTO;
import com.sivaguru.taskapi.dto.TaskResponseDTO;
import com.sivaguru.taskapi.generics.ApiResponse;
import com.sivaguru.taskapi.model.Task;
import com.sivaguru.taskapi.repository.TaskRepository;
import com.sivaguru.taskapi.service.TaskService;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
  public ApiResponse<List<TaskResponseDTO>> getAllTasks() {

    List<TaskResponseDTO> tasks = taskService.getAllTasks();
    return new ApiResponse<>(200, "Tasks fetched successfully", tasks);
  }

  @GetMapping("/status/{status}")
  public ApiResponse<List<TaskResponseDTO>> getTasksByStatus(@PathVariable("status") String status) {

    List<TaskResponseDTO> tasks = taskService.getTasksByStatus(status);

    return new ApiResponse<>(200, "Tasks fetched successfully", tasks);
  }



  @GetMapping("/{id}")
  public ApiResponse<TaskResponseDTO> getTaskById(@PathVariable Long id) {

    TaskResponseDTO task = taskService.getTaskById(id);
    return new ApiResponse<>(200, "Task fetched successfully", task);
  }

  @PostMapping
  public ApiResponse<TaskResponseDTO> createTask(@Valid @RequestBody TaskRequestDTO dto) {
    TaskResponseDTO task = taskService.createTask(dto);
    return new ApiResponse<>(201, "Task created Successfully", task);
  }

  @GetMapping("/count")
  public ApiResponse<Long> getTaskCount() {
    Long count = taskService.getTaskCount();
    return new ApiResponse<>(200, "Task count fetched successfully", count);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse<TaskResponseDTO>> updateTask(@PathVariable Long id, @Valid @RequestBody TaskRequestDTO dto) {
    TaskResponseDTO updated = taskService.updateTask(id, dto);
    return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(200, "Task updated successfully", updated));

  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse<Void>> deleteTask(@PathVariable Long id) {
    taskService.deleteTask(id);
    return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(200, "Task deleted successfully", null));
  }

}
