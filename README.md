# Console Chess

Date: January 2023

## Objectives

- **Creation** of a functioning terminal-based chess simulator game.
- **Inclusion** of numerous design patterns where required & appropriate.
- **Abiding** by S.O.L.I.D principles throughout the project.

---

## Chess Description

Chess is a two-player strategy game played on a checkered gameboard consisting of 64 squares arranged in an 8x8 grid. Each player starts with 16 pieces: one king, one queen, two rooks, two knights, two bishops, and eight pawns. The goal is to checkmate the opponent's king, putting it in a position where it is under immediate attack (in "check") and cannot escape (mate). Players alternate turns to make moves with their pieces, aiming to checkmate the opponent’s king.

---

## Packaging Hierarchy

The project’s directory structure is organized as follows:

- **Root Directory: `Chess`**
  - **`Chess.java`**: Main class responsible for executing the project.
  - **Three Main Directories**:
    - **`Game`**: Contains game-related components.
    - **`Console`**: Manages console input, output, and commands.
    - **`Utils`**: Houses general utilities.

### Utils

- No secondary directories.

### Console

- **Directories**:
  - **`Commands`**: Contains command implementations, factories, and executors.
    - **`Impl`**: Command implementations.
  - **`IO`**: Manages console I/O and dependencies.
    - **`Messages`**: Contains message encapsulator and builder.

### Game

- **Main Class**: **`ChessGame.java`**
- **Directories**:
  - **`Components`**: Contains game components.
    - **`Board`**: Board logic, components, and printer.
    - **`Pieces`**: Piece implementations and factory.
    - **`Players`**: Player class and printer.
  - **`Validators`**: Movement and attack checkers.
    - **`Movements`**: Movement validation handlers.
  - **`Executors`**: Movement execution simulations.
  - **`Data`**: Game data and encapsulations.

---

## Project Code Analysis

### 1. Chess Game & Components Creation

- **Chessboard Creation**
- **Players Creation**

### 2. Console Initialization & Function

- **Input Stream Creation**
- **ANSI Codes Formatting**
- **Message Building**

### 3. Commands Factory, Polling, & Execution

- **Creation of Commands**
- **Input Polling**
- **Polled Input Execution**

### 4. Creation & Destruction Commands

- **`start` Command**
- **`stopgame` Command**
- **`reset` Command**
- **`exit` Command**

### 5. Pieces Factory

### 6. Move Command

### 7. Movement Simulation

### 8. Movement Validation

- **Grid Movement Validation**
- **Pawn Movement Validation**
- **En Passant Movement Validation**
- **Knight Movement Validation**
- **King Movement Validation**

### 9. Movement Execution

- **Self-Checking Prevention**
- **Pawn Transformation**
- **Consume Attacked Piece**
- **Move Piece**
- **Move Castled Rook (if castling)**
- **Checkmate Handling**
- **Max Movement Draw Handling**

### 10. Printing Chessboard & Player Data

- **Chessboard Printer**
- **`show` Command**
- **`toggle board` Command**
- **`set color` Command**
- **Player Printer**
- **`username` Command**
- **`points` Command**
- **`?` Help**

---

## Chess Game & Components Creation

A `ChessGame` instance is created by instantiating the **`ChessGame.java`** class, which initializes:

- **The Board**
- **The Players**
- **The Pieces** (via commands)

### Chessboard Creation

- The chessboard consists of tiles with a checkered pattern.
- Each tile is indexed by coordinates [X, Y], starting at [0, 0].

### Players Creation

- Each player has a counter for points, can be either white or black, and can change names.

---

## Console

The console class is publicly accessible and used for output and input. It initializes the input stream in the `init()` method, called from the `main()` method in **`Chess.java`**.

### ANSI Formatting

- Utilizes ANSI codes for color and texture.
- Formatting is centralized in **`AnsiColors.java`**.

### Message Building

- Implemented using the Builder pattern in **`MessageBuilder.java`** for easy message formatting.

---

## Commands Factory, Polling, & Execution

### Commands Factory

- **`CommandFactory.java`** creates commands on demand.

### Commands Polling

- **`CommandPoller.java`** listens for user input and sends it to the Command Executor.

### Commands Execution

- Validates and executes commands using the factory and command abstractions.

---

## Creation & Destruction Commands

### `start` Command

- Initializes the board with pieces and starts the game.

### `stopgame` Command

- Stops the game without resetting data.

### `reset` Command

- Empties the board and resets validators.

### `exit` Command

- Terminates the program by stopping the CommandPoller.

---

## Pieces & PiecesFactory

- **Factory** creates pieces with a specified color.
- **Pieces**: Rook, Knight, Bishop, Queen, King, Pawn.

---

## Move Command

- Moves pieces with specified origin and target tiles.

---

## Movement Simulation

- Uses **`simulateQuery()`** to check if a move is valid before applying it.

---

## Movement Validation

- **GridValidator**: Checks movement in uniform directions.
- **PawnValidator**: Checks initial position and movement.
- **En Passant Validator**: Validates en passant moves.
- **KnightValidator**: Checks L-shaped movements.
- **KingValidator**: Validates king's movement and checks.

---

## Movement Execution

- **Self-Checking Prevention**: Ensures no self-checks.
- **Pawn Transformation**: Transforms pawns upon reaching the opposite side.
- **Move Rook if Castling Validated**: Moves rook if castling.
- **Checkmate Handling**: Handles checkmate scenarios.
- **Exceeded Maximum Move Count**: Ends game after 50 moves.

---

## Printing Chessboard & Player Data

- **Chessboard Printer**: Uses Unicode and ANSI codes for representation.
- **Commands**: `show`, `toggleboard`, `setcolor`.
- **Player Printer**: Displays player data alongside the board.

---

## Additional Information

### Design Patterns Used

- **Singleton**: `GameConfig.java`
- **Factory**: `CommandFactory.java`, `PieceFactory.java`
- **Builder**: `MessageBuilder.java`
- **Chain of Command**: Validators
- **Command**: `Command.java`

### OOP & Design Patterns

- **Singleton**: Used for configuration management.
- **Factory**: For command and piece creation.
- **Builder**: For message formatting.
- **Command**: For handling console inputs.
- **Chain of Command**: For validation processes.

### S.O.L.I.D Principles

- **Single Responsibility**: ChessBoard class.
- **Open-Closed Principle**: Implemented through factory patterns.
- **Liskov Substitution**: Maintained.

---

## Test Cases

### Feature Testing

- **En Passant & Pawn Transformation**: Tested various moves including en passant and transformation.
- **Max Move Count**: Set `ChessGame.MAX_MOVE` to 1 and tested moves.

### Duels

- **Duel #1**: Bent Corneliussen vs Martin Jensen, 1963
- **Duel #2**: Mark Greenwalt vs Bill Wall, 1983

