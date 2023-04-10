import { render, screen } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import { describe, expect, it, vitest } from "vitest";
import TodoForm from "../src/components/TodoForm";

describe("NewTodoForm", () => {
  it("should render correctly", () => {
    const handleAddTodo = vitest.fn();
    render(<TodoForm handleAddTodo={handleAddTodo} />);
    expect(screen.getByPlaceholderText("Add todo")).toBeInTheDocument();
  });

  it("should call handleAddTodo when submitting non-empty todo", async () => {
    const user = userEvent.setup();
    const handleAddTodo = vitest.fn();
    render(<TodoForm handleAddTodo={handleAddTodo} />);
    await user.click(screen.getByPlaceholderText("Add todo"));
    await user.keyboard("Test[Enter]");
    expect(handleAddTodo).toHaveBeenCalledWith("Test");
  });

  it("should not call handleAddTodo when submitting empty todo", async () => {
    const user = userEvent.setup();
    const handleAddTodo = vitest.fn();
    render(<TodoForm handleAddTodo={handleAddTodo} />);
    await user.click(screen.getByPlaceholderText("Add todo"));
    await user.keyboard("[Backspace][Enter]");
    expect(handleAddTodo).not.toHaveBeenCalled();
  });
});
