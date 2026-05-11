# Java Tetris

A complete, object-oriented Tetris clone built in Java using Swing.

## Features
- Full Tetris rules: rotation, line clears, scoring, leveling up.
- Classic 7 Tetrominoes with standard colors.
- Next piece preview.
- Game over detection and restart.
- Progressive difficulty (blocks fall faster as you clear lines and level up).

## How to Run

### Windows
You can simply double-click `build.bat` to compile the game, and then double-click `run.bat` to play it.

Alternatively, from the command line:
```cmd
build.bat
run.bat
```

### Manual Compilation
```bash
# Compile
javac -d bin src/com/tetris/**/*.java

# Run
java -cp bin com.tetris.main.Main
```

## Controls
- **Left Arrow / A** : Move Left
- **Right Arrow / D** : Move Right
- **Down Arrow / S** : Soft Drop (Move down faster)
- **Up Arrow / W / Space** : Rotate
- **Enter** : Start / Restart Game
- **P** : Pause Game
