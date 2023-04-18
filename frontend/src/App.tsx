import { useEffect, useState } from "react";
import CategoryToolbar from "./components/CategoryToolbar";
import Header from "./components/Header";
import TodoList from "./components/TodoList";
import { Todo } from "./interfaces";
import axios from "./axios-config";

export default function App() {
  const [todos, setTodos] = useState<Todo[]>([]);

  useEffect(() => {
    axios
      .get("/todos")
      .then((response) => setTodos(response.data))
      .catch((error) => console.error("Error fetching data: ", error));
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
    const newTodo: Todo = {
      id: (todos.length + 1).toString(),
      text: text,
      isCompleted: false,
    };
    setTodos((todos) => [newTodo, ...todos]);
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
