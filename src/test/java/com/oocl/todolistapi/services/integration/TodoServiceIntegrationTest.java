package com.oocl.todolistapi.services.integration;

import com.oocl.todolistapi.models.Todo;
import com.oocl.todolistapi.repositories.TodoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TodoServiceIntegrationTest {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private MockMvc mockMvc;

    @AfterEach
    void tearDown() {
        todoRepository.deleteAll();
    }

    @Test
    void should_return_all_todos_when_get_all_todos() throws Exception {
        Todo todo = new Todo("Remember to do TDD first", false);
        todoRepository.save(todo);

        mockMvc.perform(get("/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].text").value("Remember to do TDD first"))
                .andExpect(jsonPath("$[0].done").value(false));
    }

    @Test
    void should_create_todo_when_creating_a_todo_given_a_new_object() throws Exception {
        String todoJson = "{\n" +
                "    \"text\":\"do tdd first\",\n" +
                "    \"done\":false\n" +
                "}";

        mockMvc.perform(post("/todos")
                .content(todoJson)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.text").value("do tdd first"))
                .andExpect(jsonPath("$.done").value(false));
    }
}
