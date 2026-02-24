package com.renuka.security_task_manager.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
public class AuthRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}