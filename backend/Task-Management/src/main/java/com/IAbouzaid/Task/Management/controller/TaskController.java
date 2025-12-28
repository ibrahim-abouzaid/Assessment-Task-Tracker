package com.IAbouzaid.Task.Management.controller;

import com.IAbouzaid.Task.Management.DTO.TaskDto;
import com.IAbouzaid.Task.Management.DTO.security.UserDto;
import com.IAbouzaid.Task.Management.Mapper.TaskMapper;
import com.IAbouzaid.Task.Management.Service.Impl.security.UserServiceImpl;
import com.IAbouzaid.Task.Management.Service.TaskService;
import com.IAbouzaid.Task.Management.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;
    private final TaskMapper taskMapper;


    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    // Create task
    @PostMapping
    public TaskDto createTask(@RequestBody TaskDto taskDto) {
        return  taskMapper.toDto(taskService.createTask(taskDto));

    }

    // List user tasks
    @GetMapping
    public List<TaskDto> listUserTasks() {

        return taskService.listUserTasks().stream().map(taskMapper::toDto).toList();

    }

    // Update task
    @PutMapping("/{id}")
    public TaskDto updateTask(@PathVariable("id") Long id, @RequestBody TaskDto taskDto) {
        return  taskMapper.toDto(taskService.updateTask( id, taskDto));

    }

    // Delete task
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
         taskService.deleteTask(id);
    }
}
