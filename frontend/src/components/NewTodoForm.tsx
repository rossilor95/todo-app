import { FormEvent, FormEventHandler, useState } from "react";

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
    <li className="bg-white border-b border-gray-200 shadow hover:bg-blue-50 sm:rounded-md mb-4">
      <div className="px-4 py-4 sm:px-6">
        <div className="flex items-center justify-between">
          <div className="flex items-center">
            <form onSubmit={handleSubmitTodo}>
              <input
                type="text"
                value={todoText}
                onChange={e => setTodoText(e.target.value)}
              />
            </form>
          </div>
        </div>
      </div>
    </li>
  );
}
