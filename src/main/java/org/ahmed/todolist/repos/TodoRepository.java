package org.ahmed.todolist.repos;

import org.ahmed.todolist.entite.Todo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends CrudRepository<Todo,Integer> {
}
