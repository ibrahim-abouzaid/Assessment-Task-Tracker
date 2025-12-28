package com.IAbouzaid.Task.Management.Service.Impl.security;

import com.IAbouzaid.Task.Management.DTO.security.UserDto;
import com.IAbouzaid.Task.Management.Service.security.AuthService;
import com.IAbouzaid.Task.Management.Service.security.UserService;
import com.IAbouzaid.Task.Management.Service.token.TokenHandler;
import com.IAbouzaid.Task.Management.controller.vm.security.AuthRequestVm;
import com.IAbouzaid.Task.Management.controller.vm.security.AuthResponseVm;

import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AuthServiceImpl implements AuthService {

    private UserService userService;

    private TokenHandler tokenHandler;

    private PasswordEncoder passwordEncoder;



    @Autowired
    public AuthServiceImpl(UserService accountService, TokenHandler tokenHandler, PasswordEncoder passwordEncoder) {
        this.userService = accountService;
        this.tokenHandler = tokenHandler;
        this.passwordEncoder = passwordEncoder;

    }

    //TODO add transactional annotation
    @Override
    public AuthResponseVm signUp(UserDto userDto) {

        UserDto user = userService.createUser(userDto);
        if(Objects.isNull(user)){
            throw new RuntimeException("create failed");
        }

        return new AuthResponseVm(user.getId(),user.getEmail(),tokenHandler.createToken(user));
    }

    @Override
    public AuthResponseVm login(AuthRequestVm accountAuthRequestVm) {
        try {
            UserDto userDto = userService.getUserByEmail(accountAuthRequestVm.getEmail());
            if (Objects.isNull(userDto)) {
                throw new SystemException("invalid Email/Password");
            }
            if (!passwordEncoder.matches(accountAuthRequestVm.getPassword(), userDto.getPassword())) {
                throw new SystemException("invalid Email/Password");
            }

            return new AuthResponseVm(userDto.getId(),userDto.getEmail(),tokenHandler.createToken(userDto));
        } catch (SystemException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


}