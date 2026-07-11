package com.sivaguru.taskapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TaskResponseDTO (
    Long id,
    String title,
    String description,
    String status
    )
{}
