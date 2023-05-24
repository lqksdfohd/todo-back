package org.ahmed.todolist.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class TodoDTO {
    private Integer id;
    @NotBlank
    private String titre;
    private String description;
}
