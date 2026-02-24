package com.renuka.security_task_manager.controller;

import com.renuka.security_task_manager.dto.TaskRequest;
import com.renuka.security_task_manager.dto.TaskResponse;
import com.renuka.security_task_manager.model.Task;
import com.renuka.security_task_manager.service.TaskService;

import jakarta.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    // ✅ CREATE
    @PostMapping
    public TaskResponse createTask(@Valid @RequestBody TaskRequest request,
                                   Authentication auth) {

        Task task = new Task(null,
                request.getTitle(),
                request.getDescription(),
                request.getStatus(),
                null);

        Task saved = service.createTask(auth.getName(), task);

        return new TaskResponse(
                saved.getId(),
                saved.getTitle(),
                saved.getDescription(),
                saved.getStatus()
        );
    }

    // ✅ GET USER TASKS
    @GetMapping
    public List<TaskResponse> getTasks(Authentication auth) {

        return service.getTasks(auth.getName())
                .stream()
                .map(t -> new TaskResponse(
                        t.getId(),
                        t.getTitle(),
                        t.getDescription(),
                        t.getStatus()))
                .collect(Collectors.toList());
    }

    // ✅ UPDATE
    @PutMapping("/{id}")
    public TaskResponse updateTask(@PathVariable Long id,
                                   @Valid @RequestBody TaskRequest request,
                                   Authentication auth) {

        Task task = new Task(null,
                request.getTitle(),
                request.getDescription(),
                request.getStatus(),
                null);

        Task updated = service.updateTask(id, auth.getName(), task);

        return new TaskResponse(
                updated.getId(),
                updated.getTitle(),
                updated.getDescription(),
                updated.getStatus()
        );
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable Long id,
                             Authentication auth) {

        service.deleteTask(id, auth.getName());
        return "Task deleted successfully";
    }

    // ✅ ADMIN - GET ALL TASKS
    @GetMapping("/admin/all")
    public List<TaskResponse> getAllTasks() {

        return service.getAllTasks()
                .stream()
                .map(t -> new TaskResponse(
                        t.getId(),
                        t.getTitle(),
                        t.getDescription(),
                        t.getStatus()))
                .collect(Collectors.toList());
    }
}