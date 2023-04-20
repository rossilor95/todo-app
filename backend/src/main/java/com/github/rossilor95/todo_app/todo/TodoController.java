package com.github.rossilor95.todo_app.todo;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

/**
 * Controller for managing Todo items.
 * Exposes REST endpoints for CRUD operations on Todo items.
 */
@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {
    private final TodoRepository todoRepository;
    private final Logger logger = LoggerFactory.getLogger(TodoController.class);

    @Autowired
    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    /**
     * Retrieves all Todo items.
     *
     * @return a ResponseEntity containing a list of all Todo items and a success status.
     */
    @GetMapping
    public ResponseEntity<List<Todo>> findAll() {
        List<Todo> todos = todoRepository.findAll();
        logger.info("Found {} todos", todos.size());
        return ResponseEntity.ok(todos);
    }


    /**
     * Saves a new Todo item.
     *
     * @param todo the Todo item to be saved.
     * @return a ResponseEntity containing the saved Todo item and a success status.
     */
    @PostMapping
    public ResponseEntity<Todo> save(@RequestBody Todo todo) {
        try {
            Todo savedTodo = todoRepository.save(todo);
            URI uriOfTodo = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(savedTodo.id())
                    .toUri();
            logger.info("Successfully created new todo with ID {}", savedTodo.id());
            return ResponseEntity.created(uriOfTodo).body(savedTodo);
        } catch (Exception e) {
            logger.error("Error occurred while saving Todo: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Deletes a Todo item by its ID.
     *
     * @param id the ID of the Todo item to be deleted.
     * @return a ResponseEntity with a success status if the Todo item was deleted,
     * or a not found status if the Todo item could not be found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        try {
            todoRepository.deleteById(id);
            logger.info("Successfully deleted todo with ID {}", id);
            return ResponseEntity.ok().build();
        } catch (EmptyResultDataAccessException e) {
            logger.error("Error occurred while deleting Todo with ID {}: Todo not found", id);
            return ResponseEntity.notFound().build();
        }
    }
}
