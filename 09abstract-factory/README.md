# Abstract Factory Pattern

Provide an interface for creating families of related or dependent objects without specifying their concrete classes.

## Key Components
- **AbstractFactory** - Declares interface for creating abstract product objects
- **ConcreteFactory** - Implements operations to create concrete product objects
- **AbstractProduct** - Declares interface for a type of product object
- **ConcreteProduct** - Defines a product to be created by ConcreteFactory
- **Client** - Uses only interfaces declared by AbstractFactory and AbstractProduct

## When to Use
- A system should be independent of how its products are created
- A system should be configured with one of multiple families of products
- A family of related products is designed to be used together
- You want to provide a class library revealing only interfaces

## Examples in This Module
- `example/ex1` - Game stage with themed enemies and items (Forest/Volcano)
- `prac1` - Cross-platform GUI (Windows/Mac buttons and checkboxes)
- `prac2` - Cloud infrastructure (AWS/GCP compute and storage)
