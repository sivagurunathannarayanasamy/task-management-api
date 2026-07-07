package com.sivaguru.taskapi.mapper;

import com.sivaguru.taskapi.dto.TaskRequestDTO;
import com.sivaguru.taskapi.dto.TaskResponseDTO;
import com.sivaguru.taskapi.model.Task;
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

}
