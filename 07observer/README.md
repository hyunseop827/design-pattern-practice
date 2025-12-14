# Observer Pattern

Define a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically.

## Key Components
- **Subject** - Knows its observers; provides interface for attaching/detaching
- **ConcreteSubject** - Stores state; sends notification when state changes
- **Observer** - Defines an updating interface
- **ConcreteObserver** - Maintains reference to ConcreteSubject; implements update

## Variants
- **Push Model** - Subject sends detailed change information
- **Pull Model** - Subject sends minimal notification; observers query for details

## When to Use
- A change to one object requires changing others (unknown how many)
- An object should notify others without knowing who they are
- Loose coupling between objects

## Examples in This Module
- `example/ex1` - Battery level display and low battery warning
- `prac1` - Weather station with multiple displays (Pull model)
- `prac2` - Game event system (monster killed, item picked, level up)
