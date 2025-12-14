# Template Method Pattern

Define the skeleton of an algorithm in an operation, deferring some steps to subclasses. Template Method lets subclasses redefine certain steps of an algorithm without changing the algorithm's structure.

## Key Components
- **AbstractClass** - Defines the template method and abstract primitive operations
- **ConcreteClass** - Implements the primitive operations
- **Template Method** - Defines the algorithm skeleton (usually `final`)
- **Primitive Operations** - Abstract methods that subclasses must implement
- **Hook Methods** - Optional methods with default behavior

## When to Use
- Implement invariant parts of an algorithm once
- Common behavior should be localized in a common class
- Control subclass extensions (hooks at specific points)

## Examples in This Module
- `example/ex1` - Customer report generator with filtering
- `prac2` - TCP/UDP connection with optional encryption
