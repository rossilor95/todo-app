package com.github.rossilor95.todo_app.todo;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

/**
 * Controller for managing Todo items.
 * Exposes REST endpoints for CRUD operations on Todo items.
 */
@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {
    private final TodoService todoService;
    private final Logger logger = LoggerFactory.getLogger(TodoController.class);

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    /**
     * Retrieves all Todo items.
     *
     * @return a ResponseEntity containing a list of all Todo items and a success status.
     */
    @GetMapping
    public ResponseEntity<List<Todo>> findAll() {
        List<Todo> todos = todoService.findAll();
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
        Todo saved = todoService.save(todo);
        URI uri = buildUri(saved);
        return ResponseEntity.created(uri).body(saved);
    }

    /**
     * Updates an existing Todo item.
     *
     * @param id      the ID of the Todo item to be updated.
     * @param updates the updates to be applied to the Todo item.
     * @return a ResponseEntity containing the updated Todo item and a success status, or a not found status if the Todo
     * item could not be found, or bad request status if the updates are invalid.
     */
    @PatchMapping("/{id}")
    public ResponseEntity<Todo> update(@PathVariable String id, @RequestBody Map<String, String> updates) {
        try {
            Todo updated = todoService.update(id, updates);
            URI uri = buildUri(updated);
            return ResponseEntity.created(uri).body(updated);
        } catch (EmptyResultDataAccessException e) {
            logger.error("Error occurred while updating Todo with ID {}: Todo not found", id);
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            logger.error("Error occurred while updating Todo with ID {}: {}", id, e.getMessage());
            return ResponseEntity.badRequest().build();
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
            todoService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (EmptyResultDataAccessException e) {
            logger.error("Error occurred while deleting Todo with ID {}: Todo not found", id);
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Builds a URI for a Todo item.
     *
     * @param todo the Todo item for which to build a URI.
     * @return the URI for the Todo item.
     */
    private URI buildUri(Todo todo) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(todo.id())
                .toUri();
    }
}
