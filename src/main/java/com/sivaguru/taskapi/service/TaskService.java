package com.sivaguru.taskapi.service;

import com.sivaguru.taskapi.exceptions.TaskNotFoundException;
import com.sivaguru.taskapi.model.Task;
import com.sivaguru.taskapi.repository.TaskRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

  private final TaskRepository taskRepository;

  public TaskService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  public List<Task> getAllTasks() {
    return taskRepository.findAll();
  }

  public Optional<Task> getTaskById(Long id) {
    return taskRepository.findById(id);
  }

  public Task createTask(Task task) {
    return taskRepository.save(task);
  }

  public long getTaskCount() {
    return taskRepository.count();
  }

  public Task updateTask(Long id, Task updatedTask) {
    Task existing = taskRepository.findById(id)
        .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));

    existing.setTitle(updatedTask.getTitle());
    existing.setDescription(updatedTask.getDescription());
    existing.setStatus(updatedTask.getStatus());

    return taskRepository.save(existing);
  }

  public void deleteTask(Long id) {
    if (!taskRepository.existsById(id)) {
      throw new TaskNotFoundException("Task not found with id: " + id);
    }

    taskRepository.deleteById(id);
  }

}


