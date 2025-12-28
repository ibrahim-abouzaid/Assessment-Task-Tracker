package com.IAbouzaid.Task.Management.base.mapper;

import java.util.List;


public interface BaseMapper<T,DTO> {


    T toEntity(DTO dto);
    DTO toDto(T t);

}
