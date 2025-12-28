package com.IAbouzaid.Task.Management.Mapper.security;


import com.IAbouzaid.Task.Management.DTO.security.UserDto;
import com.IAbouzaid.Task.Management.base.mapper.BaseMapper;
import com.IAbouzaid.Task.Management.controller.vm.UserResponseVm;
import com.IAbouzaid.Task.Management.controller.vm.security.AuthRequestVm;
import com.IAbouzaid.Task.Management.model.security.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserDto> {



    UserDto toDto (User user);
    UserResponseVm toUserVm(User user);

    UserDto toUserDto(AuthRequestVm authRequestVm);

   //AuthResponseVm toUserResponseVm(UserDto userDto);
}
