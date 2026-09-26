package com.sivaguru.taskapi.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.sivaguru.taskapi.dto.TaskRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.json.JsonMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

//  @Autowired
//  private ObjectMapper objectMapper;

  @Autowired
  private JsonMapper objectMapper;

  @Test
  void getTasksByPage_whenTasksExist_returnsPagedResponse() throws Exception {
    TaskRequestDTO requestDto = new TaskRequestDTO("Integration Task", "Created via test", "TODO");

    mockMvc.perform(post("/api/tasks")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(requestDto)))
        .andExpect(status().isCreated());

    mockMvc.perform(get("/api/tasks?page=0&size=5&sort=title,asc"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.statusCode").value(200))
        .andExpect(jsonPath("$.data").isArray())
        .andExpect(jsonPath("$.pageInfo.totalElements").value(1));
  }

  @Test
  void createTask_whenTitleIsBlank_returnsValidationError() throws Exception {
    TaskRequestDTO invalidDto = new TaskRequestDTO("", "Some description", "TODO");

    mockMvc.perform(post("/api/tasks")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(invalidDto)))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.statusCode").value(400))
        .andExpect(jsonPath("$.message").value("Validation failed"))
        .andExpect(jsonPath("$.data.title").value("Title must not be blank"));
  }

}
