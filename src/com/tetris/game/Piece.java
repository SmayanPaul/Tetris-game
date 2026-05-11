package com.tetris.game;

/**
 * Represents the current falling piece on the board.
 */
public class Piece {
    private final Shape shape;
    private int x;
    private int y;
    private int rotation;

    public Piece(Shape shape) {
        this.shape = shape;
        this.rotation = 0;
        
        // Center the piece horizontally
        this.x = 5 - (shape.getSize() / 2);
        
        // Adjust initial Y so the piece spawns just above the visible board if possible,
        // or at the very top. For simplicity, we spawn at Y=0.
        // I pieces and O pieces might need slight adjustments.
        this.y = 0;
    }

    public Shape getShape() {
        return shape;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

    public int[][] getMatrix() {
        return shape.getMatrix(rotation);
    }
    
    public int[][] getNextRotationMatrix() {
        return shape.getMatrix(rotation + 1);
    }

    public void rotate() {
        rotation = (rotation + 1) % 4;
    }

    public void moveDown() {
        y++;
    }

    public void moveLeft() {
        x--;
    }

    public void moveRight() {
        x++;
    }
}
