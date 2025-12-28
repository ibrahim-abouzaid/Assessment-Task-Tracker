package com.IAbouzaid.Task.Management.Service.Impl;

import com.IAbouzaid.Task.Management.DTO.TaskDto;
import com.IAbouzaid.Task.Management.DTO.security.UserDto;
import com.IAbouzaid.Task.Management.Mapper.security.UserMapper;
import com.IAbouzaid.Task.Management.Repo.TaskRepo;
import com.IAbouzaid.Task.Management.Service.TaskService;
import com.IAbouzaid.Task.Management.model.Task;
import com.IAbouzaid.Task.Management.model.TaskStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepo  taskRepo;
    private final UserMapper userMapper;


    public TaskServiceImpl(TaskRepo taskRepo, UserMapper userMapper) {
        this.taskRepo = taskRepo;
        this.userMapper = userMapper;
    }

    @Override
    public Task createTask( TaskDto taskDto) {
        if(taskDto.getId()!=null){
            throw new RuntimeException("Id Must be Null");
        }

        //get user details throw JWT
        UserDto userDto= (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Task task = new Task();
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setStatus(taskDto.getStatus());
        task.setCreatedAt(LocalDateTime.now());
        task.setUser(userMapper.toEntity(userDto));
        return taskRepo.save(task);
    }

    @Override
    public List<Task> listUserTasks() {
        UserDto userDto= (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return taskRepo.findByUserId(userDto.getId());
    }

    @Override
    public Task updateTask( Long taskId, TaskDto taskDto) {
        UserDto userDto= (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Task task = taskRepo.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        if (!task.getUser().getId().equals(userDto.getId())) {
            throw new RuntimeException("Access denied");
        }
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setStatus(taskDto.getStatus());
        return taskRepo.save(task);
    }

    @Override
    public void deleteTask( Long taskId) {
        UserDto userDto= (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Task task = taskRepo.findById(taskId)
                .orElse( null);
        if(task!=null){
        if (!task.getUser().getId().equals(userDto.getId())) {
            throw new RuntimeException("Access denied");
        }
        }
        taskRepo.delete(task);

    }
}
