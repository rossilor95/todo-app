package com.github.rossilor95.todo_app.category;

import com.github.rossilor95.todo_app.todo.Todo;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document("categories")
public class Category {

    @Id
    private String id;

    private String name;

    @DocumentReference
    private List<Todo> todoIds;

    public Category(String id, String name, List<Todo> todoIds) {
        this.id = id;
        this.name = name;
        this.todoIds = todoIds;
    }

    private Category() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Todo> getTodoIds() {
        return todoIds;
    }

    public void setTodoIds(List<Todo> todoIds) {
        this.todoIds = todoIds;
    }
}
