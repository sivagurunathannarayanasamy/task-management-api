package com.sivaguru.taskapi.service;

import com.sivaguru.taskapi.dto.PageMetadata;
import com.sivaguru.taskapi.dto.PagedResponse;
import com.sivaguru.taskapi.dto.TaskRequestDTO;
import com.sivaguru.taskapi.dto.TaskResponseDTO;
import com.sivaguru.taskapi.exceptions.TaskNotFoundException;
import com.sivaguru.taskapi.mapper.TaskMapper;
import com.sivaguru.taskapi.model.Task;
import com.sivaguru.taskapi.repository.TaskRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

  @Mock
  private TaskRepository taskRepository;

  @Mock
  private TaskMapper taskMapper;

  @InjectMocks
  private TaskService taskService;

  @Test
  void getTaskById_whenTaskExists_returnsTaskResponseDTO() {
    Long id = 1L;
    Task task = new Task( "Task A", "First task", "TODO");
    TaskResponseDTO expectedDto = new TaskResponseDTO(id, "Task A", "First task", "TODO");

    when(taskRepository.findById(id)).thenReturn(Optional.of(task));
    when(taskMapper.toResponseDTO(task)).thenReturn(expectedDto);

    TaskResponseDTO result = taskService.getTaskById(id);

    assertEquals(expectedDto, result);
  }

  @Test
  void getTaskById_whenTaskDoesNotExist_throwsTaskNotFoundException() {

    Long id = 99L;
    when(taskRepository.findById(id)).thenReturn(Optional.empty());

    assertThrows(TaskNotFoundException.class, () -> taskService.getTaskById(id));
  }

  @Test
  void createTask_whenTaskCreated_returnsTaskResponseDTO() {

    TaskRequestDTO dto = new TaskRequestDTO("Task A", "First task", "TODO");

    Task task = new Task("Task A", "First task", "TODO");
    Task saved = new Task("Task A", "First task", "TODO");
    saved.setId(1L);
    TaskResponseDTO expectedDto = new TaskResponseDTO(1L, "Task A", "First task", "TODO");

    when(taskMapper.toEntity(dto)).thenReturn(task);
    when(taskRepository.save(task)).thenReturn(saved);
    when(taskMapper.toResponseDTO(saved)).thenReturn(expectedDto);

    TaskResponseDTO result = taskService.createTask(dto);
    assertEquals(expectedDto, result);
  }

  @Test
  void getTasksByPage_whenPageExists_returnsTaskResponseDTO() {

    List<Task> taskList = List.of(
        new Task("Task A", "First task", "TODO"),
        new Task("Task B", "Second task", "IN_PROGRESS")
    );

    TaskResponseDTO dto1 = new TaskResponseDTO(1L, "Task A", "First task", "TODO");
    TaskResponseDTO dto2 = new TaskResponseDTO(2L, "Task B", "Second task", "IN_PROGRESS");

    when(taskMapper.toResponseDTO(taskList.get(0))).thenReturn(dto1);
    when(taskMapper.toResponseDTO(taskList.get(1))).thenReturn(dto2);



    Pageable pageable = PageRequest.of(0, 5);

    Page<Task> taskPage =  new PageImpl<>(taskList, pageable, 2);

    when(taskRepository.findAll(pageable)).thenReturn(taskPage);

    List<TaskResponseDTO> expectedDtos = List.of(dto1, dto2);

    PageMetadata expectedMetadata = new PageMetadata(taskPage.getNumber(), taskPage.getSize(), taskPage.getTotalElements(), taskPage.getTotalPages());
    PagedResponse<List<TaskResponseDTO>> expectedResponse = new PagedResponse<>(200, "Tasks in page retrieved successfully",  expectedDtos, expectedMetadata);

    PagedResponse<List<TaskResponseDTO>> result = taskService.getTasksByPage(pageable);
    assertEquals(200, result.getStatusCode());
    assertEquals("Tasks in page retrieved successfully", result.getMessage());
    assertEquals(expectedDtos, result.getData());
    assertEquals(expectedMetadata.getPageNumber(), result.getPageInfo().getPageNumber());
    assertEquals(expectedMetadata.getPageSize(), result.getPageInfo().getPageSize());
    assertEquals(expectedMetadata.getTotalElements(), result.getPageInfo().getTotalElements());
    assertEquals(expectedMetadata.getTotalPages(), result.getPageInfo().getTotalPages());
  }


}
