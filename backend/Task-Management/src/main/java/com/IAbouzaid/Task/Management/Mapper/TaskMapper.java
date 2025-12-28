package com.IAbouzaid.Task.Management.Mapper;

import com.IAbouzaid.Task.Management.DTO.TaskDto;
import com.IAbouzaid.Task.Management.base.mapper.BaseMapper;
import com.IAbouzaid.Task.Management.model.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring") // "spring" so it works with @Autowired

public interface TaskMapper extends BaseMapper<Task, TaskDto> {

}
