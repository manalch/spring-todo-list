package services;

import jdk.nashorn.internal.objects.AccessorPropertyDescriptor;
import models.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.TodoRepository;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TodoServiceTest {

    private TodoRepository todoRepository;
    private TodoService todoService;

    @BeforeEach
    void setUp() {
        todoRepository = mock(TodoRepository.class);
        todoService = new TodoService(todoRepository);
    }

    @Test
    void should_return_all_todos_when_find_all_todos() {
        //given

        //when
        when(todoRepository.findAll()).thenReturn(asList(new Todo(), new Todo()));
        int actual = todoService.findTodos().size();

        //then
        assertEquals(2, actual);
    }

    @Test
    void should_return_created_todo_when_creating_given_a_todo() {
        //given
        Todo todo = new Todo("Remember to do TDD first", false);

        //when
        when(todoRepository.save(todo)).thenReturn(todo);
        Todo actual = todoService.createTodo(todo);

        //then
        assertSame(todo, actual);
    }

    @Test
    void should_get_todo_when_searched_by_id() {
        //given
        Todo todo = new Todo();

        //when
        when(todoRepository.findById(anyInt())).thenReturn(java.util.Optional.of(todo));
        Todo actual = todoService.findTodoById(anyInt());

        //then
        assertSame(todo, actual);
    }

    @Test
    void should_update_todo_when_update_request_is_given() {
        //given
        Todo todo = new Todo("Remember to do TDD first", false);
        Todo doneTodo = new Todo("Remember to do TDD first", true);

        //when
        when(todoRepository.save(todo)).thenReturn(todo);
        todoService.createTodo(todo);
        when(todoRepository.findById(anyInt())).thenReturn(java.util.Optional.of(todo));
        todoService.findTodoById(anyInt());
        when(todoRepository.save(doneTodo)).thenReturn(doneTodo);

        //then
        Todo actual = todoService.updateTodo(anyInt(), doneTodo);
        assertSame(todo, actual);
        assertTrue(actual.isDone());
    }
}