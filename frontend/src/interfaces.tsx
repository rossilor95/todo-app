export interface Todo {
  id?: string;
  text: string;
  dueDate: Date | null;
  isCompleted: boolean;
  isImportant: boolean;
}
