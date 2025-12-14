# Factory Method Pattern

Define an interface for creating an object, but let subclasses decide which class to instantiate. Factory Method lets a class defer instantiation to subclasses.

## Key Components
- **Product** - Defines the interface of objects the factory method creates
- **ConcreteProduct** - Implements the Product interface
- **Creator** - Declares the factory method; may define a default implementation
- **ConcreteCreator** - Overrides factory method to return ConcreteProduct

## When to Use
- A class can't anticipate the class of objects it must create
- A class wants its subclasses to specify the objects it creates
- Delegate responsibility to one of several helper subclasses

## Examples in This Module
- `example/ex1` - Game stage with enemy creation (Slime, Goblin, Orc)
- `prac2` - Report generation (PDF, Excel)
