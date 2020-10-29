package com.oocl.todolistapi.controllers;

import com.oocl.todolistapi.models.Todo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.oocl.todolistapi.services.TodoService;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoListController {

    private final TodoService todoService;

    public TodoListController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<Todo> getTodos() {
        return todoService.findTodos();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Todo createTodo(@RequestBody Todo todo) {
        return todoService.createTodo(todo);
    }

    @GetMapping("/{id}")
    public Todo getTodoById(@PathVariable int id) {
        return todoService.findTodoById(id);
    }

    @PutMapping("/{employeeId}")
    public Todo updateTodo(@PathVariable int id, @RequestBody Todo updatedTodo) {
        return todoService.updateTodo(id, updatedTodo);
    }

    @DeleteMapping("/{employeeId}")
    public void deleteTodo(@PathVariable int id) {
        todoService.deleteTodo(id);
    }
}
