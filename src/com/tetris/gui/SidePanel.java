package com.tetris.gui;

import com.tetris.game.Game;
import com.tetris.game.Piece;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

/**
 * Handles displaying the game statistics and the next piece preview.
 */
public class SidePanel extends JPanel {
    private final Game game;
    private static final int PANEL_WIDTH = 200;
    private static final int PANEL_HEIGHT = 600; // Same as GamePanel height (20 * 30)
    private static final int CELL_SIZE = 30;

    public SidePanel(Game game) {
        this.game = game;
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(new Color(30, 30, 30));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawStats(g);
        drawNextPiece(g);
        drawControls(g);
    }

    private void drawStats(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        
        g.drawString("SCORE", 20, 40);
        g.setColor(Color.YELLOW);
        g.drawString(String.valueOf(game.getScore()), 20, 65);

        g.setColor(Color.WHITE);
        g.drawString("LEVEL", 20, 110);
        g.setColor(Color.CYAN);
        g.drawString(String.valueOf(game.getLevel()), 20, 135);

        g.setColor(Color.WHITE);
        g.drawString("LINES", 20, 180);
        g.setColor(Color.GREEN);
        g.drawString(String.valueOf(game.getLinesCleared()), 20, 205);
    }

    private void drawNextPiece(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("NEXT", 20, 270);

        // Draw the preview box
        g.setColor(new Color(50, 50, 50));
        int boxX = 20;
        int boxY = 290;
        int boxSize = 140;
        g.fillRect(boxX, boxY, boxSize, boxSize);
        g.setColor(Color.GRAY);
        g.drawRect(boxX, boxY, boxSize, boxSize);

        Piece next = game.getNextPiece();
        if (next != null) {
            int[][] matrix = next.getMatrix();
            int size = matrix.length;
            
            // Calculate centering offsets within the preview box
            int pieceWidth = 0;
            int pieceHeight = 0;
            for(int r=0; r<size; r++) {
                for(int c=0; c<size; c++) {
                    if(matrix[r][c] != 0) {
                        pieceWidth = Math.max(pieceWidth, c + 1);
                        pieceHeight = Math.max(pieceHeight, r + 1);
                    }
                }
            }
            
            int startX = boxX + (boxSize - (pieceWidth * CELL_SIZE)) / 2;
            int startY = boxY + (boxSize - (pieceHeight * CELL_SIZE)) / 2;

            for (int row = 0; row < size; row++) {
                for (int col = 0; col < size; col++) {
                    if (matrix[row][col] != 0) {
                        drawBlock(g, startX + (col * CELL_SIZE), startY + (row * CELL_SIZE), next.getShape().getColor());
                    }
                }
            }
        }
    }
    
    private void drawControls(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        
        int startY = 480;
        g.drawString("CONTROLS:", 20, startY);
        g.drawString("\u2190 \u2192 : Move", 20, startY + 20);
        g.drawString("\u2191 / Space : Rotate", 20, startY + 40);
        g.drawString("\u2193 : Soft Drop", 20, startY + 60);
        g.drawString("Enter : Start/Restart", 20, startY + 80);
        g.drawString("P : Pause", 20, startY + 100);
    }

    private void drawBlock(Graphics g, int x, int y, Color color) {
        g.setColor(color);
        g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
        
        g.setColor(color.brighter());
        g.drawLine(x, y, x + CELL_SIZE - 1, y);
        g.drawLine(x, y, x, y + CELL_SIZE - 1);
        
        g.setColor(color.darker());
        g.drawLine(x + CELL_SIZE - 1, y, x + CELL_SIZE - 1, y + CELL_SIZE - 1);
        g.drawLine(x, y + CELL_SIZE - 1, x + CELL_SIZE - 1, y + CELL_SIZE - 1);
    }
}
