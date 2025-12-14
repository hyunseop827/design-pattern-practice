# Command Pattern

Encapsulate a request as an object, thereby letting you parameterize clients with different requests, queue or log requests, and support undoable operations.

## Key Components
- **Command** - Declares an interface for executing an operation
- **ConcreteCommand** - Binds a Receiver to an action
- **Receiver** - Knows how to perform the operations
- **Invoker** - Asks the command to carry out the request
- **Client** - Creates ConcreteCommand and sets its Receiver

## When to Use
- Parameterize objects with an action to perform
- Specify, queue, and execute requests at different times
- Support undo/redo operations
- Support logging changes for recovery

## Examples in This Module
- `example/ex1` - Basic command with Bird, TV, File
- `example/ex2` - TV remote with power/mute toggle
- `example/ex3` - Multi-button remote control
- `prac2` - Stock trading broker system
