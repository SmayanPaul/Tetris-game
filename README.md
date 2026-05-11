<div align="center">
  <h1>🧱 Classic Java Tetris</h1>
  <p>
    <b>A robust, fully-featured classic Tetris clone built entirely with Java and Swing.</b>
  </p>
  
  [![Java Version](https://img.shields.io/badge/Java-8%2B-blue.svg)](https://www.oracle.com/java/)
  [![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)
  [![Platform](https://img.shields.io/badge/Platform-Windows%20%7C%20macOS%20%7C%20Linux-lightgray.svg)]()
  [![Contributions Welcome](https://img.shields.io/badge/Contributions-Welcome-brightgreen.svg)]()
</div>

<br>

## 📖 Overview

Welcome to **Java Tetris**, an open-source, object-oriented implementation of the beloved arcade classic. Designed with clean architecture and maintainability in mind, this project serves as both a fully playable game and an educational resource for understanding Java GUI development, game loops, and matrix-based collision detection.

This project is completely self-contained. It relies solely on the standard Java Development Kit (JDK) and `javax.swing` libraries, ensuring it runs seamlessly right out of the box without the need for external engines, dependencies, or complex build configurations.

## ✨ Features

- **Authentic Gameplay:** Implements standard Tetris mechanics including precise piece rotation, bounds checking, and line clearing.
- **Progressive Difficulty:** The game accelerates dynamically. As you clear more lines and level up, the falling speed increases to challenge your reflexes.
- **Classic Tetrominoes:** All 7 standard shapes (I, J, L, O, S, T, Z) with standard coloring and specific rotational pivot logic.
- **Preview Panel:** Displays the upcoming piece to help players plan their next strategic move.
- **Live Scoring & Stats:** Real-time tracking of current score, active level, and total lines cleared.
- **Polished UI:** Custom rendering with pseudo-3D block beveling and dark-mode aesthetics using Java 2D Graphics.

## 🛠️ Tech Stack

- **Language:** Java
- **GUI Framework:** Java Swing / AWT
- **Architecture:** Object-Oriented MVC-inspired pattern

## 📁 Project Structure

```text
Tetris/
├── src/com/tetris/
│   ├── main/          # Application entry point and thread management
│   ├── game/          # Core game engine, logic, physics, and matrix operations
│   ├── gui/           # Swing components, custom painting, and UI layouts
│   └── input/         # Keyboard event listeners
├── .gitignore         # Version control ignore list
├── .gitattributes     # Repository metadata and language stats rules
├── build.bat          # Windows compilation script
├── run.bat            # Windows execution script
└── README.md          # Project documentation
```

## 🚀 Getting Started

### Prerequisites

Ensure you have the [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/downloads/) (version 8 or newer) installed on your system.
Verify your installation by running:
```bash
java -version
```

### Installation & Execution

#### Option 1: Quick Start (Windows)
1. Clone the repository: `git clone https://github.com/SmayanPaul/Tetris-game.git`
2. Open the project folder.
3. Double-click `build.bat` to compile the source code into a local `bin/` directory.
4. Double-click `run.bat` to launch the game.

#### Option 2: Command Line (Cross-Platform)
Open your preferred terminal and execute the following:

```bash
# 1. Clone the repository
git clone https://github.com/SmayanPaul/Tetris-game.git
cd Tetris-game

# 2. Compile the Java files
mkdir bin
javac -d bin src/com/tetris/**/*.java

# 3. Run the application
java -cp bin com.tetris.main.Main
```

## 🎮 Controls

The game supports standard keyboard inputs for seamless control:

| Action | Primary Key | Secondary Key |
| :--- | :---: | :---: |
| **Move Left** | `←` Left Arrow | `A` |
| **Move Right**| `→` Right Arrow | `D` |
| **Rotate**    | `↑` Up Arrow | `W` / `Space` |
| **Soft Drop** | `↓` Down Arrow | `S` |
| **Start / Restart** | `Enter` | - |
| **Pause**     | `P` | - |

*(Note: "Hard Drop" functionality can be added in future updates!)*

## 🤝 Contributing

Contributions, issues, and feature requests are highly welcome!
If you'd like to improve the game (e.g., adding sound effects, a high-score saving system, ghost pieces, or wall kicks), feel free to fork the repository and submit a pull request.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

Distributed under the MIT License. See `LICENSE` for more information.
