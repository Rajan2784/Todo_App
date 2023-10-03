package com.example.Todo.App.controlers;

import com.example.Todo.App.models.TodoItem;
import com.example.Todo.App.services.TodoItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class TodoFormController {

    @Autowired
    private TodoItemService todoItemService;

    @GetMapping("/create-todo")
    public String showCreateForm(TodoItem todoItem){
        return "new-todo-item";
    }

    @PostMapping("/todo")
    public String createTodoItem(@Valid TodoItem todoItem, BindingResult result, Model model){

        TodoItem item = new TodoItem();
        item.setDescription(todoItem.getDescription());
        item.setIsComplete(todoItem.getIsComplete());

        todoItemService.save(todoItem);

        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteTododItem(@PathVariable("id") Long id, Model model){
        TodoItem todoItem = todoItemService.getById(id)
                .orElseThrow(()-> new IllegalArgumentException("TodoItem id : " + id + " not found"));

        todoItemService.delete(todoItem);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id,Model model){
        TodoItem todoItem = todoItemService
                .getById(id)
                .orElseThrow(()-> new IllegalArgumentException("TodoItem id: " + id + " not found"));
        model.addAttribute("todo",todoItem);

        return "edit-todo-item";
    }

    @PostMapping("/todo/{id}")
    public String updateTodoItem(
            @PathVariable("id") Long id, @Valid TodoItem todoItem, BindingResult result
    ){
        TodoItem item = todoItemService
                .getById(id)
                .orElseThrow(()-> new IllegalArgumentException("TodoItem id: " + id + " not found"));

        item.setIsComplete(todoItem.getIsComplete());
        item.setDescription(todoItem.getDescription());

        todoItemService.save(item);

        return "redirect:/";
    }
}

