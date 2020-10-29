package services;

import jdk.nashorn.internal.objects.AccessorPropertyDescriptor;
import models.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.TodoRepository;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;
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
}