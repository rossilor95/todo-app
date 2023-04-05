import { render, screen } from "@testing-library/react";
import { describe, expect, it, vitest } from "vitest";
import { Todo } from "../src/interfaces";
import TodoList from "../src/components/TodoList";
import userEvent from "@testing-library/user-event";

const todos: Todo[] = [
  { id: 1, text: "Finalise presentation", isCompleted: true },
  { id: 2, text: "Book flight to London", isCompleted: false },
  { id: 3, text: "Get flowers for nana", isCompleted: false },
  { id: 4, text: "Get groceries for dinner", isCompleted: false }
];

describe("TodoList", () => {
  it("should render TodoList component with NewTodoForm and todos", () => {
    const handleAddTodo = vitest.fn();
    const handleToggleTodo = vitest.fn();
    render(<TodoList todos={todos} handleAddTodo={handleAddTodo} handleToggleTodo={handleToggleTodo} />);
    expect(screen.getAllByRole("listitem")).toHaveLength(todos.length + 1);
  });
  it("should call handleToggleTodo when TodoItem checkbox is checked", async () => {
    const user = userEvent.setup();
    const handleAddTodo = vitest.fn();
    const handleToggleTodo = vitest.fn();
    render(<TodoList todos={todos} handleAddTodo={handleAddTodo} handleToggleTodo={handleToggleTodo} />);
    await user.click(screen.getByLabelText("Finalise presentation"));
    expect(handleToggleTodo).toHaveBeenCalledOnce();  
  });                      
});
