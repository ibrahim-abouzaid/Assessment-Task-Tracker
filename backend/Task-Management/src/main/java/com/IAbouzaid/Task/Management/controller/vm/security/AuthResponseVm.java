package com.IAbouzaid.Task.Management.controller.vm.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AuthResponseVm {
    private Long id;
    private String email;
    private String token;



}