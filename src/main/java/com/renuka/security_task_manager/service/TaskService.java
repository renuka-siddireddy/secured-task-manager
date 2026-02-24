package com.renuka.security_task_manager.service;

import com.renuka.security_task_manager.model.*;
import com.renuka.security_task_manager.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepo;
    private final UserRepository userRepo;

    public TaskService(TaskRepository taskRepo, UserRepository userRepo) {
        this.taskRepo = taskRepo;
        this.userRepo = userRepo;
    }

    public Task createTask(String username, Task task) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        task.setUser(user);
        return taskRepo.save(task);
    }

    public List<Task> getTasks(String username) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return taskRepo.findByUser(user);
    }

    // ✅ UPDATE TASK
    public Task updateTask(Long id, String username, Task updatedTask) {

        Task existing = taskRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        // ensure only owner updates
        if (!existing.getUser().getUsername().equals(username)) {
            throw new RuntimeException("You are not allowed to update this task");
        }

        existing.setTitle(updatedTask.getTitle());
        existing.setDescription(updatedTask.getDescription());
        existing.setStatus(updatedTask.getStatus());

        return taskRepo.save(existing);
    }

    // ✅ DELETE TASK
    public void deleteTask(Long id, String username) {

        Task existing = taskRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (!existing.getUser().getUsername().equals(username)) {
            throw new RuntimeException("You are not allowed to delete this task");
        }

        taskRepo.delete(existing);
    }

    // ✅ ADMIN - get all tasks
    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }
}