package com.IAbouzaid.Task.Management.Service;

import com.IAbouzaid.Task.Management.DTO.TaskDto;
import com.IAbouzaid.Task.Management.model.Task;

import java.util.List;

public interface TaskService {
    Task createTask (TaskDto taskDto);
    List<Task> listUserTasks();
    Task updateTask( Long taskId, TaskDto taskDto);
    void deleteTask( Long taskId);
}
