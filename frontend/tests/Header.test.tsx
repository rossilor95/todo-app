import { render, screen } from "@testing-library/react";
import { describe, expect, it } from "vitest";
import Header from "../src/components/Header";

describe("Header", () => {
  it("should render the app title", () => {
    render(<Header />);
    expect(screen.getByText("Todo App")).toBeInTheDocument();
  });
});
