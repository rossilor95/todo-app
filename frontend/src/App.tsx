import { useEffect, useState } from "react";
import CategoryToolbar from "./components/CategoryToolbar";
import Header from "./components/Header";
import TodoList from "./components/TodoList";
import { Todo, TodoInput } from "./interfaces";
import axios from "./axios-config";

export default function App() {
  const [todos, setTodos] = useState<Todo[]>([]);

  useEffect(() => {
    axios
      .get("/todos")
      .then((response) => setTodos(response.data))
      .catch((error) => console.error("Error fetching Todos: ", error));
  }, []);

  const handleToggleTodo = (id: string) => {
    setTodos(
      todos.map((todo) => {
        if (todo.id === id) {
          return { ...todo, isCompleted: !todo.isCompleted };
        }
        return todo;
      })
    );
  };

  const handleAddTodo = (text: string) => {
    const newTodo: TodoInput = {
      text: text,
      dueDate: null,
      isCompleted: false,
      isImportant: false
    };
    axios
      .post("/todos", newTodo)
      .then((response) => setTodos((todos) => [response.data, ...todos]))
      .catch((error) => console.error("Error creating new Todo: ", error));
  };

  return (
    <div className="bg-gray-50 min-h-screen">
      <Header />
      <CategoryToolbar selectedCategory="My day" />
      <TodoList
        todos={todos}
        handleToggleTodo={handleToggleTodo}
        handleAddTodo={handleAddTodo}
      />
    </div>
  );
}
