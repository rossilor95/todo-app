import { Box, Container } from "@mui/material";
import { Stack } from "@mui/system";
import { useState } from "react";
import CategoryToolbar from "./components/CategoryToolbar";
import Header from "./components/Header";
import TodoList from "./components/TodoList";
import { Todo } from "./interfaces";

export default function App() {
  const [todos, setTodos] = useState<Todo[]>([
    { id: 1, text: "Finalise presentation", isCompleted: true },
    { id: 2, text: "Book flight to London", isCompleted: false },
    { id: 3, text: "Get flowers for nana", isCompleted: false },
    { id: 4, text: "Get groceries for dinner", isCompleted: false },
  ]);

  const handleToggleTodo = (id: number) => {
    setTodos(
      todos.map((todo) => {
        if (todo.id === id) {
          return { ...todo, isCompleted: !todo.isCompleted };
        }
        return todo;
      })
    );
  };

  return (
    <>
        <Header />
    <Container>
        <CategoryToolbar selectedCategory="My day" />
        <TodoList todos={todos} handleToggleTodo={handleToggleTodo} />
    </Container>
    </>

  );
}
