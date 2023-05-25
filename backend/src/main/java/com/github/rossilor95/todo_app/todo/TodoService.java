package com.github.rossilor95.todo_app.todo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class TodoService {
    private final Logger logger = LoggerFactory.getLogger(TodoService.class);
    private final TodoRepository todoRepository;

    @Autowired
    TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> findAll() {
        List<Todo> todos = todoRepository.findAll();
        logger.info("Found {} todos", todos.size());
        return todos;
    }

    public Todo save(@RequestBody Todo todo) {
        Todo saved = todoRepository.save(todo);
        logger.info("Successfully created new Todo with ID {}", saved.id());
        return saved;
    }

    public Todo update(String id, Map<String, Object> updates) throws EmptyResultDataAccessException, IllegalArgumentException {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
        Todo updated = applyUpdate(todo, updates);
        todoRepository.save(updated);
        logger.info("Successfully updated Todo with ID {}", id);
        return updated;
    }

    public void deleteById(String id) throws EmptyResultDataAccessException {
        todoRepository.deleteById(id);
        logger.info("Successfully deleted Todo with ID {}", id);
    }

    /**
     * Applies updates to a Todo item.
     *
     * @param todo the Todo item to which to apply the updates.
     * @param updates the updates to be applied to the Todo item.
     * @return the updated Todo item.
     * @throws IllegalArgumentException if any of the updates are invalid.
     */
    private Todo applyUpdate(Todo todo, Map<String, Object> updates) {
        final String id = todo.id();
        String text = todo.text();
        Date dueDate = todo.dueDate();
        boolean isCompleted = todo.isCompleted();
        boolean isImportant = todo.isImportant();
        for (var entry : updates.entrySet()) {
            switch (entry.getKey()) {
                case "text" -> text = (String) entry.getValue();
                case "dueDate" -> dueDate = (Date) entry.getValue();
                case "isCompleted" -> isCompleted = (boolean) entry.getValue();
                case "isImportant" -> isImportant = (boolean) entry.getValue();
                default -> throw new IllegalArgumentException("Invalid update field: " + entry.getKey());
            }
        }
        return new Todo(id, text, dueDate, isCompleted, isImportant);
    }
}
