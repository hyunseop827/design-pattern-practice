# Composite Pattern

Compose objects into tree structures to represent part-whole hierarchies. Composite lets clients treat individual objects and compositions of objects uniformly.

## Key Components
- **Component** - Declares interface for objects in the composition
- **Leaf** - Represents leaf objects (no children)
- **Composite** - Defines behavior for components having children

## When to Use
- Represent part-whole hierarchies of objects
- Clients should ignore the difference between compositions and individual objects
- Treat all objects in the composite structure uniformly

## Examples in This Module
- `example/ex1` - File system (File and Directory)
- `prac1` - File/Directory structure (before/after)
- `prac2` - Restaurant menu system
