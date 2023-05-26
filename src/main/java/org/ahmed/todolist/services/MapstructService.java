package org.ahmed.todolist.services;

import org.ahmed.todolist.dtos.TodoCeationDTO;
import org.ahmed.todolist.dtos.TodoDTO;
import org.ahmed.todolist.entite.Todo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Service;

@Service
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MapstructService {

    Todo todoCreationDTOVersTodo(TodoCeationDTO dto);
    TodoDTO todoVersTodoDTO(Todo todo);
}
