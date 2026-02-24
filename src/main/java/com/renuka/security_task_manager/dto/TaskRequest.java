package com.renuka.security_task_manager.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
public class TaskRequest {

    @NotBlank
    private String title;

    private String description;
    private String status;
}