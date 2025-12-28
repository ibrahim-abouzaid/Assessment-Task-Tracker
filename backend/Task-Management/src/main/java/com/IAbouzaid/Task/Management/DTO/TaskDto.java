package com.IAbouzaid.Task.Management.DTO;

import com.IAbouzaid.Task.Management.model.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private Long id;

    @NotBlank
    @NotNull
    private String title;

    private String description;

    private TaskStatus status;


}
