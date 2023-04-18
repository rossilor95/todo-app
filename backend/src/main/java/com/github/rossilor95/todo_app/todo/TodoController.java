package com.github.rossilor95.todo_app.todo;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {
    private final TodoRepository todoRepository;
    private final Logger logger = LoggerFactory.getLogger(TodoController.class);

    @Autowired
    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping
    public ResponseEntity<List<Todo>> findAll() {
        try {
            List<Todo> todos = todoRepository.findAll();
            logger.info("Found {} todos", todos.size());
            return ResponseEntity.ok(todos);
        } catch (Exception e) {
            logger.error("Error occurred while finding todos: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<Todo> save(@RequestBody Todo todo) {
        try {
            Todo savedTodo = todoRepository.save(todo);
            URI uriOfTodo = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(savedTodo.id())
                    .toUri();
            logger.info("Successfully created new todo with ID {}", savedTodo.id());
            return ResponseEntity.created(uriOfTodo).build();
        } catch (Exception e) {
            logger.error("Error occurred while saving Todo: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        try {
            todoRepository.deleteById(id);
            logger.info("Successfully deleted todo with ID {}", id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error("Error occurred while deleting Todo: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
