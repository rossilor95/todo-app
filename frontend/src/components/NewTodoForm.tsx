import { FormEvent, useState } from "react";
import { HiPlus } from "react-icons/hi2";


interface TodoFormProps {
  handleAddTodo: (text: string) => void;
}

export default function TodoForm({ handleAddTodo }: TodoFormProps) {
  const [todoText, setTodoText] = useState<string>("");

  const handleSubmitTodo = (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    if (todoText.trim() === "") {
      return;
    }
    handleAddTodo(todoText);
    setTodoText("");
  };

  return (
    <li className="bg-white border-b border-gray-200 shadow sm:rounded-md mb-4">
      <div className="px-4 py-2 sm:px-5 flex items-center">
        <HiPlus className="h-6 w-6 text-blue-600" />
        <form onSubmit={handleSubmitTodo}>
          <input
            id="todo"
            className="border-none flex-grow ml-0.5 placeholder-blue-600 focus:ring-transparent focus:placeholder-black"
            type="text"
            value={todoText}
            placeholder="Add todo"
            onChange={e => setTodoText(e.target.value)}
          />
        </form>
      </div>
    </li>
  );
}
