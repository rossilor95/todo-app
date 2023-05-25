package com.github.rossilor95.todo_app.todo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.Nullable;

import java.time.LocalDate;

/**
 * <p>A class representing a Todo item.</p>
 * <p>This class is annotated with Spring Data's {@code @Document} annotation to indicate
 * * that it should be persisted in a document-based NoSQL database, such as
 * MongoDB.</p>
 *
 * @param id          The ID of the Todo item.
 * @param text        The text of the Todo item.
 * @param dueDate     The due date of the Todo item, or null if there is no due date.
 * @param isCompleted Whether the Todo item has been completed.
 * @param isImportant Whether the Todo item is marked as important.
 * @implNote This class is implemented as a record to provide a concise and immutable representation of a Todo item.
 * It also includes built-in implementations of {@code equals}, {@code hashCode}, and {@code toString}.
 */
@Document(collection = "todos")
public record Todo(@Id String id, String text, @Nullable LocalDate dueDate, boolean isCompleted, boolean isImportant) {
}
