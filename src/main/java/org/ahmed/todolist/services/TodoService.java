package org.ahmed.todolist.services;

import org.ahmed.todolist.entite.Todo;
import org.ahmed.todolist.repos.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> recupererListeDesTodos(){
        List<Todo> output = new ArrayList<>();
        todoRepository.findAll().forEach(output::add);
        return output;
    }

    public Todo sauvegarderUneTodo(Todo todo){
        return todoRepository.save(todo);
    }

    public Optional<Todo> recupererUneTodo(int id){
        Optional<Todo> optional = todoRepository.findById(id);
        return optional;
    }
}
