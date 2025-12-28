package com.IAbouzaid.Task.Management.Service.Impl.security;

import com.IAbouzaid.Task.Management.DTO.security.UserDto;
import com.IAbouzaid.Task.Management.Mapper.security.UserMapper;
import com.IAbouzaid.Task.Management.Repo.security.UserRepo;
import com.IAbouzaid.Task.Management.Service.security.UserService;
import com.IAbouzaid.Task.Management.controller.vm.UserResponseVm;
import com.IAbouzaid.Task.Management.model.security.User;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserRepo userRepo;
    private PasswordEncoder passwordEncoder;

    private UserMapper userMapper;

@Autowired
    public UserServiceImpl(UserRepo userRepo, @Lazy PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;

    }

    @Override
    public List<UserResponseVm> getUsers() {
        try {
            List<User> users = userRepo.findAll();
            if (users.isEmpty()) {
                throw new SystemException("empty.users");
            }
            return users.stream().map(userMapper::toUserVm).collect(Collectors.toList());
        } catch (SystemException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        if(Objects.nonNull(userDto.getId())){
            throw new RuntimeException("id.must_be.null");
        }
        User user =userRepo.findByEmail(userDto.getEmail());
        if(Objects.nonNull(user)){
            throw new RuntimeException("user found");
        }

            user = userMapper.toEntity(userDto);
            //encode password
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user = userRepo.save(user);
            return userMapper.toDto(user);


    }



    @Override
    public UserDto getUserById(Long id) {
        try {
            if (Objects.isNull(id)) {
                throw new SystemException("id.must_be.not_null");
            }
            Optional<User> result = userRepo.findById(id);
            if (result.isEmpty()) {
                throw new SystemException("not_found.user");
            }
            return userMapper.toDto(result.get());
        } catch (SystemException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override

    public UserDto getUserByEmail(String email) {
        try {
            if (email.isEmpty()) {
                throw new SystemException("not_empty.name");
            }
            User result = userRepo.findByEmail(email);
            if (result == null) {
                return null;
            }
            return userMapper.toDto(result);
        } catch (SystemException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
