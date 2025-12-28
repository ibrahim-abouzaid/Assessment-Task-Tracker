package com.IAbouzaid.Task.Management.Service.security;

import com.IAbouzaid.Task.Management.DTO.security.UserDto;
import com.IAbouzaid.Task.Management.controller.vm.security.AuthRequestVm;
import com.IAbouzaid.Task.Management.controller.vm.security.AuthResponseVm;

;

public interface AuthService {
   AuthResponseVm signUp(UserDto userDto);

    AuthResponseVm login(AuthRequestVm authRequestVm);
}