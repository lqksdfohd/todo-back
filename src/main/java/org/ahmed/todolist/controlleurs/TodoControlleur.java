package org.ahmed.todolist.controlleurs;

import org.ahmed.todolist.dtos.TodoDTO;
import org.ahmed.todolist.entite.Accompli;
import org.ahmed.todolist.entite.Todo;
import org.ahmed.todolist.services.MapstructService;
import org.ahmed.todolist.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class TodoControlleur {

    @Autowired
    private TodoService todoService;

    @Autowired
    private MapstructService mapstructService;

    @GetMapping(value = "/")
    public List<TodoDTO> recupererToutesLesTodos(){
        List<Todo> liste = todoService.recupererListeDesTodos();
        List<TodoDTO> output = liste.stream().map(t -> mapstructService.todoVersTodoDTO(t))
                .collect(Collectors.toList());
        return output;
    }

    @PostMapping("/creer-todo")
    public TodoDTO creerUneTodo(@Valid @RequestBody TodoDTO dto){
        Todo enInput = mapstructService.todoDTOVersTodo(dto);
        enInput.setAccompli(Accompli.FALSE);
        Todo enOutput = todoService.sauvegarderUneTodo(enInput);
        TodoDTO output = mapstructService.todoVersTodoDTO(enOutput);
        return output;
    }

    @PostMapping("/completer-todo/{id}")
    public ResponseEntity<TodoDTO> completerUneTodo(@PathVariable("id") int id ) throws Exception{
        Optional<Todo> optional = todoService.recupererUneTodo(id);
        if(optional.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{
            Todo todo = optional.get();
            todo.setAccompli(Accompli.TRUE);
            TodoDTO enOutput = mapstructService.todoVersTodoDTO(todoService.sauvegarderUneTodo(todo));
            return new ResponseEntity<>(enOutput, HttpStatus.OK);
        }
    }
}
