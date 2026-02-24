package com.renuka.security_task_manager.dto;

import lombok.*;

@AllArgsConstructor
@Getter
public class TaskResponse {
    private Long id;
    private String title;
    private String description;
    private String status;
}