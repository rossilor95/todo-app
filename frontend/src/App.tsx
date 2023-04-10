import { useEffect, useState } from "react";
import CategoryToolbar from "./components/CategoryToolbar";
import Header from "./components/Header";
import TodoList from "./components/TodoList";
import { Todo } from "./interfaces";

export default function App() {
  const [todos, setTodos] = useState<Todo[]>([]);

  useEffect(() => {
    fetch("http://localhost:8080/api/v1/todos")
      .then((response) => response.json())
      .then((data) => setTodos(data))
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
