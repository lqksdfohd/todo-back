package org.ahmed.todolist.service;

import org.ahmed.todolist.TodolistApplication;
import org.ahmed.todolist.entite.Todo;
import org.ahmed.todolist.repos.TodoRepository;
import org.ahmed.todolist.services.TodoService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TodolistApplication.class})
public class TodoServiceTest {

    @Autowired
    private TodoService todoService;

    @MockBean
    private TodoRepository todoRepository;

    @Test
    public void testRecupererListeDesTodos_DbVide(){
        Mockito.when(todoRepository.findAll()).thenReturn(new ArrayList<>());

        Iterable<Todo> resultat = todoService.recupererListeDesTodos();

        Assertions.assertThat(resultat).isEmpty();
    }

    @Test
    public void testRecupererListeDesTodos_DbContientTodos(){
        Todo t = new Todo();
        t.setId(1);
        t.setTitre("titre");
        t.setDescription("description");

        Mockito.when(todoRepository.findAll()).thenReturn(Arrays.asList(t));

        Assertions.assertThat(todoService.recupererListeDesTodos()).isNotEmpty();
    }
}
