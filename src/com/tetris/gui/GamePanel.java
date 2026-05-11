package com.tetris.gui;

import com.tetris.game.Board;
import com.tetris.game.Game;
import com.tetris.game.Piece;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

/**
 * Handles rendering the main game board and the falling piece.
 */
public class GamePanel extends JPanel {
    private final Game game;
    private static final int CELL_SIZE = 30; // 30x30 pixels per block
    
    // Board dimensions in pixels
    private static final int PANEL_WIDTH = Board.COLUMNS * CELL_SIZE;
    private static final int PANEL_HEIGHT = Board.ROWS * CELL_SIZE;

    public GamePanel(Game game) {
        this.game = game;
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(new Color(20, 20, 20)); // Dark gray background
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawGrid(g);
        drawBoard(g);
        drawCurrentPiece(g);
        drawGameState(g);
    }

    private void drawGrid(Graphics g) {
        g.setColor(new Color(40, 40, 40));
        // Vertical lines
        for (int x = 0; x <= PANEL_WIDTH; x += CELL_SIZE) {
            g.drawLine(x, 0, x, PANEL_HEIGHT);
        }
        // Horizontal lines
        for (int y = 0; y <= PANEL_HEIGHT; y += CELL_SIZE) {
            g.drawLine(0, y, PANEL_WIDTH, y);
        }
    }

    private void drawBoard(Graphics g) {
        Color[][] grid = game.getBoard().getGrid();
        for (int row = 0; row < Board.ROWS; row++) {
            for (int col = 0; col < Board.COLUMNS; col++) {
                if (grid[row][col] != null) {
                    drawBlock(g, col * CELL_SIZE, row * CELL_SIZE, grid[row][col]);
                }
            }
        }
    }

    private void drawCurrentPiece(Graphics g) {
        Piece piece = game.getCurrentPiece();
        if (piece != null && game.isPlaying() && !game.isGameOver()) {
            int[][] matrix = piece.getMatrix();
            for (int row = 0; row < matrix.length; row++) {
                for (int col = 0; col < matrix.length; col++) {
                    if (matrix[row][col] != 0) {
                        int drawX = (piece.getX() + col) * CELL_SIZE;
                        int drawY = (piece.getY() + row) * CELL_SIZE;
                        // Don't draw blocks above the visible board
                        if (drawY >= 0) {
                            drawBlock(g, drawX, drawY, piece.getShape().getColor());
                        }
                    }
                }
            }
        }
    }

    private void drawBlock(Graphics g, int x, int y, Color color) {
        // Fill base color
        g.setColor(color);
        g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
        
        // Draw highlights and shadows for a 3D bevel effect
        g.setColor(color.brighter());
        g.drawLine(x, y, x + CELL_SIZE - 1, y);
        g.drawLine(x, y, x, y + CELL_SIZE - 1);
        
        g.setColor(color.darker());
        g.drawLine(x + CELL_SIZE - 1, y, x + CELL_SIZE - 1, y + CELL_SIZE - 1);
        g.drawLine(x, y + CELL_SIZE - 1, x + CELL_SIZE - 1, y + CELL_SIZE - 1);
        
        // Optional inner border
        // g.setColor(Color.BLACK);
        // g.drawRect(x, y, CELL_SIZE, CELL_SIZE);
    }

    private void drawGameState(Graphics g) {
        if (!game.isPlaying() && !game.isGameOver()) {
            drawCenteredText(g, "PRESS ENTER TO START", new Color(255, 255, 255, 200));
        } else if (game.isPaused()) {
            drawCenteredText(g, "PAUSED", new Color(255, 255, 255, 200));
        } else if (game.isGameOver()) {
            drawCenteredText(g, "GAME OVER\nPRESS ENTER TO RESTART", new Color(255, 50, 50, 220));
        }
    }

    private void drawCenteredText(Graphics g, String text, Color color) {
        g.setFont(new Font("Arial", Font.BOLD, 20));
        FontMetrics fm = g.getFontMetrics();
        g.setColor(color);
        
        // Handle multiline text
        String[] lines = text.split("\n");
        int y = (PANEL_HEIGHT - (fm.getHeight() * lines.length)) / 2 + fm.getAscent();
        
        // Draw a dark semi-transparent background behind text for readability
        g.setColor(new Color(0, 0, 0, 150));
        g.fillRect(0, y - fm.getAscent() - 10, PANEL_WIDTH, (fm.getHeight() * lines.length) + 20);
        
        g.setColor(color);
        for (String line : lines) {
            int x = (PANEL_WIDTH - fm.stringWidth(line)) / 2;
            g.drawString(line, x, y);
            y += fm.getHeight();
        }
    }
}
