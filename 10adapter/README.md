# Adapter Pattern

Convert the interface of a class into another interface clients expect. Adapter lets classes work together that couldn't otherwise because of incompatible interfaces.

## Key Components
- **Target** - Defines the domain-specific interface that Client uses
- **Adapter** - Adapts the interface of Adaptee to the Target interface
- **Adaptee** - Defines an existing interface that needs adapting
- **Client** - Collaborates with objects conforming to the Target interface

## Variants
- **Class Adapter** - Uses multiple inheritance (not common in Java)
- **Object Adapter** - Uses composition (preferred in Java)

## When to Use
- Use an existing class with an incompatible interface
- Create a reusable class that cooperates with unrelated classes
- Need to use several existing subclasses without subclassing each one

## Examples in This Module
- `prac1` - Logger adapter for FancyLogLibrary
- `prac2` - Robot adapter for AlienMachine (meters to feet conversion)
