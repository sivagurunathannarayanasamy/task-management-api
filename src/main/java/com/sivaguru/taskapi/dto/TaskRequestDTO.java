package com.sivaguru.taskapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TaskRequestDTO (

      @NotBlank(message = "Title must not be blank")
      @Size(max = 100, message = "Title must be at most 100 characters")
      String title,

      @Size(max = 500, message = "Description must be at most 500 characters")
      String description,

      @NotBlank(message = "Status must not be blank")
      String status
)
{}
