interface CategoryToolbarProps {
  selectedCategory: string;
}

export default function CategoryToolbar({
  selectedCategory,
}: CategoryToolbarProps) {
  return (
    <div className="mx-6 py-4">
      <h2 className="text-2xl font-semibold pb-1">{selectedCategory}</h2>
      {selectedCategory === "My day" && (
        <h3 className="text-sm text-gray-500">
          {new Date().toLocaleDateString("en-US", {
            weekday: "long",
            month: "long",
            day: "numeric",
          })}
        </h3>
      )}
    </div>
  );
}
