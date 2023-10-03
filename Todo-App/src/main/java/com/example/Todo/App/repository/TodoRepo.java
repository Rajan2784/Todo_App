package com.example.Todo.App.repository;

import com.example.Todo.App.models.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepo extends JpaRepository<TodoItem, Long> {

}
