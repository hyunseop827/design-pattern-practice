# Singleton Pattern

Ensure a class only has one instance, and provide a global point of access to it.

## Key Components
- **Singleton** - Defines getInstance() that returns the unique instance

## Implementation Variants
- **Eager Initialization** - Instance created at class loading
- **Lazy Initialization** - Instance created on first access
- **Double-Checked Locking (DCL)** - Thread-safe lazy initialization
- **Bill Pugh (Holder)** - Uses static inner class

## When to Use
- Exactly one instance of a class is needed
- The single instance should be extensible by subclassing
- Global access point is required (e.g., logging, configuration)

## Examples in This Module
- `prac1` - Database connection with DCL implementation
