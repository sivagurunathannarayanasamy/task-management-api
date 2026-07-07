package com.sivaguru.taskapi.dto;

public record TaskResponseDTO (
    Long id,
    String title,
    String description,
    String status
    )
{}
