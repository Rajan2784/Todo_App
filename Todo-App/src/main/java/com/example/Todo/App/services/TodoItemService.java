package com.example.Todo.App.services;

import com.example.Todo.App.models.TodoItem;
import com.example.Todo.App.repository.TodoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class TodoItemService {

    @Autowired
    private TodoRepo todoRepo;

    public Iterable<TodoItem> getAll(){
        return todoRepo.findAll();
    }

    public Optional<TodoItem> getById(Long id){
        return todoRepo.findById(id);
    }

    public TodoItem save(TodoItem todoItem){
        if (todoItem.getId()==null){
            todoItem.setCreatedAt(Instant.now());
        }

        todoItem.setUpdatedAt(Instant.now());
        return todoRepo.save(todoItem);
    }

    public void delete(TodoItem todoItem){
        todoRepo.delete(todoItem);
    }
}
