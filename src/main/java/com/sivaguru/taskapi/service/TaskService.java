package com.sivaguru.taskapi.service;

import com.sivaguru.taskapi.dto.TaskRequestDTO;
import com.sivaguru.taskapi.dto.TaskResponseDTO;
import com.sivaguru.taskapi.exceptions.TaskNotFoundException;
import com.sivaguru.taskapi.mapper.TaskMapper;
import com.sivaguru.taskapi.model.Task;
import com.sivaguru.taskapi.repository.TaskRepository;
import com.sivaguru.taskapi.utils.RepositoryUtils;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

  private final TaskRepository taskRepository;
  private final TaskMapper taskMapper;

  public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
    this.taskRepository = taskRepository;
    this.taskMapper = taskMapper;
  }

  public List<TaskResponseDTO> getAllTasks() {
    return taskRepository.findAll()
        .stream()
        .map(taskMapper::toResponseDTO)
        .toList();
  }


 public TaskResponseDTO getTaskById(Long id) {
   Task task = RepositoryUtils.findByIdOrThrow(taskRepository, id, "Task");
    return taskMapper.toResponseDTO(task);
 }

 public TaskResponseDTO createTask(TaskRequestDTO dto) {
    Task task = taskMapper.toEntity(dto);
    Task saved = taskRepository.save(task);
    return taskMapper.toResponseDTO(saved);
 }



  public long getTaskCount() {
    return taskRepository.count();
  }

//  public Task updateTask(Long id, Task updatedTask) {
//    Task existing = taskRepository.findById(id)
//        .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));
//
//    existing.setTitle(updatedTask.getTitle());
//    existing.setDescription(updatedTask.getDescription());
//    existing.setStatus(updatedTask.getStatus());
//
//    return taskRepository.save(existing);
//  }

  public TaskResponseDTO updateTask(Long id, TaskRequestDTO dto) {
    Task existing = taskRepository.findById(id)
        .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));

    existing.setTitle(dto.title());
    existing.setDescription(dto.description());
    existing.setStatus(dto.status());

    Task saved = taskRepository.save(existing);
    return taskMapper.toResponseDTO(saved);

  }

  public void deleteTask(Long id) {
    if (!taskRepository.existsById(id)) {
      throw new TaskNotFoundException("Task not found with id: " + id);


    }

    taskRepository.deleteById(id);
  }

}


