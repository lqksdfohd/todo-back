package org.ahmed.todolist.controlleurs;

import org.ahmed.todolist.dtos.TodoCeationDTO;
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

    @GetMapping(value = "/recuperer-toutes-les-todos")
    public List<TodoDTO> recupererToutesLesTodos(){
        List<Todo> liste = todoService.recupererListeDesTodos();
        List<TodoDTO> output = liste.stream().map(t -> mapstructService.todoVersTodoDTO(t))
                .sorted((t1,t2) -> -(t1.getId() - t2.getId()))
                .collect(Collectors.toList());
        return output;
    }

    @PostMapping("/creer-todo")
    public TodoDTO creerUneTodo(@Valid @RequestBody TodoCeationDTO dto){
        Todo enInput = mapstructService.todoCreationDTOVersTodo(dto);
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

    @GetMapping(value = "/recuperer-todo/{id}")
    public ResponseEntity<TodoDTO> recupererTodoById(@PathVariable("id") int id){
        Optional<Todo> optional = todoService.recupererUneTodo(id);
        if(optional.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{
            TodoDTO output = mapstructService.todoVersTodoDTO(optional.get());
            return new ResponseEntity<>(output,HttpStatus.OK);
        }
    }
}
