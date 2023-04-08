package com.github.rossilor95.todo_app.todo;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "todos")
public class Todo {

    @Id
    private ObjectId id;

    private String text;
    private Date dueDate;
    private boolean isCompleted;
    private boolean isImportant;

    private Todo() {
    }

    public Todo(ObjectId id, String text, Date dueDate, boolean isCompleted, boolean isImportant) {
        this.id = id;
        this.text = text;
        this.dueDate = dueDate;
        this.isCompleted = isCompleted;
        this.isImportant = isImportant;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public boolean isImportant() {
        return isImportant;
    }

    public void setImportant(boolean important) {
        isImportant = important;
    }
}
