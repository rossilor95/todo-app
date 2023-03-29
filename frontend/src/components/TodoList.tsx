import { Todo } from "../interfaces";
import NewTodoForm from "./NewTodoForm";

interface TodoItemProps {
  todo: Todo;
  handleToggleTodo: (id: number) => void;
}

interface TodoListProps {
  todos: Todo[];
  handleToggleTodo: (id: number) => void;
  handleAddTodo: (text: string) => void;
}

function TodoItem({ todo, handleToggleTodo }: TodoItemProps) {
  return (
    <li className="bg-white border-b border-gray-200 shadow hover:bg-blue-50 sm:rounded-md mb-4">
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
  return (
    <ul className="mx-6">
      <NewTodoForm handleAddTodo={handleAddTodo} />
      {todos.map((todo) => (
        <TodoItem todo={todo} handleToggleTodo={handleToggleTodo} />
      ))}
    </ul>
  );
}
