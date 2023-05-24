package org.ahmed.todolist.controlleurs;

import org.ahmed.todolist.dtos.TodoDTO;
import org.ahmed.todolist.entite.Todo;
import org.ahmed.todolist.services.MapstructService;
import org.ahmed.todolist.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
public class TodoControlleur {

    @Autowired
    private TodoService todoService;

    @Autowired
    private MapstructService mapstructService;

    @GetMapping(value = "/")
    public List<Todo> recupererToutesLesTodos(){
        return todoService.recupererListeDesTodos();
    }

    @PostMapping("/creer-todo")
    public TodoDTO creerUneTodo(@Valid @RequestBody TodoDTO dto){
        Todo enInput = mapstructService.todoDTOVersTodo(dto);
        Todo enOutput = todoService.sauvegarderUneTodo(enInput);
        TodoDTO output = mapstructService.todoVersTodoDTO(enOutput);
        return output;
    }
}
