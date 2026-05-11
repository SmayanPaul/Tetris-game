package com.tetris.game;

/**
 * Manages the core game state, score, level, and game loop timing.
 */
public class Game {
    private Board board;
    private Piece currentPiece;
    private Piece nextPiece;
    
    private boolean isPlaying;
    private boolean isPaused;
    private boolean isGameOver;
    
    private int score;
    private int level;
    private int linesCleared;
    
    // Base speed in milliseconds
    private static final int BASE_SPEED = 800;

    public Game() {
        board = new Board();
        initGame();
    }

    public void initGame() {
        board.clear();
        score = 0;
        level = 1;
        linesCleared = 0;
        isPlaying = false;
        isPaused = false;
        isGameOver = false;
        
        nextPiece = new Piece(Shape.getRandomShape());
        spawnPiece();
    }

    public void start() {
        initGame();
        isPlaying = true;
    }

    public void pause() {
        if (isPlaying && !isGameOver) {
            isPaused = !isPaused;
        }
    }

    private void spawnPiece() {
        currentPiece = nextPiece;
        nextPiece = new Piece(Shape.getRandomShape());
        
        // Check for immediate collision (Game Over condition)
        if (!board.isValidPosition(currentPiece, currentPiece.getX(), currentPiece.getY(), currentPiece.getMatrix())) {
            isGameOver = true;
            isPlaying = false;
        }
    }

    public void update() {
        if (!isPlaying || isPaused || isGameOver) {
            return;
        }

        // Try to move down
        if (board.isValidPosition(currentPiece, currentPiece.getX(), currentPiece.getY() + 1, currentPiece.getMatrix())) {
            currentPiece.moveDown();
        } else {
            // Lock piece and handle line clears
            board.lockPiece(currentPiece);
            int cleared = board.clearLines();
            updateScore(cleared);
            spawnPiece();
        }
    }

    public void moveLeft() {
        if (!isPlaying || isPaused || isGameOver) return;
        if (board.isValidPosition(currentPiece, currentPiece.getX() - 1, currentPiece.getY(), currentPiece.getMatrix())) {
            currentPiece.moveLeft();
        }
    }

    public void moveRight() {
        if (!isPlaying || isPaused || isGameOver) return;
        if (board.isValidPosition(currentPiece, currentPiece.getX() + 1, currentPiece.getY(), currentPiece.getMatrix())) {
            currentPiece.moveRight();
        }
    }

    public void moveDown() {
        if (!isPlaying || isPaused || isGameOver) return;
        if (board.isValidPosition(currentPiece, currentPiece.getX(), currentPiece.getY() + 1, currentPiece.getMatrix())) {
            currentPiece.moveDown();
            // Small score bump for soft drop
            score += 1;
        }
    }

    public void hardDrop() {
        if (!isPlaying || isPaused || isGameOver) return;
        int dropDistance = 0;
        while (board.isValidPosition(currentPiece, currentPiece.getX(), currentPiece.getY() + 1, currentPiece.getMatrix())) {
            currentPiece.moveDown();
            dropDistance++;
        }
        score += dropDistance * 2; // Hard drop bonus
        // Immediately lock and update state
        board.lockPiece(currentPiece);
        int cleared = board.clearLines();
        updateScore(cleared);
        spawnPiece();
    }

    public void rotate() {
        if (!isPlaying || isPaused || isGameOver) return;
        if (board.isValidPosition(currentPiece, currentPiece.getX(), currentPiece.getY(), currentPiece.getNextRotationMatrix())) {
            currentPiece.rotate();
        } else {
            // Optional: Implement wall kick logic here
            // Try shifting left or right once
            if (board.isValidPosition(currentPiece, currentPiece.getX() - 1, currentPiece.getY(), currentPiece.getNextRotationMatrix())) {
                currentPiece.moveLeft();
                currentPiece.rotate();
            } else if (board.isValidPosition(currentPiece, currentPiece.getX() + 1, currentPiece.getY(), currentPiece.getNextRotationMatrix())) {
                currentPiece.moveRight();
                currentPiece.rotate();
            }
        }
    }

    private void updateScore(int lines) {
        if (lines > 0) {
            linesCleared += lines;
            
            // Standard Tetris scoring system based on level
            int lineScore = 0;
            switch (lines) {
                case 1: lineScore = 40; break;
                case 2: lineScore = 100; break;
                case 3: lineScore = 300; break;
                case 4: lineScore = 1200; break;
            }
            score += lineScore * level;
            
            // Level up every 10 lines
            level = (linesCleared / 10) + 1;
        }
    }

    public int getDelay() {
        // Speed curve: getting faster as level increases
        int delay = BASE_SPEED - ((level - 1) * 50);
        return Math.max(delay, 50); // Minimum delay of 50ms
    }

    public Board getBoard() { return board; }
    public Piece getCurrentPiece() { return currentPiece; }
    public Piece getNextPiece() { return nextPiece; }
    public boolean isPlaying() { return isPlaying; }
    public boolean isPaused() { return isPaused; }
    public boolean isGameOver() { return isGameOver; }
    public int getScore() { return score; }
    public int getLevel() { return level; }
    public int getLinesCleared() { return linesCleared; }
}
