package com.IAbouzaid.Task.Management.Service.security;


import com.IAbouzaid.Task.Management.DTO.security.UserDto;
import com.IAbouzaid.Task.Management.controller.vm.UserResponseVm;

import java.util.List;

public interface UserService {
    List<UserResponseVm> getUsers();

    UserDto createUser(UserDto userDto);


    UserDto getUserById(Long id);

    UserDto getUserByEmail(String Email);

}