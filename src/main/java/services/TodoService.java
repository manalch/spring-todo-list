package services;

import exceptions.NotFoundTodoException;
import models.Todo;
import org.springframework.stereotype.Service;
import repositories.TodoRepository;

import java.util.List;

@Service
public class TodoService {

    private TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> findTodos() {
        return todoRepository.findAll();
    }

    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo updateTodo(int id, Todo updatedTodo) {
        Todo todo = findTodoById(id);
        todo.setDone(updatedTodo.isDone());
        return todoRepository.save(todo);
    }

    public Todo findTodoById(int id) {
        return todoRepository.findById(id)
                .orElseThrow(NotFoundTodoException::new);
    }

    public void deleteTodo(int id) {
        todoRepository.delete(findTodoById(id));
    }
}
