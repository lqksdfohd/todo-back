package org.ahmed.todolist.controlleurs;

import org.ahmed.todolist.entite.Todo;
import org.ahmed.todolist.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class TodoControlleur {

    @Autowired
    private TodoService todoService;

    @GetMapping(value = "/")
    public List<Todo> recupererToutesLesTodos(){
        return todoService.recupererListeDesTodos();
    }
}
