import { MouseEvent } from "react";
import { Todo } from "../interfaces";
import TodoForm from "./TodoForm";

interface TodoItemProps {
  todo: Todo;
  handleContextMenu: (e: MouseEvent, Todo: Todo) => void;
  handleToggleTodo: (id: string) => void;
}

interface TodoListProps {
  todos: Todo[];
  handleToggleTodo: (id: string) => void;
  handleAddTodo: (text: string) => void;
}

function TodoItem({ todo, handleContextMenu, handleToggleTodo }: TodoItemProps) {
  return (
    <li className="bg-white border-b border-gray-200 shadow hover:bg-blue-50 sm:rounded-md mb-4" onContextMenu={(e) => handleContextMenu(e, todo)}>
      <div className="px-4 py-4 sm:px-6 flex items-center">
        <input
          id={`todo-${todo.id}`}
          type="checkbox"
          className="h-4 w-4 rounded-full checked:bg-blue-600 hover:border-blue-600"
          checked={todo.isCompleted}
          onChange={() => handleToggleTodo(todo.id)}
        />
        <label htmlFor={`todo-${todo.id}`} className="ml-5">
          <p className={todo.isCompleted ? "line-through text-gray-500" : undefined}>
            {todo.text}
          </p>
        </label>
      </div>
    </li>
  );
}

export default function TodoList({ todos, handleToggleTodo, handleAddTodo }: TodoListProps) {

  const handleContextMenu = (e: MouseEvent, rightClickTodo: Todo) => {
    e.preventDefault();
    console.log(rightClickTodo);
  };

  return (
    <ul className="mx-6">
      <TodoForm handleAddTodo={handleAddTodo} />
      {todos.map((todo) => (
        <TodoItem key={todo.id} todo={todo} handleContextMenu={handleContextMenu} handleToggleTodo={handleToggleTodo} />
      ))}
    </ul>
  );
}
