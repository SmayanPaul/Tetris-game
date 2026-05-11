package com.tetris.game;

import java.awt.Color;

/**
 * Represents the Tetris grid and handles placement/collision logic.
 */
public class Board {
    public static final int COLUMNS = 10;
    public static final int ROWS = 20;

    // The grid stores the color of the locked blocks, or null if empty
    private Color[][] grid;

    public Board() {
        grid = new Color[ROWS][COLUMNS];
    }

    public Color[][] getGrid() {
        return grid;
    }

    public void clear() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                grid[row][col] = null;
            }
        }
    }

    /**
     * Checks if the given piece at its current (or projected) position is valid
     * (i.e., not colliding with walls or other blocks).
     */
    public boolean isValidPosition(Piece piece, int newX, int newY, int[][] matrix) {
        int size = matrix.length;
        
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                // If there's a block in the piece's matrix at this position
                if (matrix[row][col] != 0) {
                    int boardX = newX + col;
                    int boardY = newY + row;

                    // Check bounds: Left, Right, Bottom walls
                    if (boardX < 0 || boardX >= COLUMNS || boardY >= ROWS) {
                        return false;
                    }

                    // Check upper bound just in case, though usually piece spawns at 0 or below
                    if (boardY >= 0) {
                        // Check collision with locked blocks
                        if (grid[boardY][boardX] != null) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * Locks the piece into the board's grid.
     */
    public void lockPiece(Piece piece) {
        int[][] matrix = piece.getMatrix();
        int size = matrix.length;
        
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (matrix[row][col] != 0) {
                    int boardY = piece.getY() + row;
                    int boardX = piece.getX() + col;
                    
                    if (boardY >= 0 && boardY < ROWS && boardX >= 0 && boardX < COLUMNS) {
                        grid[boardY][boardX] = piece.getShape().getColor();
                    }
                }
            }
        }
    }

    /**
     * Clears full lines and returns the number of lines cleared.
     */
    public int clearLines() {
        int linesCleared = 0;
        
        // Start from the bottom and move up
        for (int row = ROWS - 1; row >= 0; row--) {
            if (isLineFull(row)) {
                linesCleared++;
                shiftLinesDown(row);
                // Since we shifted down, we need to check this same row index again
                row++;
            }
        }
        
        return linesCleared;
    }

    private boolean isLineFull(int row) {
        for (int col = 0; col < COLUMNS; col++) {
            if (grid[row][col] == null) {
                return false;
            }
        }
        return true;
    }

    private void shiftLinesDown(int startRow) {
        for (int row = startRow; row > 0; row--) {
            for (int col = 0; col < COLUMNS; col++) {
                grid[row][col] = grid[row - 1][col];
            }
        }
        // Clear the top row
        for (int col = 0; col < COLUMNS; col++) {
            grid[0][col] = null;
        }
    }
}
