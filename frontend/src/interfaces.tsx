export interface Todo {
  id: string;
  text: string;
  dueDate: Date | null;
  isCompleted: boolean;
  isImportant: boolean;
}

export interface TodoInput {
  text?: string;
  dueDate?: Date | null;
  isCompleted?: boolean;
  isImportant?: boolean;
}
