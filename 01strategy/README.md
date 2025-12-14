# Strategy Pattern

Define a family of algorithms, encapsulate each one, and make them interchangeable. Strategy lets the algorithm vary independently from clients that use it.

## Key Components
- **Strategy** - Interface for the algorithm
- **ConcreteStrategy** - Implements the algorithm
- **Context** - Maintains a reference to a Strategy object

## When to Use
- Multiple classes differ only in their behavior
- You need different variants of an algorithm
- Avoid exposing complex, algorithm-specific data structures

## Examples in This Module
- `example/ex1` - Salary calculation with different strategies
- `prac1` - Shopping cart discount by membership type
- `prac2` - Game character weapon system
