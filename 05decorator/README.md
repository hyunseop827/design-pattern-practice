# Decorator Pattern

Attach additional responsibilities to an object dynamically. Decorators provide a flexible alternative to subclassing for extending functionality.

## Key Components
- **Component** - Defines interface for objects that can have responsibilities added
- **ConcreteComponent** - Defines an object to which responsibilities can be attached
- **Decorator** - Maintains a reference to a Component and conforms to Component's interface
- **ConcreteDecorator** - Adds responsibilities to the component

## When to Use
- Add responsibilities to individual objects dynamically and transparently
- Responsibilities can be withdrawn
- Extension by subclassing is impractical

## Examples in This Module
- `example/ex1` - Text rendering with surround and padding decorators
- `prac1` - Toast with toppings (cheese, ham, jelly)
- `prac2` - Notification system (Email + SMS + Slack)
