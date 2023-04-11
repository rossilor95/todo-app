package com.github.rossilor95.todo_app.todo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "todos")
public record Todo(@Id String id, String text, Date dueDate, boolean isCompleted, boolean isImportant) {
}
